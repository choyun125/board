package com.choyun.service;

import java.util.HashMap;
import java.util.List;

import com.choyun.domain.BoardVO;
import com.choyun.paging.SearchCriteria;


public interface BoardService {
	
	public List<BoardVO> printBoardList() throws Exception;
	public BoardVO readBoardData(int bno) throws Exception;
	
	public Integer sumTotal(SearchCriteria cri) throws Exception;
	public List<BoardVO> printBoardPagingList(SearchCriteria cri) throws Exception;
	
	public boolean registerBoardData(BoardVO vo) throws Exception;
	public boolean modifyBoardData(BoardVO vo) throws Exception;
	public boolean modifyBoardViewCount(int bno) throws Exception;
	public boolean modifyBoardReplyCount(HashMap<String, Object> keyMap) throws Exception;
	public boolean removeBoardData(int bno) throws Exception;
	
}
