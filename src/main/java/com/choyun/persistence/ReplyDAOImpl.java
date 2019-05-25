package com.choyun.persistence;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.choyun.dao.ReplyDAO;
import com.choyun.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	
	private static final String NAMESPACE = "com.choyun.mapper.ReplyMapper";
	
	@Resource(name = "mysqlSqlSession01")
	private SqlSession session;
	
	@Override
	public List<ReplyVO> selectReplyList(Integer bno) throws Exception {
		
		return session.selectList(NAMESPACE + ".selectReplyList", bno);
	}
	
	@Override
	public ReplyVO selectReplyData(String rno) throws Exception {
		
		return session.selectOne(NAMESPACE + ".selectReplyData", rno);
	}
	
	@Override
	public Integer countTotal(HashMap<String, Object> keyMap) throws Exception {
		
		return session.selectOne(NAMESPACE + ".countTotal", keyMap);
	}
	
	@Override
	public List<ReplyVO> selectReplyPagingList(HashMap<String, Object> keyMap) throws Exception {
		
		return session.selectList(NAMESPACE + ".selectReplyPagingList", keyMap);
	}
	
	@Override
	public void insertReplyData(HashMap<String, Object> keyMap) throws Exception {
		session.insert(NAMESPACE + ".insertReplyData", keyMap);
	}
	
	@Override
	public void updateReplyData(ReplyVO vo) throws Exception {
		session.update(NAMESPACE + ".updateReplyData", vo);
	}
	
	@Override
	public void deleteReplyData(HashMap<String, Object> keyMap) throws Exception {
		session.delete(NAMESPACE + ".deleteReplyData", keyMap);
	}
	
}
