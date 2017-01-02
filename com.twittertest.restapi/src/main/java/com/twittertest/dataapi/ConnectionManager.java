package com.twittertest.dataapi;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {

	private static String hostName = "db4free.net:3306";
	private static String dbName = "twittertest";
	private static String username = "twittertest";
	private static String password = "twittertest";
	private Connection connection;

	private void connectToDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + hostName + "/" + dbName, username, password);
			System.out.println("Connection established!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected Connection getConnection() {
		if (connection == null) {
			connectToDB();
		}
		return connection;
	}

}
