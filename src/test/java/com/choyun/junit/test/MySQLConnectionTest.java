package com.choyun.junit.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

public class MySQLConnectionTest {
	
	@Test
	public void testConnection() {
		
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://10.211.55.6:3306/aop_ex?serverTimezone=Asia/Seoul";
		String user = "choyun";
		String password = "chdussld";
		
		try(Connection conn = DriverManager.getConnection(url, user, password)) {
			Class.forName(driver);
			System.out.println("데이터베이스 연동에 성공하였습니다.");
		}
		catch (ClassNotFoundException e) {
			System.out.println("Driver Loading에 실패하였습니다.");
			System.out.println("ERROR를 확인해주세요.");
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.out.println("Database Connection에 실패하였습니다.");
			System.out.println("ERROR를 확인해주세요.");
			e.printStackTrace();
		}

	}
	
}
