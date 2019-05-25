package com.choyun.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.choyun.dao.AttachDAO;
import com.choyun.domain.AttachVO;
import com.choyun.paging.SearchCriteria;
import com.choyun.service.AttachService;

@Service
public class AttachServiceImpl implements AttachService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Inject
	private AttachDAO adao;
	
	private HashMap<String, Object> createKeyMap(String[] value) {
		
		HashMap<String, Object> keyMap = new HashMap<String, Object>();
		
		String[] key = new String[] { "bno" };
		
		int keyLength = key.length;
		
		for (int i = 0; i < keyLength; i++) {
			if (value[i] != null) {
				keyMap.put(key[i], value[i]);
				logger.info("keyMap create : " + key[i] + " - " + keyMap.get(key[i]));
			}
		}
		
		return keyMap;
	}
	
	private HashMap<String, Object> createPagingMap(String[] value) {
		
		HashMap<String, Object> pagingMap = new HashMap<String, Object>();
		
		String[] key = new String[] { "pageStart", "perPageNum" };
		
		int keyLength = key.length;
		
		for (int i = 0; i < keyLength; i++) {
			if (value[i] != null) {
				pagingMap.put(key[i], Integer.parseInt(value[i]));
				logger.info("pagingMap create : " + key[i] + " - " + pagingMap.get(key[i]));
			}
		}
		
		return pagingMap;
	}
	
	private HashMap<String, Object> createUnionMap(HashMap<String, Object> ... values) {
		
		HashMap<String, Object> unionMap = new HashMap<String, Object>();
		
		String[] key = new String[] { "keyMap", "pageMaker" };
		
		int length = values.length;
		
		for (int i = 0; i < length; i++) {
			
			unionMap.put(key[i], values[i]);
			logger.info("unionMap create : " + key[i] + " - " + unionMap.get(key[i]));
		}
		
		return unionMap;
	}
	
	@Override
	public List<AttachVO> printAttachFileAllList() throws Exception {
		
		return adao.selectAttachFileAllList();
	}
	
	@Override
	public AttachVO printAttachFileData(int ano) throws Exception {
		
		return adao.selectAttachFileData(ano);
	}
	
	@Override
	public int sumTotal(SearchCriteria cri) throws Exception {
		
		return adao.countTotal(cri);
	}
	
	@Override
	public List<AttachVO> printAttachFilePagingAllList(SearchCriteria cri) {
		
		return adao.selectAttachFilePagingAllList(cri);
	}
	
	@Override
	public int sumBnoTotal(String[] ... values) throws Exception {
		
		return adao.countBnoTotal(createUnionMap(createKeyMap(values[0]), createPagingMap(values[1])));
	}
	
	@Override
	public List<AttachVO> readAttachFileBnoList(int bno) throws Exception {
		
		return adao.selectAttachFileBnoList(bno);
	}
	
	@Override
	public List<AttachVO> readAttachFilePagingBnoList(String[] ... values) {
		
		return adao.selectAttachFilePagingBnoList(createUnionMap(createKeyMap(values[0]), createPagingMap(values[1])));
	}
	
	@Override
	public boolean registerAttachFile(AttachVO vo) throws Exception {
		
		if (adao.insertAttachFile(vo) == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean modifyAttachFile(AttachVO vo) throws Exception {
		
		if (adao.updateAttachFile(vo) == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean removeAttachFile(int ano) throws Exception {
		
		if (adao.deleteAttachFile(ano) == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
