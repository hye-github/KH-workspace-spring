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

import kh.spring.dto.CommentDTO;

@Component
public class CommentDAO {

	@Autowired
	private JdbcTemplate jdbc;
	
	
	public int insertComment(String writer, String contents, int parent_seq) throws Exception {
		String sql = "insert into comments values(comments_seq.nextval,?,?,sysdate,?)";
		return jdbc.update(sql, writer, contents, parent_seq);
	}


	public List<CommentDTO> selectCommentBySeq(int parent_seq) throws Exception {
		String sql = "select * from comments where parent_seq= ? order by seq asc";
		return jdbc.query(sql, new RowMapper<CommentDTO>() {
			@Override
			public CommentDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				CommentDTO dto = new CommentDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setWriter(rs.getString("writer"));
				dto.setContents(rs.getString("contents"));
				dto.setWrite_date(rs.getTimestamp("write_date"));
				dto.setParent_seq(rs.getInt("parent_seq"));
				return dto;
			}
		});
	}
	
	public int deleteComment(int seq) throws Exception {
		String sql = "delete from comments where seq= ?";
		return jdbc.update(sql, seq);
	}

	public int deleteCommentByParentSeq(int parent_seq) throws Exception {
		String sql = "delete from comments where parent_seq= ?";
		return jdbc.update(sql, parent_seq);
	}

	public int modifyCommentBySeq(String contents, int seq) throws Exception {
		String sql = "update comments set contents=? where seq=?";
		return jdbc.update(sql, contents, seq);
	}
	
	// 댓글의 개수를 반환하는 코드 짜기
	public int getCommentsCount(int parent_seq) throws Exception {
		String sql = "select count(*) from comments where parent_seq=?";
		return jdbc.queryForObject(sql, Integer.class, parent_seq);
	}
	
	
	
	
	
	
	
	
	
//	@Autowired
//	private DataSource ds;
//	
//	
//	public int insertComment(String writer, String contents, int parent_seq) throws Exception {
//
//		String sql = "insert into comments values(comments_seq.nextval,?,?,sysdate,?)";
//
//		try (Connection con = ds.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
//
//			pstat.setString(1, writer);
//			pstat.setString(2, contents);
//			pstat.setInt(3, parent_seq);
//			
//			return pstat.executeUpdate();
//		}
//	}
//
//
//	public List<CommentDTO> selectCommentBySeq(int parent_seq) throws Exception {
//		String sql = "select * from comments where parent_seq= ? order by seq asc";
//
//		try (Connection con = ds.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
//
//			pstat.setInt(1, parent_seq);
//
//			try (ResultSet rs = pstat.executeQuery();) {
//
//				List<CommentDTO> list = new ArrayList<>();
//				
//				while (rs.next()) {
//					CommentDTO dto = new CommentDTO();
//					
//					dto.setSeq(rs.getInt("seq"));
//					dto.setWriter(rs.getString("writer"));
//					dto.setContents(rs.getString("contents"));
//					dto.setWrite_date(rs.getTimestamp("write_date"));
//					dto.setParent_seq(rs.getInt("parent_seq"));
//					list.add(dto);
//				}
//	
//				return list;
//			}
//		}
//	}
//	
//	
//
//	public int deleteComment(int seq) throws Exception {
//
//		String sql = "delete from comments where seq= ?";
//
//		try (Connection con = ds.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
//
//			pstat.setInt(1, seq);
//
//			return pstat.executeUpdate();
//		}
//	}
//
//	public int deleteCommentByParentSeq(int parent_seq) throws Exception {
//
//		String sql = "delete from comments where parent_seq= ?";
//
//		try (Connection con = ds.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
//
//			pstat.setInt(1, parent_seq);
//
//			return pstat.executeUpdate();
//		}
//	}
//
//	
//	
//	public int modifyCommentBySeq(String contents, int seq) throws Exception {
//		String sql = "update comments set contents=? where seq=?";
//
//		try (Connection con = ds.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
//
//			pstat.setString(1, contents);
//			pstat.setInt(2, seq);
//
//			return pstat.executeUpdate();
//
//		}
//	}
//	
//	
//	// 댓글의 개수를 반환하는 코드 짜기
//	public int getCommentsCount(int parent_seq) throws Exception {
//		String sql = "select count(*) from comments where parent_seq=?";
//		try (Connection con = ds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);) {
//			
//			pstat.setInt(1, parent_seq);
//
//			try(ResultSet rs = pstat.executeQuery();){
//					
//			rs.next();
//			return rs.getInt(1);
//			}
//		}
//	}
	
}
