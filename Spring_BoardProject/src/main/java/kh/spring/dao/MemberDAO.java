package kh.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kh.spring.dto.MemberDTO;

@Component
public class MemberDAO {

	@Autowired
	private DataSource ds;

	public boolean idCheck(String id) throws Exception {
		String sql = "select * from members where id = ?";
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, id);
			try (ResultSet rs = ps.executeQuery();) {
				return rs.next();
			}
		}
	}

	public int sign(MemberDTO dto) throws Exception {
		String sql = "insert into members values(?,?,?,?,?,?,?,?,sysdate)";
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPw());
			ps.setString(3, dto.getName());
			ps.setString(4, dto.getPhone());
			ps.setString(5, dto.getEmail());
			ps.setString(6, dto.getZipcode());
			ps.setString(7, dto.getAddress1());
			ps.setString(8, dto.getAddress2());
			return ps.executeUpdate();
		}
	}

	public boolean isLoginOk(String id, String pw) throws Exception {
		String sql = "select * from members where id = ? and pw = ?";
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, id);
			ps.setString(2, pw);
			try (ResultSet rs = ps.executeQuery();) {
				return rs.next();
			}
		}
	}

	public int modify(MemberDTO dto) throws Exception {
		String sql = "update members set pw=?, name=?, phone=?, email=?, zipcode=?, address1=?, address2=? where id=?";
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, dto.getPw());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getPhone());
			ps.setString(4, dto.getEmail());
			ps.setString(5, dto.getZipcode());
			ps.setString(6, dto.getAddress1());
			ps.setString(7, dto.getAddress2());
			ps.setString(8, dto.getId());
			return ps.executeUpdate();
		}
	}

	public int delete(String id) throws Exception {
		String sql = "delete from members where id= ?";
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, id);
			return ps.executeUpdate();
		}
	}

	public MemberDTO selectById(String id) throws Exception {
		String sql = "select * from members where id=?";
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, id);
			try (ResultSet rs = ps.executeQuery();) {
				rs.next();
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setZipcode(rs.getString("zipcode"));
				dto.setAddress1(rs.getString("address1"));
				dto.setAddress2(rs.getString("address2"));
				dto.setSignup_date(rs.getTimestamp("signup_date"));
				return dto;
			}
		}
	}

}
