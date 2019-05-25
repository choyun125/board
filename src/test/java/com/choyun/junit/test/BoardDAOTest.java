package com.choyun.junit.test;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.choyun.dao.BoardDAO;
import com.choyun.domain.BoardVO;
import com.choyun.paging.SearchCriteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class BoardDAOTest {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);
	
	@Inject
	private BoardDAO bdao;
	
	@Test
	public void testSumTotal() {
		
		try {
			
			SearchCriteria cri = new SearchCriteria();
			
			Integer total = bdao.countTotal(cri);
			logger.info("TOTAL : " + total);
		}
		
		catch (Exception e) {
			logger.info("ERROR가 발생 하였습니다.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSelectBoardList() {
		
		try {
			SearchCriteria cri = new SearchCriteria();
			cri.setPage(1);
			cri.setPerPageNum(5);
			
			List<BoardVO> list = bdao.selectBoardList();
			
			int length = list.size();
			
			logger.info("-- Board Data --");
			
			for (int i = 0; i < length; i++) {
				
				logger.info(list.get(i).toString());
			}
		}
		
		catch (Exception e) {
			logger.info("ERROR가 발생 하였습니다.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSelectBoardPagingList() {
		
		try {
			SearchCriteria cri = new SearchCriteria();
			cri.setSearchType("userid");
			cri.setKeyword("user05");
			cri.setPage(1);
			cri.setPerPageNum(5);
			
			List<BoardVO> list = bdao.selectBoardPagingList(cri);
			
			int length = list.size();
			
			logger.info("-- Board Data --");
			
			for (int i = 0; i < length; i++) {
				
				logger.info(list.get(i).toString());
			}
		}
		
		catch (Exception e) {
			logger.info("ERROR가 발생 하였습니다.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReadBoardData() {
		
		int bno = 1;
		try {
			BoardVO readData = bdao.selectBoardData(bno);
			
			logger.info(readData.toString());
		}
		
		catch (Exception e) {
			logger.info("ERROR가 발생 하였습니다.");
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testInsertBoardData() {
		
		try {
			
			BoardVO insertData = new BoardVO();
			insertData.setTitle("제목 입니다.");
			insertData.setContent("내용 입니다.");
			insertData.setUserid("user00");
			//bdao.insertBoardData(insertData);
			
			
		}
		
		catch (Exception e) {
			logger.info("ERROR가 발생 하였습니다.");
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testUpdateBoardData() {
		
		try {
			
			BoardVO updateData = new BoardVO();
			updateData.setBno(8);
			updateData.setTitle("제목 이지롱");
			updateData.setContent("내용 이지롱");
			bdao.updateBoardData(updateData);
			
		}
		
		catch (Exception e) {
			logger.info("ERROR가 발생 하였습니다.");
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testDeleteBoardData() {
		
		int bno = 8;
		try {
			//bdao.deleteBoardData(bno);
		}
		
		catch (Exception e) {
			logger.info("ERROR가 발생 하였습니다.");
			e.printStackTrace();
		}
	}
	
}

