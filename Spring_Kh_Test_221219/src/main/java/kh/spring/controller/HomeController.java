package kh.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import kh.spring.dao.MessageDAO;
import kh.spring.dto.MessageDTO;



@Controller
public class HomeController {

	@Autowired
	private MessageDAO dao;
	
	@ResponseBody
	@RequestMapping(value = "insertMessage", produces = "text/html;charset=utf8")
	public String insertMessage(MessageDTO dto) throws Exception {
		dao.insert(dto);
		return "성공";
	}
	
	@ResponseBody
	@RequestMapping(value = "listMessage", produces = "text/html;charset=utf8")
	public String select(MessageDTO dto) throws Exception {
		List<MessageDTO> list = dao.select();
			JsonObject obj = new JsonObject();
			for(int i = 0 ; i < list.size() ; i++) {
				obj.addProperty("name", dto.getName());
				obj.addProperty("message", dto.getMessage());	
			}
		return new Gson().toJson(obj);
	}
	
	@RequestMapping("error")
	public String error() {
		return "error";
	}

	@ExceptionHandler(Exception.class) 
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "redirect:error";
	}
	
}
