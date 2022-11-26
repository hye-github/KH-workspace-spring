package kh.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller // 어노테이션 문법
public class HomeController {
	
	// 서블릿에 대한 상속이 없음
	// Spring 의 철학 중 한가지 : POJO 
	// Spring 의 전신 = EJB : 라이브러리가 무거워서 성능 문제 발생, 최적화가 되어있지않음, 문법적으로 진입 장벽이 높음
	// Spring : 경량화, POJO - Plain Old Java Object(평범하고 오래되고 자바의 기본 객체) 여러운걸 배제하고 편하게 한 것
	// POJO를 지원하는 어노테이션 문법
	// 
	
	
	@RequestMapping("/") // 어노테이션 문법
	public String home() {
		return "home";
	}
	
	// HomeController는 메인화면으로 돌아가기 위함
	
	
//	@RequestMapping("/hello")
//	public String hello() {
//		System.out.println("스프링에 오신걸 환영합니다.");
//		return "home";
//	}
	
//	@RequestMapping("param")
//	public String param(HttpServletRequest request) {
//		String writer = request.getParameter("writer");
//		String message = request.getParameter("message");
//		System.out.println("인자값 동작 확인 " + writer + " : " + message);
//		return "home";
//	}
	
//	@RequestMapping("param")
//	public String param(String writer, String message) { // jsp의 name 속성과 변수 이름이 같아야한다. request 안해도 된다.
//		System.out.println("인자값 동작 확인 " + writer + " : " + message);
//		return "home";
//	}
	
	
}
