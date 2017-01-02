package com.twittertest.entities;

import java.util.Date;

/**
 * Tweet Entity
 * 
 * @author Nidhi_Bhola
 *
 */
public class Tweet {
	private String hashtag;
	private String username;
	private String text;
	private Date createdAt;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date date) {
		this.createdAt = date;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

}
