package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.User;

@Controller
public class SignController {
	
	@RequestMapping("/")
	public String root(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginId")==null) {
			return "index";
		}else {
			return "welcome";
		}
	}
	
	@RequestMapping("/signIn")
	public String signIn(
			@RequestParam("inputId") String inputId
			,@RequestParam("inputPw") String inputPw
			,Model model
			,HttpServletRequest request) {
		
		User user = new User("aaaa", "0000");
		
		if(inputId.equals(user.getId()) && inputPw.equals(user.getPw())){
			HttpSession session = request.getSession();
			session.setAttribute("loginId",user.getId());
			session.setMaxInactiveInterval(60*30); 
			return "welcome";
		}else{
			String message="";
			message=(inputId.equals("") || inputPw.equals(""))? "아이디와 비밀번호를 입력하세요." :"아이디와 비밀번호가 일치하지 않습니다."; 
			model.addAttribute("message", message);
			return "index";
		}
	}
	
	@RequestMapping("/signOut")
	public String signOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}
	

}
