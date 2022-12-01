package kh.spring.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		System.out.println("Home 테스트");
		return "home";
	}
	
	@RequestMapping("insert")
	public String insert(String writer, String message) {
		System.out.println(writer + " : " + message);
		return "redirect:/";
	}
	
}
