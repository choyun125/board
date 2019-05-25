package com.choyun.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choyun.dao.AttachDAO;
import com.choyun.dao.BoardDAO;
import com.choyun.domain.AttachVO;
import com.choyun.domain.BoardVO;
import com.choyun.paging.SearchCriteria;
import com.choyun.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO bdao;
	
	@Inject
	private AttachDAO adao;
	
	@Override
	public List<BoardVO> printBoardList() throws Exception {
		
		return bdao.selectBoardList();
	}
	
	@Transactional
	@Override
	public BoardVO readBoardData(int bno) throws Exception {
		
		bdao.updateBoardViewCount(bno);
		return bdao.selectBoardData(bno);
	}
	
	@Override
	public Integer sumTotal(SearchCriteria cri) throws Exception {
		
		return bdao.countTotal(cri);
	}
	
	@Override
	public List<BoardVO> printBoardPagingList(SearchCriteria cri) throws Exception {
		
		return bdao.selectBoardPagingList(cri);
	}
	
	@Transactional
	@Override
	public boolean registerBoardData(BoardVO vo) throws Exception {
		
		String[] files = vo.getFiles();
		if (files == null) {
			return false;
		}
		
		else {
			for(String fileName : files) {
				AttachVO avo = new AttachVO();
				avo.setFullName(fileName);
				avo.setBno(vo.getBno());
				adao.insertAttachFile(avo);
			}
		}
		
		if (bdao.insertBoardData(vo) == 1) {
			
			return true;
		}
		else {
			return false;
		}
	}
	
	
	@Transactional
	@Override
	public boolean modifyBoardData(BoardVO vo) throws Exception {
		
		if (bdao.updateBoardData(vo) == 1) {
			
			String[] files = vo.getFiles();
			
			List<AttachVO> list = adao.selectAttachFileBnoList(vo.getBno());
			
			for(AttachVO avo : list) {
				adao.deleteAttachFile(avo.getAno());
			}
			
			for(String fileName : files) {
				AttachVO avo = new AttachVO();
				avo.setFullName(fileName);
				avo.setBno(vo.getBno());
				adao.insertAttachFile(avo);
			}
			
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean modifyBoardViewCount(int bno) throws Exception {
		
		if (bdao.updateBoardViewCount(bno) == 1) {
			
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean modifyBoardReplyCount(HashMap<String, Object> keyMap) throws Exception {
		
		if (bdao.updateBoardReplyCount(keyMap) == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Transactional
	@Override
	public boolean removeBoardData(int bno) throws Exception {
		
		List<AttachVO> list = adao.selectAttachFileBnoList(bno);
		
		for(AttachVO avo : list) {
			adao.deleteAttachFile(avo.getAno());
		}
		
		if (bdao.deleteBoardData(bno) == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
