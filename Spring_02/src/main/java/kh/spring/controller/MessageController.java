package kh.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.spring.dao.MessageDAO;
import kh.spring.dto.MessageDTO;

@Controller
@RequestMapping("/message/") // 이름 지표가 되어줌. 미세한 차이지만 성능 저하를 줄여준다.
public class MessageController {

	@Autowired
	private MessageDAO dao;
	
	
	
	@RequestMapping("toInput")
	public String toInput() {
		return "message/inputForm"; // 뷰리졸버가 경로+jsp를 알아서 붙일꺼임 // 그 위치로 포워딩됨
	}
	
	@RequestMapping("toOutput")
	public String toOutput() {
		return "message/outputForm";
	}
	
	
	@RequestMapping("insert")
	public String param(MessageDTO dto) { // jsp의 name 속성과 DTO 내의 이름이 같아야한다. request 안해도 된다.
		try {
			int result = dao.insert(dto);
			System.out.println("결과 : " + result); // 성공은 숫자 1
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "redirect:/";
		// return "home"; 이면 기본 forward라 insert 계속 됨. 모든 리퀘스트를 털고
		// 리다이렉트가 되어야함
		// 포워드는 서버가 페이지를 바꿔치기
		// 리다이렉트는 서버가 클라이언트에게 페이지를 바꾸라고 명령하는 것
		// redirect: 가 붙으면 뷰리졸버가 동작하지않음
		// return "redirect:home"; 이렇게 하면 처리해줄 @RequestMapping가 없음
		// return "redirect:/"; 이렇게 가야 @RequestMapping("/")으로 가면 정보 털고 home에서 시작
	}
	
	@RequestMapping("delete")
	public String param(int seq) { // jsp의 name 속성과 DTO 내의 이름이 같아야한다. request 안해도 된다.
		try {
			dao.delete(seq);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "/message/outputForm";
	}
	
	@RequestMapping("getList")
	public String selectAll(Model model) {
		try {
			List<MessageDTO> list = dao.selectAll();
			int count = dao.count();
			model.addAttribute("list", list);
			model.addAttribute("count", count);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "/message/list";
	}
	
	
}
