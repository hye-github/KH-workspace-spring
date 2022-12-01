package kh.spring.board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kh.spring.dao.MemberDAO;
import kh.spring.dto.MemberDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml", // @Autowired 하려면 넣어야함
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class MemberDAOTest {
// 스프링은 필요있는데 웹이 필요 없음
	
	@Autowired
	private MemberDAO dao;
	
	@Test
	public void insertTest() throws Exception {
		dao.sign(new MemberDTO("id","pw","name","phone","email",null,
				"address1","address2",null,null),null);
	}
	
}
