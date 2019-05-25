package com.choyun.service;

import java.util.List;

import com.choyun.domain.ReplyVO;
import com.choyun.paging.Criteria;

public interface ReplyService {
	
	public List<ReplyVO> printReplyList(Integer bno) throws Exception;
	public ReplyVO readReplyData(String rno) throws Exception;
	
	public Integer sumTotal(Integer bno) throws Exception;
	public List<ReplyVO> printReplyPagingList(Criteria cri, Integer bno) throws Exception;
	
	public void registerReplyData(Object[] value) throws Exception;
	public void modifyReplyData(ReplyVO vo) throws Exception;
	public void removeReplyData(Object[] value) throws Exception;
}
