package kh.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kh.spring.dto.MessageDTO;

@Component
public class MessageDAO {

	@Autowired // 자료형을 확인해서 Spring 컨테이너한테 자료 요청을 함. 데이터소스는 베이직데이터소스를 상속 받아서 가능. / 의존성 주입이 자동으로 된다.
	private DataSource ds; // 여기에 new 안함. Spring은 new 안쓰기 위해서
	// DL or DI > 멤버필드에 DL은 좋은 생각이 아님.
	
	public int insert(MessageDTO dto) throws Exception{
		
		String sql = "insert into kh_message values(kh_message_seq.nextval, ?, ?)";
		
		try (Connection con = ds.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, dto.getWriter());
			pstat.setString(2, dto.getMessage());
			return pstat.executeUpdate(); 
			// commit; 빼먹은 이유 : ojdbc 8버전이라 ㄱㅊ
		}
	}
	
	public int delete(int seq) throws Exception{
		String sql = "delete from kh_message where seq = ?";
		try (Connection con = ds.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, seq);
			return pstat.executeUpdate();
		}
	}
	
	public List<MessageDTO> selectAll() throws Exception {
		String sql = "select * from kh_message order by 1";
		try (Connection con = ds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();) {
				
			List<MessageDTO> list = new ArrayList<>();
			
			while(rs.next()) {
				MessageDTO dto = new MessageDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setWriter(rs.getString("writer"));
				dto.setMessage(rs.getString("message"));
				list.add(dto);
			}
			return list;
		}
	}
	
}
