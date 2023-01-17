package kh.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kh.spring.dto.MessageDTO;


@Component
public class MessageDAO {
	
	@Autowired
	private SqlSession db;
	
	public int insert(MessageDTO dto) throws Exception {
		return db.insert("Message.insert", dto);
	}
	
	public List<MessageDTO> select() throws Exception {
		return db.selectList("Message.select");
	}
	
}
