package com.board.app;

public class Reply_Info {
	private int reply_id;
	private String user_id;
	private String user_name;
	private String text;
	
	
	public int getReply_id() {
		return reply_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public String getText() {
		return text;
	}
	
	public Reply_Info(int reply_id, String user_id, String user_name, String text) {
		super();
		this.reply_id = reply_id;
		this.user_id = user_id;
		this.user_name = user_name;
		this.text = text;
	}
	@Override
	public String toString() {
		return "Reply_Info [reply_id=" + reply_id + ", user_id=" + user_id + ", user_name=" + user_name + ", text="
				+ text + "]";
	}
	
}
