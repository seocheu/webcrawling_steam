package com.board.app;

public class Post_Info {
	private int post_id;
	private String title;
	private String content;
	private String user_id;
	private String user_name;
	private String post_date;
	private int view_cnt;
	private int like_cnt;
	private int reply_cnt;
	
	public int getPost_id() {
		return post_id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getUser_id() {
		return user_id;
	}
	
	public String getUser_name() {
		return user_name;
	}
	
	public String getPost_date() {
		return post_date;
	}

	public int getView_cnt() {
		return view_cnt;
	}
	
	public int getLike_cnt() {
		return like_cnt;
	}

	public int getReply_cnt() {
		return reply_cnt;
	}

	public Post_Info(int post_id, String title, String content, 
			String user_id, String user_name, String post_date, int view_cnt, int like_cnt, int reply_cnt) {
		this.post_id = post_id;
		this.title = title;
		this.content = content;
		this.user_id = user_id;
		this.user_name = user_name;
		this.post_date = post_date;
		this.view_cnt = view_cnt;
		this.like_cnt = like_cnt;
		this.reply_cnt = reply_cnt;
	}

	@Override
	public String toString() {
		return "Post_Info [post_id=" + post_id + ", title=" + title + ", content=" + content + ", user_id=" + user_id
				+ ", user_name=" + user_name + ", post_date=" + post_date + ", view_cnt=" + view_cnt + ", like_cnt="
				+ like_cnt + ", reply_cnt=" + reply_cnt + "]";
	}

	
}
