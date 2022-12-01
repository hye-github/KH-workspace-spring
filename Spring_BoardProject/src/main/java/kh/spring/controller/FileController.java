package kh.spring.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import kh.spring.dao.BoardDAO;
import kh.spring.dao.FileDAO;
import kh.spring.dto.BoardDTO;
import kh.spring.dto.FileDTO;

@Controller
@RequestMapping("/file/")
public class FileController {

	@Autowired
	private BoardDAO daoBd;

	@Autowired
	private FileDAO daoFl;

	@Autowired
	private HttpSession session;
	
	
	@RequestMapping("upload")
	public String upload(BoardDTO dto, String title, String contents, MultipartFile file) throws Exception { // cos.jar 사용하지않는걸 추천. apach 사용을 추천.
		
		System.out.println(title);
		System.out.println(contents);
		
		int nextVal = daoBd.getNextVal();
		daoBd.insert(nextVal, dto);
		
		
		System.out.println(file.getOriginalFilename());
		
		String realPath = session.getServletContext().getRealPath("upload");
		File filePath = new File(realPath);
		
		if(!filePath.exists()) {filePath.mkdir();}
		
		String oriName = file.getOriginalFilename();
		String sysName = UUID.randomUUID() + "_" + oriName;
		// 겹치지않는 이름을 써야함. ex. 현재 시간값 - 사용자가 많으면 겹칠 수도 있다.
		// 겹치지않는 이름을 만들어주는 클래스가 있다.
				
		// MultipartFile file 얘는 파일이 임시 저장됨. 사라지기 전에 복사해놔야함.
		file.transferTo(new File(filePath+"/"+sysName));
		
		daoFl.insert(new FileDTO(0, oriName, sysName, nextVal));
		
		return "redirect:/";
	}

	@RequestMapping(value = "multiUpload", produces = "text/html;charset=utf8")
	public String multiUpload(BoardDTO dto, String title, String contents, MultipartFile[] files) throws Exception {
		
		int nextVal = daoBd.getNextVal();
		daoBd.insert(nextVal, dto);

		String realPath = session.getServletContext().getRealPath("upload");
		File filePath = new File(realPath);
		if(!filePath.exists()) {filePath.mkdir();}
		
		System.out.println(files.length);

		if(!files[0].getOriginalFilename().equals("")) {
//		if(files[0].getOriginalFilename()!="") {
			
			for(MultipartFile file : files) {
				
				System.out.println(file.getOriginalFilename());
				// 안전하게 하려면, - input을 여러개 만들어서 하게 하려면 아래와 같이 적는게 좋다.
				// null 은 거르기
				// front에서 걸러내는게 제일 베스트임. 빼고 submit 하는게 좋다.
				if(file.getOriginalFilename()==null) {continue;}
				
				String oriName = file.getOriginalFilename();
				String sysName = UUID.randomUUID() + "_" + oriName;
				// 겹치지않는 이름을 써야함. ex. 현재 시간값 - 사용자가 많으면 겹칠 수도 있다.
				// 겹치지않는 이름을 만들어주는 클래스가 있다. UUID
	
				// MultipartFile file 얘는 파일이 임시 저장됨. 사라지기 전에 복사해놔야함.
				file.transferTo(new File(filePath+"/"+sysName));
				
				daoFl.insert(new FileDTO(0, oriName, sysName, nextVal));	
			}
		}
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "download")
	public void download(String oriname, String sysname, HttpServletResponse resp) throws Exception {
		
		System.out.println(oriname);
		System.out.println(sysname);
		String realPath = session.getServletContext().getRealPath("upload");
		File targetFile = new File(realPath + "/" + sysname);
		
		oriname =  new String(oriname.getBytes("utf8"), "ISO-8859-1"); // 크롬 인코딩 설정
//		oriname =  new String(oriname.getBytes("utf8"), "utf8");

		System.out.println("2번째 : " + oriname);
		
		resp.setHeader("content-disposition", "attachment;filename=\"" + oriname + "\";");
		// 응답 데이터가 첨부파일임을 알림, 다운로드 파일 이름 세팅
		
		// 하드디스크에 있는 targetFile 을 꺼내오는 Stream 개방
		try(DataInputStream dis = new DataInputStream(new FileInputStream(targetFile));
			DataOutputStream dos = new DataOutputStream(resp.getOutputStream());
//			ServletOutputStream dos = resp.getOutputStream();	
				){
			// Client 에게 direct 로 전송할 네트워크 Stream 개방
			
			byte[] fileContents = new byte[(int)targetFile.length()];
			
			dis.readFully(fileContents);//배열에 데이터 담기.
			dos.write(fileContents);
			dos.flush();
		}
	}
	
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}
	
}
