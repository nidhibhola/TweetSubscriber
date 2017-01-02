package com.twittertest.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.twittertest.entities.Tweet;

/**
 * Implementation of hashtag related data services
 * 
 * @author Nidhi_Bhola
 *
 */
public class HashtagDataServiceImpl implements IHashtagDataService {
	private Connection connection;
	private ConnectionManager connectionManager;

	/**
	 * Constructor. Initializes connection.
	 */
	public HashtagDataServiceImpl() {
		connectionManager = new ConnectionManager();
		connection = connectionManager.getConnection();
	}

	/**
	 * Adds given tweet to database
	 */
	public void addTweetForHashtag(Tweet tweet) {
		try {

			// query to add row to database table
			String query = " insert into hashtag_table (hashtag, username, text, createdAt)" + " values (?, ?, ?,?)";

			// prepared statement
			PreparedStatement preparedStmt = connection.prepareStatement(query);
			java.sql.Date createdDate = new java.sql.Date(tweet.getCreatedAt().getTime());
			preparedStmt.setString(1, tweet.getHashtag());
			preparedStmt.setString(2, tweet.getUsername());
			preparedStmt.setString(3, tweet.getText());
			preparedStmt.setDate(4, createdDate);

			// executing the statement
			preparedStmt.execute();
		} catch (SQLException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
		}
	}
}