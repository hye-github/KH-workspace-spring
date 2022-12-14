package kh.spring.controller;


import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kh.spring.dao.MemberDAO;
import kh.spring.dto.MemberDTO;
import kh.spring.services.MemberService;

@Controller
@RequestMapping("/member/")
public class MemberController {
	
	@Autowired // 필드 하나에 하나
	private HttpSession session;
	
	@Autowired
	private MemberService service;
	
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(MemberDTO dto) throws Exception {
		boolean result = service.isLoginOk(dto);
		if(result) {
			session.setAttribute("loginID",dto.getId());
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
		System.out.println(id);
		boolean result = service.idCheck(id);

		System.out.println(result);
		return String.valueOf(result);
	}
	

// 프로필 이미지 등록 전 메서드	
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
					
					dto.setProfile(sysName);
				}
			}
		}
		service.sign(dto);
		
		// 파일 업로드와 암호화 처리는 controller에는 어울리지않는다. 하지만 어디에 넣던 ㄱㅊ. Service나 Controller
		
		System.out.println("회원가입 성공");
		return "redirect:/";
	}
	
	
	@RequestMapping("logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
	
	
//	@RequestMapping("mypage")
//	public String mypage(Model model) throws Exception {
//		String loginID = (String) session.getAttribute("loginID");
//		MemberDTO dto = service.selectById(loginID);
//		model.addAttribute("list", dto);
//		System.out.println("마이페이지 출력 성공");
//
//		System.out.println(dto.getName());
//		return "member/mypageView";
//	}
//	
//	@RequestMapping("modifyPage")
//	public String modifyPage(Model model) throws Exception {
//		String loginID = (String) session.getAttribute("loginID");
//		MemberDTO dto = service.selectById(loginID);
//		model.addAttribute("list", dto);
//		return "member/mypageModify";
//	}
//	
//	@RequestMapping(value = "modify", method = RequestMethod.POST)
//	public String modify(MemberDTO dto, Model model) throws Exception {
//		service.modify(dto);
//		return "redirect:/member/mypage";
//	}
	
	
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String delete() throws Exception {
		String loginID = (String) session.getAttribute("loginID");
		service.delete(loginID);
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
