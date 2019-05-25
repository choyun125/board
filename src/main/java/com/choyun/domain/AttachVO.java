package com.choyun.domain;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AttachVO {
	
	private int ano;
	private String fileName;
	private String fullName;
	private int bno;
	private String description;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Timestamp regdate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Timestamp updateDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Timestamp duedate;
	
	public int getAno() {
		
		return ano;
	}
	
	public void setAno(int ano) {
		
		this.ano = ano;
	}
	
	public String getFileName() {
		
		return fileName;
	}
	
	public void setFileName(String filename) {
		
		this.fileName = filename;
	}
	
	public String getFullName() {
		
		return fullName;
	}
	
	public void setFullName(String fullname) {
		
		this.fullName = fullname;
	}
	
	public int getBno() {
		
		return bno;
	}
	
	public void setBno(int bno) {
		
		this.bno = bno;
	}
	
	public String getDescription() {
		
		return description;
	}
	
	public void setDescription(String description) {
		
		this.description = description;
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
	
	public Timestamp getDuedate() {
		
		return duedate;
	}
	
	public void setDuedate(Timestamp duedate) {
		
		this.duedate = duedate;
	}

	@Override
	public String toString() {
		
		return "AttachVO [ano=" + ano + ", fileName=" + fileName + ", fullName=" + fullName + ", bno=" + bno + ", description=" + description + ", regdate=" + regdate + ", updateDate=" + updateDate + ", duedate=" + duedate + "]";
	}
	
	
}
