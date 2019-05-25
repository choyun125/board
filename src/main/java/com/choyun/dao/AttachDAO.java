package com.choyun.dao;

import java.util.HashMap;
import java.util.List;

import com.choyun.domain.AttachVO;
import com.choyun.paging.SearchCriteria;

public interface AttachDAO {
	
	public List<AttachVO> selectAttachFileAllList() throws Exception;
	public AttachVO selectAttachFileData(int ano) throws Exception;
	
	// bno, SearchCriteria
	public int countTotal(SearchCriteria cri) throws Exception;
	public List<AttachVO> selectAttachFilePagingAllList(SearchCriteria cri);
	
	
	public int countBnoTotal(HashMap<String, Object> keyMap) throws Exception;
	public List<AttachVO> selectAttachFileBnoList(int bno) throws Exception;
	public List<AttachVO> selectAttachFilePagingBnoList(HashMap<String, Object> keyMap);
	
	public int insertAttachFile(AttachVO vo) throws Exception;
	public int updateAttachFile(AttachVO vo) throws Exception;
	public int deleteAttachFile(int ano) throws Exception;
}
