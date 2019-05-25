package com.choyun.junit.test;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })

public class DataSourceTest {
	
	@Inject
	private DataSource ds;
	
	@Test
	public void testConnection() {
		
		try (Connection conn = ds.getConnection()) {
			System.out.println("데이터베이스 연동에 성공 하였습니다.");
			
		}
		catch (SQLException e) {
			System.out.println("데이터베이스 연동에 실패 하였습니다.");
			System.out.println("ERROR를 확인해주세요.");
			e.printStackTrace();
		}
	}
	
}
