package kh.spring.test;

//package com.test.spring;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


// ▶ 요구사항3 ◀
// MVC 패턴의 Controller 단을 테스트 할 수 있도록 애노테이션을 설정하시오.
// 단, 단위 테스트를 위해서 SpringJUnit4ClassRunner라는 Runner 클래스를 사용하도록 한다.
// 그리고 애플리케이션 컨텍스트(어플리케이션 환경설정)의 설정파일위치는 기본경로파일인
// /src/main/webapp/WEB-INF/spring/root-context.xml 파일과
// /src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml 파일만을 사용하는것을 전제하도록 한다.

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@WebAppConfiguration // 톰캣 서버처럼 동작
public class MemberControllerTest {

// ▶ 요구사항4 ◀
// org.slf4j.LoggerFactory 클래스의 getLogger 메소드를 통해 org.slf4j.Logger 객체인 logger 를 생성하시오.

	private static final Logger logger = LoggerFactory.getLogger(MemberControllerTest.class);

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

// ▶ 요구사항5 ◀
// 매번 테스트를 진행할때 마다 테스트 하기전 가상의 요청과 응답을 처리하기 위한 MockMvc mockMvc 객체를 만들어 주기 위해
// @Before 애노테이션으로 setup()메소드를 생성하려고 한다.
// MockMvc mockMvc 객체를 생성하시오.

		logger.info("setup()완료~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

	@Test
	public void testMemberRegister() throws Exception {
		logger.info("===== testMemberRegister() 메소드 시작 =====");
		try {

// ▶ 요구사항6 ◀
// .perform()을 사용하여 매핑url("/beginSpring/memberRegister.action")로 request 하시오.
// 테스트시 입력되는 userid 값은 "leess", passwd 값은 "qwer1234$", name 값은 "이순신",
// email 값은 "leess@gmail.com", tel 값은 "01023456789" 으로 한다.

			this.mockMvc.perform(MockMvcRequestBuilders.post("/beginSpring/memberRegister.action")
					.param("userid", "leess").param("passwd", "qwer1234").param("name", "이순신")
					.param("email", "leess@gmail.com").param("tel", "01023456789")

// ▶ 요구사항7 ◀
// .andDo()을 사용하여 처리되어진 내용을 출력하시오.

			).andDo(MockMvcResultHandlers.print()

// ▶ 요구사항8 ◀
// .andExpect를 사용하여 상태값이 에러가 없는 정상적인 상태(status 가 200)가 되도록 검증하시오.

			).andExpect(MockMvcResultMatchers.status().isOk());

// ▶ 요구사항9 ◀
// logger 객체를 사용하여 >>> 테스트 수행 성공!!<<< 이라는 정보를 기록하시오.

			logger.info(">>> 테스트 수행 성공!!<<<");

		} catch (Exception e) {

// ▶ 요구사항10 ◀
// 테스트 수행중 오류가 발생하면 >>> 테스트 수행 실패 : 오류메시지를 남기도록 하시오.
			logger.info(">>> 테스트 수행 실패 : ");
			e.printStackTrace();
		}
	}
}