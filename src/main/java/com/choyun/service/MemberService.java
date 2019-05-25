package com.choyun.service;

import java.util.List;

import com.choyun.domain.LoginDTO;
import com.choyun.domain.MemberVO;
import com.choyun.paging.SearchCriteria;

public interface MemberService {
	
	public List<MemberVO> printMemberList() throws Exception;
	public MemberVO readMemberData(String userid) throws Exception;
	
	public MemberVO login(LoginDTO dto) throws Exception;
	
	public Integer sumTotal(SearchCriteria cri) throws Exception;
	public List<MemberVO> printMemberPagingList(SearchCriteria cri) throws Exception;
	
	public void registerMemberData(MemberVO vo) throws Exception;
	public void modifyMemberData(MemberVO vo) throws Exception;
	public void removeMemberData(String userid) throws Exception;
	
}
