package kh.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kh.spring.dao.CommentDAO;
import kh.spring.dto.BoardDTO;

@Controller
@RequestMapping("/comment/")
public class CommentController {

	@Autowired
	private CommentDAO dao;

	@Autowired
	private HttpSession session;

	@RequestMapping(value = "write", method = RequestMethod.POST)
	public String write(BoardDTO dto, int seq, String comments, int parent_seq, Model model) throws Exception {
		String loginID = (String) session.getAttribute("loginID");
		dao.insertComment(loginID, comments, parent_seq);
		return "redirect:/board/detail?seq="+parent_seq;
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String delete(int seq, int parent_seq, Model model) throws Exception {
		dao.deleteComment(seq);
		return "redirect:/board/detail?seq="+parent_seq;
	}
	
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public String modify(String commentsAdd, int seq, int parent_seq, Model model) throws Exception {
		dao.modifyCommentBySeq(commentsAdd, seq);
		return "redirect:/board/detail?seq="+parent_seq;
	}
	
	@ExceptionHandler(Exception.class) // 모든 예외의 조상, 모든 예외 처리
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}
}