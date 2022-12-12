package kh.spring.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private HttpSession session;
	
	
	@RequestMapping("/")
	public String home(HttpServletRequest request, String id) {
		session.setAttribute("IP", request.getRemoteAddr());
		session.setAttribute("loginID", id);
		
		return "home";
	}
	
}
