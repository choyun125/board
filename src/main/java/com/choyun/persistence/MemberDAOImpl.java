package com.choyun.persistence;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.choyun.dao.MemberDAO;
import com.choyun.domain.LoginDTO;
import com.choyun.domain.MemberVO;
import com.choyun.paging.SearchCriteria;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	private static final String NAMESPACE= "com.choyun.mapper.MemberMapper";
	
	@Resource(name = "mysqlSqlSession01")
	private SqlSession session;
	
	@Override
	public List<MemberVO> selectMemberList() throws Exception {
		
		return session.selectList(NAMESPACE + ".selectMemberList");
	}
	
	@Override
	public MemberVO selectMemberData(String userid) throws Exception {
		
		return session.selectOne(NAMESPACE + ".selectMemberData", userid);
	}
	
	@Override
	public MemberVO selectMemberCheck(LoginDTO dto) throws Exception {
		
		return session.selectOne(NAMESPACE + ".selectMemberCheck", dto);
	}
	
	@Override
	public Integer countTotal(SearchCriteria cri) throws Exception {
		
		return session.selectOne(NAMESPACE + ".countTotal", cri);
	}
	
	@Override
	public List<MemberVO> selectMemberPagingList(SearchCriteria cri) throws Exception {
		
		return session.selectList(NAMESPACE + ".selectMemberPagingList", cri);
	}
	
	@Override
	public void insertMemberData(MemberVO vo) throws Exception {
		
		session.insert(NAMESPACE + ".insertMemberData", vo);
	}
	
	@Override
	public void updateMemberData(MemberVO vo) throws Exception {
		System.out.println("DAO : " + vo.toString());
		session.update(NAMESPACE + ".updateMemberData", vo);
	}
	
	@Override
	public void deleteMemberData(String userid) throws Exception {
		session.delete(NAMESPACE + ".deleteMemberData", userid);
	}

}
