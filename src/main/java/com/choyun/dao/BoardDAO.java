package com.choyun.dao;

import java.util.HashMap;
import java.util.List;

import com.choyun.domain.BoardVO;
import com.choyun.paging.SearchCriteria;

public interface BoardDAO {
	
	public List<BoardVO> selectBoardList() throws Exception;
	public BoardVO selectBoardData(int bno) throws Exception;
	
	public int countTotal(SearchCriteria cri) throws Exception;
	public List<BoardVO> selectBoardPagingList(SearchCriteria cri) throws Exception;
	
	public int insertBoardData(BoardVO vo) throws Exception;
	public int updateBoardData(BoardVO vo) throws Exception;
	public int updateBoardViewCount(int bno) throws Exception;
	public int updateBoardReplyCount(HashMap<String, Object> keyMap) throws Exception;
	public int deleteBoardData(int bno) throws Exception;

}
