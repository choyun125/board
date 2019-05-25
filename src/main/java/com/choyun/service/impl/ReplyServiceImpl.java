package com.choyun.service.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choyun.dao.BoardDAO;
import com.choyun.dao.ReplyDAO;
import com.choyun.domain.ReplyVO;
import com.choyun.paging.Criteria;
import com.choyun.service.ReplyService;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Inject
	BoardDAO bdao;
	
	@Inject
	ReplyDAO rdao;
	
	private HashMap<String, Object> createMap(Object[] value) throws Exception {
		
		HashMap<String, Object> keyMap = new HashMap<String, Object>();
		
		keyMap.put("amount", value[1]);
		
		switch (String.valueOf(value[0])) {
			
			case "insert": {
				ReplyVO vo = (ReplyVO) value[2];
				
				for (Field field : value[2].getClass().getDeclaredFields()) {
					String filedName = field.getName();
					String methodName = filedName.replace(filedName.substring(0, 1), filedName.substring(0, 1).toUpperCase());
					keyMap.put(field.getName(), vo.getClass().getDeclaredMethod("get" + methodName).invoke(vo));
				}
				
				break;
			}
			
			case "delete": {
				keyMap.put("bno", value[2]);
				keyMap.put("rno", value[3]);
				break;
			}
			
		}
		
		return keyMap;
		
	}
	
	@Override
	public List<ReplyVO> printReplyList(Integer bno) throws Exception {
		
		return rdao.selectReplyList(bno);
	}
	
	@Override
	public ReplyVO readReplyData(String rno) throws Exception {
		
		return rdao.selectReplyData(rno);
	}
	
	@Override
	public Integer sumTotal(Integer bno) throws Exception {
		
		HashMap<String, Object> keyMap = new HashMap<String, Object>();
		
		keyMap.put("bno", bno);
		
		return rdao.countTotal(keyMap);
	}
	
	@Override
	public List<ReplyVO> printReplyPagingList(Criteria cri, Integer bno) throws Exception {
		
		HashMap<String, Object> keyMap = new HashMap<String, Object>();
		
		keyMap.put("startPage", cri.getPageStart());
		keyMap.put("perPageNum", cri.getPerPageNum());
		keyMap.put("bno", bno);
		
		return rdao.selectReplyPagingList(keyMap);
	}
	
	@Transactional
	@Override
	public void registerReplyData(Object[] value) throws Exception {
		
		HashMap<String, Object> keyMap = createMap(value);
		bdao.updateBoardReplyCount(keyMap);
		rdao.insertReplyData(keyMap);
	}
	
	@Override
	public void modifyReplyData(ReplyVO vo) throws Exception {
		
		rdao.updateReplyData(vo);
	}
	
	@Transactional
	@Override
	public void removeReplyData(Object[] value) throws Exception {

		HashMap<String, Object> keyMap = createMap(value);
		bdao.updateBoardReplyCount(keyMap);
		rdao.deleteReplyData(keyMap);

	}
	
}
