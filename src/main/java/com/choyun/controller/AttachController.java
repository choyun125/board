package com.choyun.controller;

import java.util.HashMap;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.choyun.domain.AttachVO;
import com.choyun.service.AttachService;

@Controller
@RequestMapping(value= {"/attach"})
public class AttachController {
	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Inject
	AttachService aService;
	
	@ResponseBody
	@RequestMapping(value= {"/list/{bno}"}, method= {RequestMethod.POST})
	public HashMap<String, Object> printAttachList (@PathVariable("bno") int bno) throws Exception {
		
		HashMap<String, Object> jsonObject= new HashMap<String, Object>();
		
		jsonObject.put("items", aService.readAttachFileBnoList(bno));
		
		return jsonObject;
		
		
	}
	
	@ResponseBody
	@RequestMapping(value= {"/insert"}, method= {RequestMethod.POST})
	public void insertAttachFileData(@RequestBody AttachVO vo) {
		
		try {
			System.out.println("insert : " + vo.toString());
			aService.registerAttachFile(vo);
			
		}
		
		catch(Exception e) {
			logger.error("-------- FILE INSERT ERROR --------");
			logger.error(e.toString());
		}
	}
	
	@ResponseBody
	@RequestMapping(value= {"/delete/{ano}"}, method= {RequestMethod.DELETE})
	public void deleteAttachFileData(@PathVariable("ano") int ano) {
		
		try {
			aService.removeAttachFile(ano);
			
		}
		
		catch(Exception e) {
			logger.error("-------- FILE DELETE ERROR --------");
			logger.error(e.toString());
		}
	}
	
}
