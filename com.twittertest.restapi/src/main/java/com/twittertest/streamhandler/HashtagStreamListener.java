/*******************************************************************************
 * © 2016 Infosys Limited, Bangalore, India. All Rights Reserved. 
 * Infosys Knowledge Platform Version: 1.1.3
 * 
 * Except for any free or open source software components embedded in this 
 * Infosys proprietary software program, this Program is protected 
 * by copyright laws, international treaties and other pending or existing 
 * intellectual property rights in India, the United States and other countries. 
 * Except as expressly permitted, any unauthorized reproduction, storage, 
 * transmission in any form or by any means (including without limitation 
 * electronic, mechanical, printing, photocopying, recording or otherwise), 
 * or any distribution of this Program, or any portion of it, may result in 
 * severe civil and criminal penalties, and will be prosecuted to the maximum 
 * extent possible under the law.
 ******************************************************************************/

package com.twittertest.streamhandler;

import java.io.IOException;

import javax.websocket.Session;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.twittertest.dataapi.HashtagDataServiceImpl;
import com.twittertest.dataapi.IHashtagDataService;
import com.twittertest.restapi.entities.Tweet;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

public class HashtagStreamListener implements StatusListener {

	protected Session session;
	protected IHashtagDataService dataService;
	protected String hashtag;

	public HashtagStreamListener(String hashtagString, Session session) {
		this.session = session;
		this.hashtag = hashtagString;
		dataService = new HashtagDataServiceImpl();
	}

	public void onStatus(Status status) {
		System.out.println("Tweet - ");
		System.out.println("\tCreatedAt: " + status.getCreatedAt());
		System.out.println("\tSource: " + status.getSource());
		System.out.println("\tHashtagEntities: " + status.getHashtagEntities());
		System.out.println("\t@" + status.getUser().getScreenName() + " - " + status.getText());

		try {
			// new tweet entity
			Tweet tweet = new Tweet();
			tweet.setCreatedAt(status.getCreatedAt());
			tweet.setText(status.getText());
			tweet.setUsername(status.getUser().getScreenName());
			tweet.setHashtag(hashtag);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jsonString = gson.toJson(tweet);

			session.getBasicRemote().sendText(jsonString);

			// adding to database
			dataService.addTweetForHashtag(tweet);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
		System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
	}

	public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
		System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
	}

	public void onScrubGeo(long userId, long upToStatusId) {
		System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
	}

	public void onStallWarning(StallWarning warning) {
		System.out.println("Got stall warning:" + warning);
	}

	public void onException(Exception ex) {
		ex.printStackTrace();
	}
}
