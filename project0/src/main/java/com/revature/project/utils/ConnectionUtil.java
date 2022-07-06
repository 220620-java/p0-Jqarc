package com.revature.project.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

	private static ConnectionUtil connUtil;
	private Properties props;
	
	private ConnectionUtil() {
		
	}
	
	public static synchronized ConnectionUtil getConnectionUtil() {
		if (connUtil == null) {
			connUtil = new ConnectionUtil();
		}
		return connUtil;
	}
	
	public Connection getConnection() {
		
		Connection conn = null;
		
		String dbUrl = System.getenv("DB_URL");
		String dbUser = System.getenv("DB_USER");
		String dbPass = System.getenv("DB_PASS");
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(
					dbUrl,
					dbUser,
					dbPass);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
