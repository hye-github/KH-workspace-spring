package kh.spring.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
@WebAppConfiguration // 톰캣 서버처럼 동작
public class HomeControllerTest {

	@Autowired
	private WebApplicationContext wac;
	// 테스트 코드 작성에 사용될 SpringContainer 인스턴스 DI

	private MockMvc mockMvc;
	// Annotation 설정에 의해 만들어진 가상 Tomcat + Spring 환경에 request / reponse 작업을 수행할 수 있는 기능을 포함
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	
	@Test
	public void homeTest() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		// perform : 수행해라
	}


	@Test
	public void insertTest() throws Exception {
		this.mockMvc.perform(
				MockMvcRequestBuilders.post("/insert")
				.param("writer", "Tom")
				.param("message", "JUnipractice")
		).andDo(
				MockMvcResultHandlers.print()
		).andExpect(
				MockMvcResultMatchers.status().is3xxRedirection()
				);
	}
	
//	@Test
//	public void selectTest() {
//	}
	
}
