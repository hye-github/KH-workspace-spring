package kh.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kh.spring.dao.BoardDAO;
import kh.spring.dto.BoardDTO;



@Controller
@RequestMapping("/board/")
public class BoardController {

	@Autowired
	private BoardDAO dao;
	
	@Autowired
	private HttpSession session;

	
	@RequestMapping("list")
	public String list(Model model) throws Exception {
		List<BoardDTO>list = dao.selectAll();
		model.addAttribute("list", list);
		return "board/list";
	}

	@RequestMapping("toWrite")
	public String toWrite() {
		return "board/write";
	}
	
	
	
	
	
	@RequestMapping("detail")
	public String detail() {
		return "board/detail";
	}

	@RequestMapping(value = "write", method = RequestMethod.POST)
	public String write() {
		return "board/write";
	}
	
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public String modify() {
		return "board/modify";
	}
	
	@ExceptionHandler(Exception.class) // 모든 예외의 조상, 모든 예외 처리
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}
}
