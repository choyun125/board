package com.choyun.persistence;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.choyun.dao.BoardDAO;
import com.choyun.domain.BoardVO;
import com.choyun.paging.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	private static final String NAMESPACE = "com.choyun.mapper.BoardMapper";
	
	@Resource(name = "mysqlSqlSession01")
	private SqlSession session;
	
	@Override
	public List<BoardVO> selectBoardList() throws Exception {
		
		return session.selectList(NAMESPACE + ".selectBoardList");
	}
	
	@Override
	public BoardVO selectBoardData(int bno) throws Exception {
		
		return session.selectOne(NAMESPACE + ".selectBoardData", bno);
	}
	
	@Override
	public int countTotal(SearchCriteria cri) throws Exception {
		
		return session.selectOne(NAMESPACE + ".countTotal", cri);
	}
	
	@Override
	public List<BoardVO> selectBoardPagingList(SearchCriteria cri) throws Exception {
		
		return session.selectList(NAMESPACE + ".selectBoardPagingList", cri);
	}
	
	@Override
	public int insertBoardData(BoardVO vo) throws Exception {
		
		return session.insert(NAMESPACE + ".insertBoardData", vo);
	}
	
	@Override
	public int updateBoardData(BoardVO vo) throws Exception {
		
		return session.update(NAMESPACE + ".updateBoardData", vo);
	}
	
	@Override
	public int updateBoardViewCount(int bno) throws Exception {
		
		return session.update(NAMESPACE + ".updateBoardViewCount", bno);
		
	}
	
	@Override
	public int updateBoardReplyCount(HashMap<String, Object> keyMap) throws Exception {
		
		return session.update(NAMESPACE + ".updateBoardReplyCount", keyMap);
	}
	
	@Override
	public int deleteBoardData(int bno) throws Exception {
		
		return session.delete(NAMESPACE + ".deleteBoardData", bno);
	}
	
}
