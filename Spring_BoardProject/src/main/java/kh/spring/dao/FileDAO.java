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

import kh.spring.dto.FileDTO;

@Component
public class FileDAO {
	
	@Autowired
	private JdbcTemplate jdbc;
	


	public int insert(FileDTO dto) throws Exception {
		String sql = "insert into files values(files_seq.nextval,?,?,?)";
		return jdbc.update(sql, dto.getOriName(), dto.getSysName(), dto.getParent_seq());
	}

	public List<FileDTO> selectBySeq(int seq) throws Exception {
		String sql = "select * from files where parent_seq= ?";
		return jdbc.query(sql, new RowMapper<FileDTO>() {
			@Override
			public FileDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				FileDTO dto = new FileDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setOriName(rs.getString("oriname"));
				dto.setSysName(rs.getString("sysname"));
				dto.setParent_seq(rs.getInt("parent_seq"));
				return dto;
			}
		});
	}

	public int deleteFilesByParentSeq(int parent_seq) throws Exception {
		String sql = "delete from files where parent_seq= ?";
		return jdbc.update(sql, parent_seq);
	}
	
	
	
//	@Autowired
//	private DataSource dataSource;
//	
//	public int insert(FileDTO dto) throws Exception {
//		String sql = "insert into files values(files_seq.nextval,?,?,?)";
//		try (Connection con = dataSource.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
//			pstat.setString(1, dto.getOriName());
//			pstat.setString(2, dto.getSysName());
//			pstat.setInt(3, dto.getParent_seq());
//			return pstat.executeUpdate();
//		}
//	}
//
//	public List<FileDTO> selectBySeq(int seq) throws Exception {
//		String sql = "select * from files where parent_seq= ?";
//		try (Connection con = dataSource.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
//			pstat.setInt(1, seq);
//			try (ResultSet rs = pstat.executeQuery();) {
//				List<FileDTO> list = new ArrayList<>();
//				while (rs.next()) {
//					FileDTO dto = new FileDTO();
//					dto.setSeq(rs.getInt("seq"));
//					dto.setOriName(rs.getString("oriname"));
//					dto.setSysName(rs.getString("sysname"));
//					dto.setParent_seq(rs.getInt("parent_seq"));
//					list.add(dto);
//				}
//				return list;
//			}
//		}
//	}
//
//	public int deleteFilesByParentSeq(int parent_seq) throws Exception {
//		String sql = "delete from files where parent_seq= ?";
//		try (Connection con = dataSource.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
//			pstat.setInt(1, parent_seq);
//			return pstat.executeUpdate();
//		}
//	}
	
	
}
