package kh.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kh.spring.dto.NetflixDTO;

@Component
public class NetflixDAO {

	@Autowired
	private DataSource ds;
	
	public int insert(NetflixDTO dto) throws Exception {
		String sql = "insert into netflix values(netflix_seq.nextval, ?, ?, sysdate)";
		try(Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);){
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getGenre());
			return ps.executeUpdate();
		}
	}
	
}
