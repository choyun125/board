package com.choyun.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.choyun.dao.MemberDAO;
import com.choyun.domain.LoginDTO;
import com.choyun.domain.MemberVO;
import com.choyun.paging.SearchCriteria;
import com.choyun.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Inject
	MemberDAO mdao;
	
	@Inject
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public List<MemberVO> printMemberList() throws Exception {
		
		return mdao.selectMemberList();
	}
	
	@Override
	public MemberVO readMemberData(String userid) throws Exception {
		
		return mdao.selectMemberData(userid);
	}
	
	@Override
	public MemberVO login(LoginDTO dto) throws Exception {
		
		return mdao.selectMemberCheck(dto);
	}
	
	@Override
	public Integer sumTotal(SearchCriteria cri) throws Exception {
		
		return mdao.countTotal(cri);
	}
	
	@Override
	public List<MemberVO> printMemberPagingList(SearchCriteria cri) throws Exception {
		
		return mdao.selectMemberPagingList(cri);
	}
	
	@Override
	public void registerMemberData(MemberVO vo) throws Exception {
		String encryptPw = passwordEncoder.encode(vo.getUserpw());
		vo.setUserpw(encryptPw);
		mdao.insertMemberData(vo);
		
	}
	
	@Override
	public void modifyMemberData(MemberVO vo) throws Exception {
		String encryptPw = passwordEncoder.encode(vo.getUserpw());
		vo.setUserpw(encryptPw);
		mdao.updateMemberData(vo);
	}
	
	@Override
	public void removeMemberData(String userid) throws Exception {
		mdao.deleteMemberData(userid);
	}

}
