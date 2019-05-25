package com.choyun.domain;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MemberVO {
	
	private int no;
	private String userid;
	private String userpw;
	private String username;
	private String email;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
	private Timestamp regdate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
	private Timestamp updateDate;
	
	public int getNo() {
		
		return no;
	}
	
	public void setNo(int no) {
		
		this.no = no;
	}
	
	public String getUserid() {
		
		return userid;
	}
	
	public void setUserid(String userid) {
		
		this.userid = userid;
	}
	
	public String getUserpw() {
		
		return userpw;
	}
	
	public void setUserpw(String userpw) {
		
		this.userpw = userpw;
	}
	
	public String getUsername() {
		
		return username;
	}
	
	public void setUsername(String username) {
		
		this.username = username;
	}
	
	public String getEmail() {
		
		return email;
	}
	
	public void setEmail(String email) {
		
		this.email = email;
	}
	
	public Timestamp getRegdate() {
		
		return regdate;
	}
	
	public void setRegdate(Timestamp regdate) {
		
		this.regdate = regdate;
	}
	
	public Timestamp getUpdateDate() {
		
		return updateDate;
	}
	
	public void setUpdateDate(Timestamp updateDate) {
		
		this.updateDate = updateDate;
	}
	
	@Override
	public String toString() {
		
		return "MemberVO [no=" + no + ", userid=" + userid + ", userpw=" + userpw + ", username=" + username + ", email=" + email + ", regdate=" + regdate + ", updateDate=" + updateDate + "]";
	}
	
}
