package com.choyun.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choyun.dao.BoardDAO;
import com.choyun.domain.BoardVO;
import com.choyun.paging.PageMaker;
import com.choyun.paging.SearchCriteria;
import com.choyun.service.BoardService;

@Controller
@RequestMapping(value = { "/board" })
public class BoardController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Inject
	BoardDAO bdao;
	
	@Inject
	BoardService bService;
	
	@RequestMapping(value = { "/list" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String print(Model model, @ModelAttribute SearchCriteria cri) {
		
		try {
			
			cri.setPerPageNum(10);
			
			PageMaker pm = new PageMaker();
			
			pm.setDisplayPageNum(10);
			pm.setCri(cri);
			pm.setTotalCount(bService.sumTotal(cri));
			
			model.addAttribute("printPage", pm);
			model.addAttribute("blist", bService.printBoardPagingList(cri));
		}
		catch (Exception e) {
			logger.error("-------- BOARD PRINT ERROR --------");
			logger.error(e.toString());
		}
		
		return "/board/BoardListView";
	}
	
	@RequestMapping(value = { "/read" }, method = { RequestMethod.GET })
	public String changeReadPage(Model model, SearchCriteria cri, int bno) {
		
		try {
			model.addAttribute("page", cri.getPage());
			model.addAttribute("cri", cri);
			model.addAttribute("bvo", bService.readBoardData(bno));
		}
		
		catch (Exception e) {
			logger.error("-------- BOARD READ ERROR --------");
			logger.error(e.toString());
		}
		return "/board/BoardReadView";
	}
	
	@RequestMapping(value = { "/insert" }, method = { RequestMethod.GET })
	public String changeInsertPage(Model model, SearchCriteria cri) {
		
		model.addAttribute("cri", cri);
		model.addAttribute("page", cri.getPage());
		return "/board/BoardInsertForm";
	}
	
	@RequestMapping(value = { "/insert" }, method = { RequestMethod.POST })
	public String insertData(SearchCriteria cri, BoardVO vo, RedirectAttributes redirectAttribute) {
		
		try {
			
			bService.registerBoardData(vo);
			redirectAttribute.addAttribute("searchType", cri.getSearchType());
			redirectAttribute.addAttribute("keyword", cri.getKeyword());
			redirectAttribute.addAttribute("page", cri.getPage());
			redirectAttribute.addFlashAttribute("result", "success");
			redirectAttribute.addFlashAttribute("method", "입력");
			logger.info("-- INSERT SUCCESS --");
		}
		
		catch (Exception e) {
			logger.error("-- INSERT FAIL --");
			logger.error(e.toString());
			
		}
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = { "/update" }, method = { RequestMethod.GET })
	public String changeUpdatePage(Model model, SearchCriteria cri, int bno) {
		
		try {
			model.addAttribute("page", cri.getPage());
			model.addAttribute("cri", cri);
			// service 에서는 viewcnt를 증가시키기 때문에, dao로 호출
			model.addAttribute("bvo", bdao.selectBoardData(bno));
		}
		
		catch (Exception e) {
			
		}
		
		return "/board/BoardUpdateForm";
	}
	
	@RequestMapping(value = { "/update" }, method = { RequestMethod.POST })
	public String updateData(SearchCriteria cri, BoardVO vo, RedirectAttributes redirectAttribute) {
		
		try {

			bService.modifyBoardData(vo);
			redirectAttribute.addAttribute("searchType", cri.getSearchType());
			redirectAttribute.addAttribute("keyword", cri.getKeyword());
			redirectAttribute.addAttribute("page", cri.getPage());
			redirectAttribute.addFlashAttribute("result", "success");
			redirectAttribute.addFlashAttribute("method", "수정");
			logger.info("-- UPDATE SUCCESS --");
		}
		
		catch (Exception e) {
			logger.error("-------- BOARD UPDATE ERROR --------");
			logger.error(e.toString());
			
		}
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public String deleteData(int bno, RedirectAttributes redirectAttribute) {
		
		try {
			
			bService.removeBoardData(bno);
			redirectAttribute.addFlashAttribute("result", "success");
			redirectAttribute.addFlashAttribute("method", "삭제");
			logger.info("-- DELETE SUCCESS --");
		}
		
		catch (Exception e) {
			logger.error("-------- BOARD DELETE ERROR --------");
			logger.error(e.toString());
			redirectAttribute.addFlashAttribute("result", "fail");
			
		}
		
		return "redirect:/board/list";
	}
	
}
