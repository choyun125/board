package com.choyun.service;

import java.util.List;

import com.choyun.domain.AttachVO;
import com.choyun.paging.SearchCriteria;

public interface AttachService {
	
	public List<AttachVO> printAttachFileAllList() throws Exception;
	public AttachVO printAttachFileData(int ano) throws Exception;
	
	// bno, SearchCriteria
	public int sumTotal(SearchCriteria cri) throws Exception;
	public List<AttachVO> printAttachFilePagingAllList(SearchCriteria cri);
	
	
	public int sumBnoTotal(String[] ... values) throws Exception;
	public List<AttachVO> readAttachFileBnoList(int bno) throws Exception;
	public List<AttachVO> readAttachFilePagingBnoList(String[] ... values);
	
	public boolean registerAttachFile(AttachVO vo) throws Exception;
	public boolean modifyAttachFile(AttachVO vo) throws Exception;
	public boolean removeAttachFile(int ano) throws Exception;
}
