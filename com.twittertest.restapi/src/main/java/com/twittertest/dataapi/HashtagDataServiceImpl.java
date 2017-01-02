package com.twittertest.dataapi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.twittertest.restapi.entities.Tweet;

public class HashtagDataServiceImpl implements IHashtagDataService {
	private Connection connection;
	private ConnectionManager connectionManager;

	public HashtagDataServiceImpl() {
		connectionManager = new ConnectionManager();
		connection = connectionManager.getConnection();
	}

	public void addTweetForHashtag(Tweet tweet) {
		try {
			java.sql.Date createdDate = new java.sql.Date(tweet.getCreatedAt().getTime());

			// the mysql insert statement
			String query = " insert into hashtag_table (hashtag, username, text, createdAt)" + " values (?, ?, ?,?)";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, tweet.getHashtag());
			preparedStmt.setString(2, tweet.getUsername());
			preparedStmt.setString(3, tweet.getText());
			preparedStmt.setDate(4, createdDate);

			// execute the preparedstatement
			preparedStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Statement stmt = null;
		try {
			Connection connection1 = new HashtagDataServiceImpl().connection;
			// STEP 4: Execute a query
			System.out.println("Creating table in given database...");
			stmt = connection1.createStatement();

			String sql = "DROP TABLE hashtag_table ";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE hashtag_table " + "(id INTEGER not NULL AUTO_INCREMENT, " + " hashtag VARCHAR(255), "
					+ " username VARCHAR(255), " + " text VARCHAR(255), " + " createdAt DATE, "
					+ " PRIMARY KEY ( id ))";

			stmt.executeUpdate(sql);

			java.util.Date date = new java.util.Date();
			java.sql.Date createdDate = new java.sql.Date(date.getTime());

			// the mysql insert statement
			// String query = " insert into hashtag_table (hashtag, username,
			// text, createdAt)" + " values (?, ?, ?,?)";
			//
			// // create the mysql insert preparedstatement
			// PreparedStatement preparedStmt =
			// connection1.prepareStatement(query);
			// preparedStmt.setString(1, "dummy");
			// preparedStmt.setString(2, "dummy");
			// preparedStmt.setString(3, "dummy");
			// preparedStmt.setDate(4, createdDate);
			//
			// // execute the preparedstatement
			// preparedStmt.execute();

			System.out.println("Created table in given database...");
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
}