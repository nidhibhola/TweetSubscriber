package com.twittertest.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles database connection
 * 
 * @author Nidhi_Bhola
 *
 */
public class ConnectionManager {

	private static String hostName = "db4free.net:3306";
	private static String dbName = "twittertest";
	private static String username = "twittertest";
	private static String password = "twittertest";
	private Connection connection;

	/**
	 * Establishes database connection
	 */
	private void connectToDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + hostName + "/" + dbName, username, password);
			Logger.getLogger("Connection established!");
		} catch (Exception ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Returns database connection.
	 * 
	 * @return
	 */
	protected Connection getConnection() {
		if (connection == null) {
			connectToDB();
		}
		return connection;
	}
}