package kh.spring.board;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
@WebAppConfiguration
public class HomeControllerTest {

	private Logger logger = LoggerFactory.getLogger(HomeControllerTest.class);
	// Logger 는 기록 남기는 애. 설정 자유롭게 가능.
	
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		System.out.println("MockMVC 세팅 완료");
		logger.info("MockMVC 세팅 완료");
	}
	
	
	@Test
	public void mypageTest() throws Exception {
		
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("loginID", "asd");
		
		try {
			this.mockMvc.perform(
					MockMvcRequestBuilders.get("/member/mypage")
					.session(session)
			).andDo(
					MockMvcResultHandlers.print()
			).andExpect(
					MockMvcResultMatchers.status().isOk()
			).andExpect(
					MockMvcResultMatchers.model().attributeExists("list")
			);
			System.out.println("MockMVC 테스트 성공");
			logger.info("테스트 성공");
		
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("테스트 실패");
		}
	}
	
	

	
//	@Test
//	public void insertTest() throws Exception {
//		this.mockMvc.perform(
//				MockMvcRequestBuilders.post("/member/sign")
//				.param("id", "asd")
//				.param("pw", "123")
//				.param("name", "Tom")
//				.param("phone", "01012341234")
//				.param("email", "a@a.com")
//				.param("zipcode", "123")
//				.param("address1", "서울시")
//				.param("address2", "어쩌구")
//				.param("profile", "null")
//		).andDo(
//				MockMvcResultHandlers.print()
//		).andExpect(
//				MockMvcResultMatchers.status().is3xxRedirection()
//				);
//	}

	
}
