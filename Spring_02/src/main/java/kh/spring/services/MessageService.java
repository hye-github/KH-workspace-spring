package kh.spring.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kh.spring.dao.MessageDAO;
import kh.spring.dto.MessageDTO;


// 게시판에 글쓰기 기능은
// Controller 관점에선 Service 글쓰기를 사용
// Service 관점에선 BoardDAO의 insert + FileDAO의 insert 기능을 사용




@Service
public class MessageService {

	@Autowired
	private MessageDAO dao;

	@Transactional
	public int insert(MessageDTO dto) {
		
		dao.insert(dto); // 게시판에 글쓰기 예제
		dto.setMessage(null); // 예외 발생 // 트렌젝션때문에 둘다 실행 안됨
		dao.insert(dto); // 첨부 파일 예제
		
		// 기능이름 작성(마음대로 이름 작성 가능), 넣을 값(파라미터) 그대로 입력
		// 분업 가능. id만 알고서 개발자 코드 작성 가능.
		
		System.out.println(dto.getSeq());
		// return dto.getSeq(); // 파일 등에서 쓸 수 있도록 리턴 // 예외는 예외타고 나오지 리턴타고 나오는게 아니다.
			return 0;
	
	}

	public MessageDTO selectBySeq(int seq) throws Exception {
		return dao.selectBySeq(seq);
	}

	public int delete(int seq) {
		return dao.delete(seq);
	}
	
	public List<MessageDTO> selectAll() throws Exception { // List 를 select 할 경우 query
		return dao.selectAll();
	}

	public List<MessageDTO> selectByCon(String condition, String keyword) throws Exception { // List 를 select 할 경우 query
		Map<String, String> param = new HashMap<>();
		param.put("condition", condition);
		param.put("keyword", keyword);
		// {"condition":condition,"keyword":keyword}
		return dao.selectByCon(param);
	}

	public List<MessageDTO> selectByMultiCon(String writer, String message) {
		MessageDTO dto = new MessageDTO(0, writer, message);
		return dao.selectByMultiCon(dto);
	}

}
