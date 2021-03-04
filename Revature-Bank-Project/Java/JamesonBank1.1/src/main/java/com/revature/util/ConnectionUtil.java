package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.postgresql.Driver;

public class ConnectionUtil {
	
	private static Logger log = Logger.getLogger(ConnectionUtil.class);

	private ConnectionUtil() {
		super();
	}

	public static Connection getConnection() {

		try {
			DriverManager.registerDriver(new Driver());
			
			String url = System.getenv("db_url");
			String username = System.getenv("db_username");
			String password = System.getenv("db_password");
			
			Connection connection = DriverManager.getConnection(url, username, password);
			
			return connection;
		} catch (SQLException e) {
			log.error("Application unable to establish connection! Exiting application... Exception message is: " + e.getMessage());
			System.exit(0);
		}
		return null;
		
	}

}
