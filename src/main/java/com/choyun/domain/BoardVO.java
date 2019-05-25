package com.choyun.domain;

import java.sql.Timestamp;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BoardVO {
	
	private int no;
	private int bno;
	private String title;
	private String content;
	private String userid;
	private String username;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Timestamp regdate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Timestamp updateDate;
	private int viewcnt;
	private int replycnt;
	private String[] files;
	
	public int getNo() {
		
		return no;
	}
	
	public void setNo(int no) {
		
		this.no = no;
	}
	
	public int getBno() {
		
		return bno;
	}
	
	public void setBno(int bno) {
		
		this.bno = bno;
	}
	
	public String getTitle() {
		
		return title;
	}
	
	public void setTitle(String title) {
		
		this.title = title;
	}
	
	public String getContent() {
		
		return content;
	}
	
	public void setContent(String content) {
		
		this.content = content;
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
	
	public int getViewcnt() {
		
		return viewcnt;
	}
	
	public void setViewcnt(int viewcnt) {
		
		this.viewcnt = viewcnt;
	}
	
	public int getReplycnt() {
		
		return replycnt;
	}
	
	public void setReplycnt(int replycnt) {
		
		this.replycnt = replycnt;
	}
	
	public String[] getFiles() {
		
		return files;
	}
	
	public void setFiles(String[] files) {
		
		this.files = files;
	}

	@Override
	public String toString() {
		
		return "BoardVO [no=" + no + ", bno=" + bno + ", title=" + title + ", content=" + content + ", userid=" + userid + ", username=" + username + ", regdate=" + regdate + ", updateDate=" + updateDate + ", viewcnt=" + viewcnt + ", replycnt=" + replycnt + ", files=" + Arrays.toString(files) + "]";
	}
	
	

}
