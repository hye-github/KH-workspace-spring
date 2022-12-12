package kh.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@Autowired
	private MyUtil util;

	@RequestMapping(value = "/")
	public String home() {
		return "home";
	}

	@RequestMapping("process")
	public String inputProc() {
		util.testingFunc();
		return "home";
	}

}
