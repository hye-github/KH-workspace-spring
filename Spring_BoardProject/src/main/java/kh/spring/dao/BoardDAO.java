package kh.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import kh.spring.dto.BoardDTO;

@Component
public class BoardDAO {

	@Autowired
	private JdbcTemplate jdbc;

	public List<BoardDTO> selectAll() throws Exception {
		String sql = "select * from board order by 1 desc";
		return jdbc.query(sql, new RowMapper<BoardDTO>() {
			@Override
			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				// next가 되었다고 생각하고 진행
				BoardDTO dto = new BoardDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setWrite_date(rs.getTimestamp("write_date"));
				dto.setView_count(rs.getInt("view_count"));
				return dto;
			}
		});
	}

	public int insert(int nextVal, BoardDTO dto) throws Exception {
		String sql = "insert into board values(" + nextVal + ",?,?,?,sysdate,0)";
		return jdbc.update(sql, dto.getWriter(), dto.getTitle(), dto.getContents());
	}

	public int getNextVal() throws Exception{
		String sql = "select board_seq.nextval from dual";
		// board 테이블 대상으로 select 하게 되면 nextval 이라는건 반복이라 반복문이 되게 된다. 우리가 원하는건 단 한번.
		return jdbc.queryForObject(sql, Integer.class);
			// https://aricode.tistory.com/10
	}

	public BoardDTO selectBySeq(int seq) throws Exception {
		String sql = "select * from board where seq= ?";
		return (BoardDTO) jdbc.queryForObject(sql, new RowMapper<BoardDTO>() {
			@Override
			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoardDTO dto = new BoardDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setWrite_date(rs.getTimestamp("write_date"));
				dto.setView_count(rs.getInt("view_count"));
				return dto;
			}
		}, seq);
	}

	public int delete(int seq, String writer) throws Exception {
		String sql = "delete from board where seq= ? and writer= ?";
		return jdbc.update(sql, seq, writer);
	}

	public int modifyBySeq(BoardDTO dto) throws Exception {
		String sql = "update board set title=?, contents=? where seq=?";
		return jdbc.update(sql, dto.getTitle(), dto.getContents(), dto.getSeq());
	}

	public int addViewCount(int seq) throws Exception {
		String sql = "update board set view_count = view_count + 1 where seq=?";
		return jdbc.update(sql, seq);
	}




//	@Autowired
//	private DataSource ds;
//
//
//	public List<BoardDTO> selectAll() throws Exception {
//		String sql = "select * from board order by 1 desc";
//		try (Connection con = ds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);
//				ResultSet rs = pstat.executeQuery();) {
//			List<BoardDTO> list = new ArrayList<>();
//			while (rs.next()) {
//				BoardDTO dto = new BoardDTO();
//				dto.setSeq(rs.getInt("seq"));
//				dto.setWriter(rs.getString("writer"));
//				dto.setTitle(rs.getString("title"));
//				dto.setContents(rs.getString("contents"));
//				dto.setWrite_date(rs.getTimestamp("write_date"));
//				dto.setView_count(rs.getInt("view_count"));
//				list.add(dto);
//			}
//			return list;
//		}
//	}
//
//	public int insert(int nextVal, BoardDTO dto) throws Exception {
//		String sql = "insert into board values(" + nextVal + ",?,?,?,sysdate,0)";
//		try (Connection con = ds.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
//			pstat.setString(1, dto.getWriter());
//			pstat.setString(2, dto.getTitle());
//			pstat.setString(3, dto.getContents());
//			return pstat.executeUpdate();
//		}
//	}
//
//	public int getNextVal() throws Exception{
//		String sql = "select board_seq.nextval from dual";
//		// board 테이블 대상으로 select 하게 되면 nextval 이라는건 반복이라 반복문이 되게 된다. 우리가 원하는건 단 한번.
//
//		try(Connection con = ds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);
//				ResultSet rs = pstat.executeQuery()){
//			rs.next();
//
//			return rs.getInt(1);
//			// https://aricode.tistory.com/10
//		}
//
//	}
//
//	public BoardDTO selectBySeq(int seq) throws Exception {
//		String sql = "select * from board where seq= ?";
//
//		try (Connection con = ds.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
//
//			pstat.setInt(1, seq);
//
//			try (ResultSet rs = pstat.executeQuery();) {
//
//				rs.next();
//
//				BoardDTO dto = new BoardDTO();
//
//				dto.setSeq(rs.getInt("seq"));
//				dto.setWriter(rs.getString("writer"));
//				dto.setTitle(rs.getString("title"));
//				dto.setContents(rs.getString("contents"));
//				dto.setWrite_date(rs.getTimestamp("write_date"));
//				dto.setView_count(rs.getInt("view_count"));
//
//				return dto;
//			}
//		}
//	}
//
//	public int delete(int seq, String writer) throws Exception {
//
//		String sql = "delete from board where seq= ? and writer= ?";
//
//		try (Connection con = ds.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
//
//			pstat.setInt(1, seq);
//			pstat.setString(2, writer);
//
//			return pstat.executeUpdate();
//		}
//	}
//
//	public int modifyBySeq(BoardDTO dto) throws Exception {
//		String sql = "update board set title=?, contents=? where seq=?";
//
//		try (Connection con = ds.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
//
//			pstat.setString(1, dto.getTitle());
//			pstat.setString(2, dto.getContents());
//			pstat.setInt(3, dto.getSeq());
//
//			return pstat.executeUpdate();
//
//		}
//	}
//
//	public int addViewCount(int seq) throws Exception {
//		String sql = "update board set view_count = view_count + 1 where seq=?";
//
//		try (Connection con = ds.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
//
//			pstat.setInt(1, seq);
//			return pstat.executeUpdate();
//
//		}
//	}

	
}
