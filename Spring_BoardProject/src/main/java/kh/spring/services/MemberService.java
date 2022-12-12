package kh.spring.services;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.spring.dao.MemberDAO;
import kh.spring.dto.MemberDTO;

@Service
public class MemberService {


	@Autowired
	private MemberDAO dao;
	
	public boolean idCheck(String id) throws Exception {
		return dao.idCheck(id);
	}

	public int delete(String id) throws Exception {
		return dao.delete(id);
	}


	public int sign(MemberDTO dto) throws Exception {
		return dao.sign(dto);
	}
	
	public boolean isLoginOk(MemberDTO dto) throws Exception {
		return dao.isLoginOk(dto);
	}
	
	
	
}
