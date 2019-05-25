package com.choyun.controller;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.choyun.domain.ReplyVO;
import com.choyun.paging.Criteria;
import com.choyun.paging.PageMaker;
import com.choyun.service.BoardService;
import com.choyun.service.ReplyService;

@RestController
@RequestMapping(value = { "/reply" })
public class ReplyController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Inject
	private BoardService bService;
	
	@Inject
	private ReplyService rService;
	
	@RequestMapping(value = { "/insert" }, method = { RequestMethod.POST })
	public void insert(@RequestBody ReplyVO vo) {
		
		try {
			
			Object[] value = new Object[3];
			
			value[0] = "insert";
			value[1] = 1;
			value[2] = vo;
			
			if (vo.getUserid() == null && vo.getUsername() != null) {
				
				String userid = bService.readBoardData(vo.getBno()).getUserid();
				vo.setUserid(userid);
				
			}
			
			rService.registerReplyData(value);
		}
		catch (Exception e) {
			logger.error("-- REPLY INSERT ERROR --");
			logger.error(e.toString());
		}
	}
	
	@RequestMapping(value = { "/update/{rno}" }, method = { RequestMethod.PUT, RequestMethod.PATCH })
	public void update(@PathVariable("rno") Integer rno, @RequestBody ReplyVO vo) {
		
		try {
			vo.setRno(rno);
			rService.modifyReplyData(vo);
		}
		catch (Exception e) {
			logger.error("-- REPLY UPDATE ERROR --");
			logger.error(e.toString());
		}
	}
	
	@RequestMapping(value = { "/delete/{rno}/{bno}" }, method = { RequestMethod.DELETE })
	public void delete(@PathVariable("rno") Integer rno,@PathVariable("bno") Integer bno) {
		
		try {
			
			Object[] value = new Object[4];
			value[0] = "delete";
			value[1] = -1;
			value[2] = bno;
			value[3] = rno;
			
			rService.removeReplyData(value);
		}
		catch (Exception e) {
			logger.error("-- REPLY DELETE ERROR --");
			logger.error(e.toString());
		}
	}
	
	@RequestMapping(value = { "/{page}/{bno}" }, method = { RequestMethod.POST })
	public HashMap<String, Object> print(@PathVariable("page") int page, @PathVariable("bno") Integer bno) {
		
		HashMap<String, Object> jsonObject = null;
		List<ReplyVO> jsonArray = null;
		
		try {
			
			jsonObject = new HashMap<String, Object>();
			
			Criteria cri = new Criteria();
			cri.setPage(page);
			cri.setPerPageNum(5);
			
			PageMaker pm = new PageMaker();
			pm.setDisplayPageNum(5);
			pm.setCri(cri);
			pm.setTotalCount(rService.sumTotal(bno));
			
			jsonArray = rService.printReplyPagingList(cri, bno);
			
			jsonObject.put("items", jsonArray);
			jsonObject.put("printPage", pm);
		}
		
		catch (Exception e) {
			logger.error("-- REPLY PRINT ERROR --");
			logger.error(e.toString());
		}
		
		return jsonObject;
	}
}
