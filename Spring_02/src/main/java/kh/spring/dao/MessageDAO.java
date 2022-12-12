package kh.spring.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kh.spring.dto.MessageDTO;

@Component
public class MessageDAO {
	
	// MyBatis 코드
	
	@Autowired
	private SqlSession db;
	
	public int insert(MessageDTO dto) {
		return db.insert("Message.insert", dto); // 파일 등에서 쓸 수 있도록 리턴 // 예외는 예외타고 나오지 리턴타고 나오는게 아니다.
	}
	
	public List<MessageDTO> selectAll() throws Exception { // List 를 select 할 경우 query
		return db.selectList("Message.selectAll");
	}
	
	public int delete(int seq){
		return db.delete("Message.delete", seq);
	}
	
	public MessageDTO selectBySeq(int seq) throws Exception {
		return db.selectOne("Message.selectBySeq", seq);
	}
	
	public List<MessageDTO> selectByCon(Map<String, String> param) throws Exception { // List 를 select 할 경우 query
		return db.selectList("Message.selectByCon", param);
	}

	public List<MessageDTO> selectByMultiCon(MessageDTO dto) {
		return db.selectList("Message.selectByMultiCon", dto);
	}
	
	
	
	
//	public List<MessageDTO> selectByMultiCon(String writer, String message) {
//		Map<String, String> param = new HashMap<>();
//		param.put("writer", writer);
//		param.put("message", message);
//		return db.selectList("Message.selectByMultiCon", param);
//	}
	
	
	

	
	// 스프링 Jdbc
	
//	@Autowired
//	private JdbcTemplate jdbc;
//
//	public int insert(MessageDTO dto){
//		String sql =  "insert into kh_message values(kh_message_seq.nextval, ?, ?)";
//		return jdbc.update(sql, dto.getWriter(), dto.getMessage());
//	}
//
//	public int delete(int seq){
//		String sql =  "delete from kh_message where seq = ?";
//		return jdbc.update(sql, seq);
//	}
//
//	public int update(MessageDTO dto){
//		String sql =  "update kh_message set writer=?, message=? where seq = ?";
//		return jdbc.update(sql, dto.getWriter(), dto.getMessage(), dto.getSeq());
//	}
//	
//	
//	public List<MessageDTO> selectAll() throws Exception { // List 를 select 할 경우 query
//		String sql = "select * from kh_message order by 1";
//		return jdbc.query(sql, new RowMapper<MessageDTO>() {
//
//			@Override
//			public MessageDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//				// next가 되었다고 생각하고 진행
//				
//				MessageDTO dto = new MessageDTO();
//				dto.setSeq(rs.getInt("seq"));
//				dto.setWriter(rs.getString("writer"));
//				dto.setMessage(rs.getString("message"));
//				return dto;
//			}
//			
//		});
//		
//		// 추상클래스는 new 못한다. 그러나 new RowMapper() 한다.
//		// query 메서드는 두번째 인자값으로 콜백을 원한다.
//		// 메서드를 오버라이딩한 인스턴스를 넘겨준다.
//	}
//	
//	
//	// 단 한개의 DTO 또는 int 값 등.. 을 select 할 경우 queryForObject
//	public MessageDTO selectBySeq(int seq) {
//		String sql = "select * from kh_message where seq = ?";
//		return (MessageDTO) jdbc.queryForObject(sql, new RowMapper<MessageDTO>() {
//				@Override
//				public MessageDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//					// next가 되었다고 생각하고 진행
//					
//					MessageDTO dto = new MessageDTO();
//					dto.setSeq(rs.getInt("seq"));
//					dto.setWriter(rs.getString("writer"));
//					dto.setMessage(rs.getString("message"));
//					return dto;
//				}
//			}, seq);
//	}
//	
//	public int count() {
//		String sql = "select count(*) from kh_message";
//		return jdbc.queryForObject(sql, Integer.class);
//	}
	
	
	// 기본 Jdbc
	

//	@Autowired // 자료형을 확인해서 Spring 컨테이너한테 자료 요청을 함. 데이터소스는 베이직데이터소스를 상속 받아서 가능. / 의존성 주입이 자동으로 된다.
//	private DataSource ds; // 여기에 new 안함. Spring은 new 안쓰기 위해서
//	// DL or DI > 멤버필드에 DL은 좋은 생각이 아님.
//
//	public int insert(MessageDTO dto) throws Exception{
//
//		String sql = "insert into kh_message values(kh_message_seq.nextval, ?, ?)";
//
//		try (Connection con = ds.getConnection();
//			PreparedStatement pstat = con.prepareStatement(sql);) {
//			pstat.setString(1, dto.getWriter());
//			pstat.setString(2, dto.getMessage());
//			return pstat.executeUpdate();
//			// commit; 빼먹은 이유 : ojdbc 8버전이라 ㄱㅊ
//		}
//	}
//
//	public int delete(int seq) throws Exception{
//		String sql = "delete from kh_message where seq = ?";
//		try (Connection con = ds.getConnection();
//			PreparedStatement pstat = con.prepareStatement(sql);) {
//			pstat.setInt(1, seq);
//			return pstat.executeUpdate();
//		}
//	}
//
//	public List<MessageDTO> selectAll() throws Exception {
//		String sql = "select * from kh_message order by 1";
//		try (Connection con = ds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);
//				ResultSet rs = pstat.executeQuery();) {
//
//			List<MessageDTO> list = new ArrayList<>();
//
//			while(rs.next()) {
//				MessageDTO dto = new MessageDTO();
//				dto.setSeq(rs.getInt("seq"));
//				dto.setWriter(rs.getString("writer"));
//				dto.setMessage(rs.getString("message"));
//				list.add(dto);
//			}
//			return list;
//		}
//	}
	
}
