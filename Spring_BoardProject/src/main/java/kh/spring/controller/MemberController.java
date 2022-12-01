package kh.spring.controller;


import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kh.spring.dao.FileDAO;
import kh.spring.dao.MemberDAO;
import kh.spring.dto.BoardDTO;
import kh.spring.dto.FileDTO;
import kh.spring.dto.MemberDTO;

@Controller
@RequestMapping("/member/")
public class MemberController {

	@Autowired
	private MemberDAO dao;
	
	@Autowired // 필드 하나에 하나
	private HttpSession session;
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(String id, String pw) throws Exception {
		System.out.println(id + " : " + pw);
		int result = dao.isLoginOk(id,pw);
		if(result>0) {
			session.setAttribute("loginID",id);
		}
		return "redirect:/";
	}
	
	@RequestMapping("toSignup")
	public String signup() {
		return "member/signup";
	}
	
	@ResponseBody // ajax
	@RequestMapping(value = "idCheck", produces = "text/html;charset=utf8")
	public String idCheck(String id) throws Exception {		
//		서버가 값을 받을 땐 차이가 없으나, 돌려보낼 때 차이가 생긴다.
//		ajax 는 페이지 전환 없이 데이터만 주고 받기때문에 foward 와 redirect 하면 안된다.
		int result = dao.idCheck(id);
		if (result>0) {
			return "true";
		}
		return "false";
	}
	
//	@RequestMapping(value = "sign", method = RequestMethod.POST)
//	public String sign(MemberDTO dto) throws Exception {
//		dao.sign(dto);
//		System.out.println("회원가입 성공");
//		return "redirect:/";
//	}
	
	
	@RequestMapping(value = "sign", produces = "text/html;charset=utf8", method = RequestMethod.POST)
	public String sign(MemberDTO dto, MultipartFile[] files) throws Exception {
		if(files != null) {
		
			String realPath = session.getServletContext().getRealPath("profile");
			File filePath = new File(realPath);
			if(!filePath.exists()) {filePath.mkdir();}
			
			System.out.println(files.length);
	
			if(!files[0].getOriginalFilename().equals("")) {
				for(MultipartFile file : files) {
					
					System.out.println(file.getOriginalFilename());
					if(file.getOriginalFilename()==null) {continue;}
					
					String oriName = file.getOriginalFilename();
					String sysName = UUID.randomUUID() + "_" + oriName;
					file.transferTo(new File(filePath+"/"+sysName));
					
					dao.sign(dto, sysName);
				}
			}
		}
		dao.sign(dto, null);
		System.out.println("회원가입 성공");
		return "redirect:/";
	}
	
	
	@RequestMapping("logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("mypage")
	public String mypage(Model model) throws Exception {
		String loginID = (String) session.getAttribute("loginID");
		MemberDTO dto = dao.selectById(loginID);
		model.addAttribute("list", dto);
		System.out.println("마이페이지 출력 성공");

		System.out.println(dto.getName());
		return "member/mypageView";
	}
	
	@RequestMapping("modifyPage")
	public String modifyPage(Model model) throws Exception {
		String loginID = (String) session.getAttribute("loginID");
		MemberDTO dto = dao.selectById(loginID);
		model.addAttribute("list", dto);
		return "member/mypageModify";
	}
	
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public String modify(MemberDTO dto, Model model) throws Exception {
		dao.modify(dto);
		return "redirect:/member/mypage";
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String delete() throws Exception {
		String loginID = (String) session.getAttribute("loginID");
		dao.delete(loginID);
		session.invalidate();
		System.out.println("회원탈퇴 성공");
		return "redirect:/";
	}
	
	// 예외 처리 생기면 얘가 동작하도록 처리 try catch 처리 안할 수 있는 방법(throws Exception은 한다)
//	@ExceptionHandler(NumberFormatException.class)
//	@ExceptionHandler(NullPointerException.class)

	// Dispatcher 처리 되기 전에 이 메서드 먼저 타고, 그 뒤에 Dispatcher 처리 됨
	@ExceptionHandler(Exception.class) // 모든 예외의 조상, 모든 예외 처리
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}
	
}
