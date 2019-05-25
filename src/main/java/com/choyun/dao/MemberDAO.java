package com.choyun.dao;

import java.util.List;

import com.choyun.domain.LoginDTO;
import com.choyun.domain.MemberVO;
import com.choyun.paging.SearchCriteria;

public interface MemberDAO {
	
	public List<MemberVO> selectMemberList() throws Exception;
	public MemberVO selectMemberData(String bno) throws Exception;
	
	public MemberVO selectMemberCheck(LoginDTO dto) throws Exception;
	
	public Integer countTotal(SearchCriteria cri) throws Exception;
	public List<MemberVO> selectMemberPagingList(SearchCriteria cri) throws Exception;
	
	public void insertMemberData(MemberVO vo) throws Exception;
	public void updateMemberData(MemberVO vo) throws Exception;
	public void deleteMemberData(String bno) throws Exception;
	
}
