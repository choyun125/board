package com.choyun.persistence;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.choyun.dao.AttachDAO;
import com.choyun.domain.AttachVO;
import com.choyun.paging.SearchCriteria;


@Repository
public class AttachDAOImpl implements AttachDAO {

	private final String namespace = "com.choyun.mapper.AttachMapper"; 
	
	@Resource(name="mysqlSqlSession01")
	private SqlSession session;
	
	@Override
	public List<AttachVO> selectAttachFileAllList() throws Exception {
		
		
		return session.selectList(namespace + ".selectAttachFileAllList");
	}

	@Override
	public AttachVO selectAttachFileData(int ano) throws Exception {
		
		
		return session.selectOne(namespace + ".selectAttachFileData", ano);
	}

	@Override
	public int countTotal(SearchCriteria cri) throws Exception {
		
		
		return session.selectOne(namespace + ".countTotal", cri);
	}

	@Override
	public List<AttachVO> selectAttachFilePagingAllList(SearchCriteria cri) {
		
		
		return session.selectList(namespace + ".selectAttachFilePagingAllList", cri);
	}
	
	@Override
	public int countBnoTotal(HashMap<String, Object> keyMap) throws Exception {
		
		
		return session.selectOne(namespace + ".countTotal", keyMap);
	}
	
	@Override
	public List<AttachVO> selectAttachFileBnoList(int bno) throws Exception {
		
		
		return session.selectList(namespace + ".selectAttachFileBnoList", bno);
	}
	
	@Override
	public List<AttachVO> selectAttachFilePagingBnoList(HashMap<String, Object> keyMap) {
		
		
		return session.selectList(namespace + ".selectAttachFilePagingBnoList", keyMap);
	}
	
	@Override
	public int insertAttachFile(AttachVO vo) {
		
		return session.insert(namespace + ".insertAttachFile", vo);
		
	}

	@Override
	public int updateAttachFile(AttachVO vo) throws Exception {
		
		
		return session.insert(namespace + ".updateAttachFile", vo);
	}

	@Override
	public int deleteAttachFile(int ano) throws Exception {
		
		
		return session.insert(namespace + ".deleteAttachFile", ano);
	}
	
	
	
}
