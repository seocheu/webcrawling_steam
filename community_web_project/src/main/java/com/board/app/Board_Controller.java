package com.board.app;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.board.app.post.Like_Check;
import com.board.app.post.Like_Do;
import com.board.app.post.Like_Undo;
import com.board.app.post.Post_Delete;
import com.board.app.post.Post_Edit;
import com.board.app.post.Post_Page;
import com.board.app.post.Post_Regist;
import com.board.app.post.Post_Simple;
import com.board.app.post.Post_View;
import com.board.app.post.Reply_Delete;
import com.board.app.post.Reply_Edit;
import com.board.app.post.Reply_Regist;
import com.board.app.post.Reply_View;
import com.board.app.user.User_Edit;

@Controller
public class Board_Controller {
	
	@RequestMapping("/main")
	public String main(Model model, HttpServletRequest request) {
		
		request.getSession().removeAttribute("prev_post");
		
        int size = (int)((Post_Page.size() - 1) / 10) + 1;	
        model.addAttribute("size", size);
        model.addAttribute("list", Post_Simple.getList(request.getParameter("page")));
        
		return "Main";
	}
	
	@RequestMapping("/main/user")
	public String user(HttpServletRequest request) {
		if(request.getSession().getAttribute("login_user") == null)
			return "redirect:/main";
		
		return "User_Menu";
	}
	
	@PostMapping("/main/user/edit") 
	public String user_edit(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("에러: " + e.getMessage());
		}
		
		User_Info user = (User_Info)request.getSession().getAttribute("login_user");
		if(user == null) {
			return "redirect:/main";
		} 
		else {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			user = User_Edit.edit(name, email, user);
		}		
		request.getSession().setAttribute("login_user", user);
		return "redirect:/main/user";
	}
	
	@RequestMapping("/main/posts/{post_id}")
	public String posts(Model model, @PathVariable int post_id, HttpServletRequest request) {	
		Object post = request.getSession().getAttribute("prev_post");
		int prev_post = -1;
		if(post != null) {
			prev_post = Integer.parseInt(post.toString());
		}
		model.addAttribute("post", Post_View.view(post_id, prev_post));
		request.getSession().setAttribute("prev_post", post_id);
		model.addAttribute("replys", Reply_View.view(post_id));
		Object user = request.getSession().getAttribute("login_user");
		if(user != null) {
			String user_id = ((User_Info)user).getUser_id();
			if(Like_Check.check(user_id, post_id)) {
				request.getSession().setAttribute("like", "true");
			} else {
				request.getSession().setAttribute("like", "false");
			}
		} else {
			request.getSession().setAttribute("like", "guest");
		}
		
		return "Post_Detail";
	}
	
	@RequestMapping("/main/like") 
	public String like(HttpServletRequest request) {
		String post_id = request.getSession().getAttribute("prev_post").toString();
		Object user = request.getSession().getAttribute("login_user");
		if(user == null)
			return "redirect:/main/posts/" + post_id.toString();
		String user_id = ((User_Info)user).getUser_id();
		
		Like_Do.like(user_id, Integer.parseInt(post_id));
		
		return "redirect:/main/posts/" + post_id.toString();
	}
	
	@RequestMapping("/main/unlike") 
	public String unlike(HttpServletRequest request) {
		String post_id = request.getSession().getAttribute("prev_post").toString();
		Object user = request.getSession().getAttribute("login_user");
		if(user == null)
			return "redirect:/main/posts/" + post_id;
		String user_id = ((User_Info)user).getUser_id();
		
		Like_Undo.unlike(user_id, Integer.parseInt(post_id));
		
		return "redirect:/main/posts/" + post_id;
	}
	
	@PostMapping("/main/reply") 
	public String reply(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("에러: " + e.getMessage());
		}
		
		String post_id = request.getSession().getAttribute("prev_post").toString();
		Object user = request.getSession().getAttribute("login_user");
		if(user == null)
			return "redirect:/main/posts/" + post_id;
		String user_id = ((User_Info)user).getUser_id();
		String text = request.getParameter("text");
		
		Reply_Regist.regist(post_id, user_id, text);
		
		return "redirect:/main/posts/" + post_id;
	}
	
	@RequestMapping("/main/reply/edit/{reply_id}")
	public String reply_edit(Model model, HttpServletRequest request, @PathVariable int reply_id) {
		Reply_Info reply = null;
		Object post_id = request.getSession().getAttribute("prev_post");
		if(post_id != null) {
			reply = Reply_View.get(reply_id, Integer.parseInt(post_id.toString()));
		}
		if(reply == null || !((User_Info)request.getSession().getAttribute("login_user")).getUser_id().equals(reply.getUser_id())) {
			return "redirect:/main/posts/" + request.getSession().getAttribute("prev_post").toString();
		}
		
		model.addAttribute("reply", reply);
		
		return "Reply_Edit";
	}
	
	@RequestMapping("/main/reply/edit/do")
	public String reply_edit_do(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("에러: " + e.getMessage());
		}
		
		String post_id = request.getSession().getAttribute("prev_post").toString();
		String reply_id = request.getParameter("reply_id");
		String text = request.getParameter("text");

		Reply_Edit.edit(post_id, reply_id, text);
		
		return "redirect:/main/posts/" + post_id;
	}
	
	@RequestMapping("/main/reply/delete")
	public String reply_delete(HttpServletRequest request) {
		String post_id = request.getSession().getAttribute("prev_post").toString();
		String reply_id = request.getParameter("reply_id");

		Reply_Delete.delete(post_id, reply_id);
		
		return "redirect:/main/posts/" + post_id;
	}
	
	@RequestMapping("/main/posting") 
	public String posting(HttpServletRequest request) {
		if(request.getSession().getAttribute("login_user") == null) {
			return "redirect:/main";
		}
		return "Posting";
	}
	
	@RequestMapping("/main/regist") 
	public String regist(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("에러: " + e.getMessage());
		}
		
		Object user = request.getSession().getAttribute("login_user");
		if(user == null) {
			return "redirect:/main";
		}
		String user_id = ((User_Info)user).getUser_id();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Post_Regist.regist(user_id, title, content);
		
		return "redirect:/main";
	}
	
	@RequestMapping("/main/edit")
	public String edit(Model model, HttpServletRequest request) {
		
		Object post = request.getSession().getAttribute("prev_post");
		int prev_post = -1;
		if(post != null) {
			prev_post = Integer.parseInt(post.toString());
		}
		Post_Info post_info = Post_View.view(prev_post, prev_post);
		if(!post_info.getUser_id().equals(((User_Info)request.getSession().getAttribute("login_user")).getUser_id()))
			return "redirect:/main";
		
		model.addAttribute("post", post_info); 
		return "Post_Edit";
	}
	
	@PostMapping("/main/edit/do")
	public String edit_do(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("에러: " + e.getMessage());
		}
		
		String post_id = request.getSession().getAttribute("prev_post").toString();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Post_Edit.edit(post_id, title, content);
		
		return "redirect:/main/posts/" + post_id;
	}
	
	@RequestMapping("/main/delete")
	public String delete(HttpServletRequest request) {
		
		String post_id = request.getSession().getAttribute("prev_post").toString();
		
		Post_Delete.delete(post_id);
		
		return "redirect:/main";
	}
}
