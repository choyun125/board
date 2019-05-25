package com.choyun.controller;

import java.util.HashMap;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.choyun.domain.MemberVO;
import com.choyun.paging.PageMaker;
import com.choyun.paging.SearchCriteria;
import com.choyun.service.MemberService;

@Controller
@RequestMapping(value = { "/member" })
public class MemberController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Inject
	MemberService mService;
	
	@ResponseBody
	@RequestMapping(value = { "/list/{page}" }, method = { RequestMethod.POST })
	public HashMap<String, Object> print(@PathVariable("page") int page) {
		
		HashMap<String, Object> jsonObject = new HashMap<String, Object>();
		
		try {
			
			SearchCriteria cri = new SearchCriteria();
			
			cri.setPage(page);
			cri.setPerPageNum(5);
			
			PageMaker pm = new PageMaker();
			pm.setDisplayPageNum(5);
			pm.setCri(cri);
			pm.setTotalCount(mService.sumTotal(cri));
			
			jsonObject.put("items", mService.printMemberPagingList(cri));
			jsonObject.put("printPage", pm);
		}
		
		catch (Exception e) {
			logger.error("-------- MEMBER PRINT ERROR --------");
			logger.error(e.toString());
		}
		
		return jsonObject;
	}
	
	@RequestMapping(value = { "/list" }, method = { RequestMethod.GET })
	public String print() {
		
		return "/member/MemberListView";
	}
	
	@ResponseBody
	@RequestMapping(value = { "/read" }, method = { RequestMethod.POST })
	public HashMap<String, Object> read(@RequestBody MemberVO vo) {
		
		HashMap<String, Object> jsonObject = null;
		
		String returnValue = "";
		
		try {
			
			jsonObject = new HashMap<String, Object>();
			
			MemberVO temp = mService.readMemberData(vo.getUserid());
			
			if (temp == null) {
				
				returnValue = "success";
			}
			
			else {
				
				returnValue = "fail";
				jsonObject.put("item", temp);
			}
			
			jsonObject.put("result", returnValue);
			return jsonObject;
			
		}
		catch (Exception e) {
			returnValue = "error";
			
			jsonObject.put("result", returnValue);
			return jsonObject;
		}
	}

	@RequestMapping(value = { "/insert" }, method = { RequestMethod.GET })
	public String changeInsertPage(Model model) {
		
		return "member/MemberInsertForm";
		
	}
	
	@ResponseBody
	@RequestMapping(value = { "/insert" }, method = { RequestMethod.POST })
	public void insert(@RequestBody MemberVO vo) {
		
		try {
			mService.registerMemberData(vo);
		}
		catch (Exception e) {
			logger.error("-------- MEMBER INSERT ERROR --------");
			logger.error(e.toString());
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value = { "/update/{userid}" }, method = { RequestMethod.PUT, RequestMethod.PATCH })
	public void update(@PathVariable("userid") String userid,  @RequestBody MemberVO vo) {
		
		try {
			vo.setUserid(userid);
			System.out.println(vo.toString());
			mService.modifyMemberData(vo);
		}
		catch (Exception e) {
			logger.error("-------- MEMBER UPDATE ERROR --------");
			logger.error(e.toString());
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value = { "/delete/{userid}" }, method = { RequestMethod.DELETE })
	public void delete(@PathVariable("userid") String userid) {
		System.out.println("userid : " + userid);
		try {
			mService.removeMemberData(userid);
		}
		catch (Exception e) {
			logger.error("-------- MEMBER DELETE ERROR --------");
			logger.error(e.toString());
		}
		
	}
	
}
