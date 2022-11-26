package kh.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kh.spring.dto.BoardDTO;

@Component
public class BoardDAO {

	@Autowired
	private DataSource ds;
	
	
	public List<BoardDTO> selectAll() throws Exception {
		String sql = "select * from board order by 1 desc";
		try (Connection con = ds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();) {
			List<BoardDTO> list = new ArrayList<>();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setWrite_date(rs.getTimestamp("write_date"));
				dto.setView_count(rs.getInt("view_count"));
				list.add(dto);
			}
			return list;
		}
	}
	
	
	public int insert(BoardDTO dto) throws Exception {

		String sql = "insert into board values(?,?,?,?,sysdate,0)";
		//String sql = "insert into board values(board_seq.nextval,?,?,?,sysdate,0)";
		try (Connection con = ds.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			
			pstat.setInt(1, dto.getSeq());
			pstat.setString(2, dto.getWriter());
			pstat.setString(3, dto.getTitle());
			pstat.setString(4, dto.getContents());

			int result = pstat.executeUpdate();

			con.commit(); // 높은 버전의 ojbc 사용할 때 commit 하면 error 난다. 조심

			return result;
		}
	}

	public int getNextVal() throws Exception{
		String sql = "select board_seq.nextval from dual";
		// board 테이블 대상으로 select 하게 되면 nextval 이라는건 반복이라 반복문이 되게 된다. 우리가 원하는건 단 한번.
		
		try(Connection con = ds.getConnection(); 
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery()){
			rs.next();
			
			return rs.getInt(1);
			// https://aricode.tistory.com/10
		}
		
	}
	

	
	
	
	public BoardDTO selectBySeq(int seq) throws Exception {
		String sql = "select * from board where seq= ?";

		try (Connection con = ds.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setInt(1, seq);

			try (ResultSet rs = pstat.executeQuery();) {

				rs.next();

				BoardDTO dto = new BoardDTO();

				dto.setSeq(rs.getInt("seq"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setWrite_date(rs.getTimestamp("write_date"));
				dto.setView_count(rs.getInt("view_count"));

				return dto;
			}
		}
	}

	public int delete(int seq) throws Exception {

		String sql = "delete from board where seq= ?";

		try (Connection con = ds.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setInt(1, seq);

			int result = pstat.executeUpdate();
			con.commit();

			return result;
		}
	}

	public int modifyBySeq(String title, String contents, int seq) throws Exception {
		String sql = "update board set title=?, contents=? where seq=?";

		try (Connection con = ds.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setString(1, title);
			pstat.setString(2, contents);
			pstat.setInt(3, seq);

			int result = pstat.executeUpdate();
			con.commit();

			return result;

		}
	}

	public int addViewCount(int seq) throws Exception {
		String sql = "update board set view_count = view_count + 1 where seq=?";

		try (Connection con = ds.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setInt(1, seq);
			int result = pstat.executeUpdate();
			con.commit();

			return result;

		}
	}
	
	
}
