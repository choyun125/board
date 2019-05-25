package com.choyun.dao;

import java.util.HashMap;
import java.util.List;

import com.choyun.domain.ReplyVO;

public interface ReplyDAO {
	
	public List<ReplyVO> selectReplyList(Integer bno) throws Exception;
	public ReplyVO selectReplyData(String rno) throws Exception;
	
	public Integer countTotal(HashMap<String, Object> keyMap) throws Exception;
	public List<ReplyVO> selectReplyPagingList(HashMap<String, Object> keyMap) throws Exception;
	
	public void insertReplyData(HashMap<String, Object> keyMap) throws Exception;
	public void updateReplyData(ReplyVO vo) throws Exception;
	public void deleteReplyData(HashMap<String, Object> keyMap) throws Exception;
}
