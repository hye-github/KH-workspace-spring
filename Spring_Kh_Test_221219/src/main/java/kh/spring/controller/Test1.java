package kh.spring.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


@Controller
public class Test1 {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}
	
	@ResponseBody
	@RequestMapping("asJson")
	public String inputProc(String data1, String data2) {
		JsonObject obj = new JsonObject();
		obj.addProperty("data1", data1);
		obj.addProperty("data2", data2);
	return new Gson().toJson(obj);
	}
	
	
	
//	@ResponseBody
//	@RequestMapping("asJson")
//	public String inputProc(String data1, String data2) {
//	return data1 + " : " + data2;
//	}
	
}
