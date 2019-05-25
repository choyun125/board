package com.choyun.domain;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReplyVO {
	
	private int no;
	private int rno;
	private int bno;
	private String userid;
	private String username;
	private String replycontent;
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
	
	public int getRno() {
		
		return rno;
	}
	
	public void setRno(int rno) {
		
		this.rno = rno;
	}
	
	public int getBno() {
		
		return bno;
	}
	
	public void setBno(int bno) {
		
		this.bno = bno;
	}
	
	public String getUserid() {
		
		return userid;
	}
	
	public void setUserid(String userid) {
		
		this.userid = userid;
	}
	
	public String getUsername() {
		
		return username;
	}
	
	public void setUsername(String username) {
		
		this.username = username;
	}
	
	public String getReplycontent() {
		
		return replycontent;
	}
	
	public void setReplycontent(String replycontent) {
		
		this.replycontent = replycontent;
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
		
		return "ReplyVO [no=" + no + ", rno=" + rno + ", bno=" + bno + ", userid=" + userid + ", username=" + username + ", replycontent=" + replycontent + ", regdate=" + regdate + ", updateDate=" + updateDate + "]";
	}
	
}
