package com.twittertest.data;

import com.twittertest.entities.Tweet;

/**
 * Interface for hashtag related data services
 * 
 * @author Nidhi_Bhola
 *
 */
public interface IHashtagDataService {

	/**
	 * Adds given tweet to database
	 */
	public void addTweetForHashtag(Tweet tweet);
}
