package com.board.app;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.app.user.Double_Check;
import com.board.app.user.Reset_PW;
import com.board.app.user.User_Conn;
import com.board.app.user.User_Find;
import com.board.app.user.User_Insert;

@Controller
public class Sign_Controller {
	
//	모델 사용법 및 리다이렉트 시 flash를 사용하여 데이터 넘기는 법
//	@PostMapping("/test")
//	public String sign_in_conn(Model model, @ModelAttribute("login_user") User_Info login_user) {
//		model.addAttribute("user", "데이터");
//		System.out.println("현재 유저: " + login_user.toString());
//		
//		return "test";
//	}
//
//	@PostMapping("/")
//	public String sign_in_conn(RedirectAttributes redirectAttributes) {
//		redirectAttributes.addFlashAttribute("login_user", user);
//		
//		return "redirect:/test";
//	}
	
	@RequestMapping("/sign_in")
	public String sign_in(HttpServletRequest request) {
		if(request.getSession().getAttribute("login_user") != null) {
			return "redirect:/main";
		}
		
		return "Sign_In";
	}
	
	@PostMapping("/sign_in/conn")
	public String sign_in_conn(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("에러: " + e.getMessage());
		}
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		// DB에서 id, pw 체크
		User_Info user = User_Conn.conn(user_id, user_pw);
		
		// id, pw 체크 실패 시 로그인 화면으로, 성공 시 유저를 세션에 저장하고 메인 화면으로
		if(user == null) {
			return ("redirect:/sign_in");
		}
		else {
			// 아이디 기억 기능
			HttpSession session = request.getSession();
			if (request.getParameter("rememberId") != null) {
				session.setAttribute("rememberId", user_id);
				session.setAttribute("rememberCheck", "checked");
			} else {
				session.removeAttribute("rememberId");
				session.removeAttribute("rememberCheck");
			}
			
			request.getSession().setAttribute("login_user", user);
			return ("redirect:/main");
		}
	}
	
	@GetMapping("/logout") 
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("login_user");
		
		return "redirect:/sign_in";
	}
	
	@GetMapping("/sign_in/find") 
	public String forgot() {
		
		return "User_Find";
	}
	
	@RequestMapping("/sign_in/reset")
	public String find(Model model, HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("에러: " + e.getMessage());
		}
		
		String email = request.getParameter("email");
		
		model.addAttribute("ids", User_Find.find(email));
		return "Reset_PW";
	}
	
	@RequestMapping("/sign_in/update")
	public String reset(RedirectAttributes redirectAttributes, HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("에러: " + e.getMessage());
		}
		
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		if(Reset_PW.set(user_id, user_pw)) {
			redirectAttributes.addFlashAttribute("rememberId", user_id);
		} else {
			redirectAttributes.addFlashAttribute("msg", "비밀번호 변경에 실패했습니다.");
		}
		
		return "redirect:/sign_in";
	}
	
	@RequestMapping("/sign_up") 
	public String sign_up(Model model) {
		
		return "Sign_Up";
	}
	
	@PostMapping("/sign_up/check")
	public String double_check(RedirectAttributes redirectAttributes, HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("에러: " + e.getMessage());
		}
		
		String user_id = request.getParameter("user_id");
		redirectAttributes.addFlashAttribute("user_id", user_id);
		
		if(Double_Check.check(user_id)) {
			redirectAttributes.addFlashAttribute("check_double", "사용 불가한 아이디입니다.");
			redirectAttributes.addFlashAttribute("dbl_check_style", "color : red");
		}
		else {
			redirectAttributes.addFlashAttribute("check_double", "사용 가능한 아이디입니다.");
			redirectAttributes.addFlashAttribute("dbl_check_style", "color : green");
		}
		
		return "redirect:/sign_up";
	}
	
	@RequestMapping("/sign_up/register") 
	public String register(RedirectAttributes redirectAttributes, HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("에러: " + e.getMessage());
		}
		
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		if (User_Insert.insert(user_id, user_pw, name, email)) {
			return "redirect:/sign_up/succ";
		} 
		else {
			redirectAttributes.addFlashAttribute("msg", "회원가입에 실패했습니다.");
			return "redirect:/sign_up";
		}
	}
	
	@RequestMapping("/sign_up/succ")
	public String succ() {
		return "Sign_Up_Succ";
	}
}
