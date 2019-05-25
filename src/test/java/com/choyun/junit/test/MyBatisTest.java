package com.choyun.junit.test;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class MyBatisTest {
	
	@Inject
	private SqlSessionFactory sqlFactory;

	@Test
	public void testFactory() {
		System.out.println(sqlFactory);
	}
	
	@Test
	public void testSession() {
		
		try (SqlSession session = sqlFactory.openSession()) {
			System.out.println("데이터베이스 연동에 성공 하였습니다.");
			
		}
		catch (Exception e) {
			System.out.println("데이터베이스 연동에 실패 하였습니다.");
			System.out.println("ERROR를 확인해주세요.");
			e.printStackTrace();
		}
	}
}
