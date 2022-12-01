package kh.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kh.spring.dao.BoardDAO;
import kh.spring.dao.CommentDAO;
import kh.spring.dao.FileDAO;
import kh.spring.dto.BoardDTO;
import kh.spring.dto.CommentDTO;
import kh.spring.dto.FileDTO;



@Controller
@RequestMapping("/board/")
public class BoardController {

	@Autowired
	private BoardDAO dao;

	@Autowired
	private CommentDAO daoCm;

	@Autowired
	private FileDAO daoFl;
	
	
	@Autowired
	private HttpSession session;

	
	@RequestMapping("list")
	public String list(BoardDTO dto, Model model) throws Exception {
		List<BoardDTO> list = dao.selectAll();
		for(BoardDTO dto2 : list) {
		dto2.setCommentNum(daoCm.getCommentsCount(dto2.getSeq()));
		}
		model.addAttribute("list", list);
		model.addAttribute("dto", dto);
		return "board/list";
	}

	@RequestMapping("toWrite")
	public String toWrite() {
		return "board/write";
	}

//	@RequestMapping(value = "write", method = RequestMethod.POST)
//	public String write(BoardDTO dto) throws Exception {
//		int nextVal = dao.getNextVal();
//		dao.insert(nextVal, dto);
//		return "redirect:/board/list";
//	}
	
	@RequestMapping("detail")
	public String detail(int seq, Model model) throws Exception {
		BoardDTO dto =  dao.selectBySeq(seq);
		model.addAttribute("dto", dto);
		
		List<CommentDTO> list = daoCm.selectCommentBySeq(seq);
		model.addAttribute("list", list);
		
		List<FileDTO> filelist = daoFl.selectBySeq(seq);
		model.addAttribute("filelist", filelist);
		
		dao.addViewCount(seq);
		return "board/detail";
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String delete(int seq) throws Exception {
		String loginID = (String) session.getAttribute("loginID");
		dao.delete(seq, loginID);
		daoCm.deleteCommentByParentSeq(seq);
		daoFl.deleteFilesByParentSeq(seq);
		return "redirect:/board/list";
	}
	
	@RequestMapping("toModify")
	public String toModify(BoardDTO dto, int seq, Model model) throws Exception {
		dao.selectBySeq(seq);
		model.addAttribute("list", dto);
		return "board/modify";
	}
	
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public String modify(BoardDTO dto, int seq, Model model) throws Exception {
		dao.modifyBySeq(dto);
		return "redirect:/board/detail?seq=" + seq;
	}
	
	@ExceptionHandler(Exception.class) // 모든 예외의 조상, 모든 예외 처리
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}
}
