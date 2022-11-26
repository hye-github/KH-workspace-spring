package kh.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.spring.dao.NetflixDAO;
import kh.spring.dto.NetflixDTO;

@Controller
@RequestMapping("/netflix/")
public class NetflixController {
	
	@Autowired
	private NetflixDAO dao;
	
	@RequestMapping("toInput")
	public String toInput() {
		return "netflix/newmovie";
	}
	
	@RequestMapping("toOutput")
	public String toOutput() {
		return "home";
	}
	
	@RequestMapping("insert")
	public String param(NetflixDTO dto) {
		try {
			dao.insert(dto);
			System.out.println("입력확인 : " + dto.getTitle() + " : " + dto.getGenre());
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "redirect:/";
	}
	
	@RequestMapping("getList")
	public String selectAll() {
		return "/";
	}
	
	
	
}
