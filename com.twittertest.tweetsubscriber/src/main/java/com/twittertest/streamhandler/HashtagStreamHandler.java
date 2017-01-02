package com.twittertest.streamhandler;

import javax.websocket.Session;

import twitter4j.FilterQuery;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.auth.AccessToken;

/**
 * Subscribes to twitter stream for given hashtag
 * 
 * @author Nidhi_Bhola
 *
 */
public final class HashtagStreamHandler {

	private final static String CONSUMER_KEY = "aCtB3k8TVziCXI67V2GLCULan";
	private final static String CONSUMER_KEY_SECRET = "mqnIxPl54sHXh30lR54O2UDz1TZVPO5GNIvK58BPYTV2VTm75B";
	private final static String ACCESS_TOKEN = "815078418035273728-kZEJ41R5OyblNmiTcsKGKPOIybDnCui";
	private final static String ACCESS_TOKEN_SECRET = "iUorqYeK7dPGY17byatlefBErtSUEoYaBJbtCTtTyoie8";

	// twitter stream
	private static TwitterStream twitterStream;
	private static String hashtagString;

	private static void authorizeUsingExistingToken(TwitterStream twitterStream) {
		// try using existing token
		String accessToken = ACCESS_TOKEN;
		String accessTokenSecret = ACCESS_TOKEN_SECRET;
		AccessToken oathAccessToken = new AccessToken(accessToken, accessTokenSecret);
		twitterStream.setOAuthAccessToken(oathAccessToken);
	}

	/**
	 * Method to start stream for given hashtag.
	 * 
	 * @param session
	 *
	 * @param args
	 *            follow(comma separated user ids) track(comma separated filter
	 *            terms)
	 * @throws TwitterException
	 *             when Twitter service or network is unavailable
	 */
	public static void startStream(String hashtag, Session session) {
		hashtagString = hashtag;
		if (hashtagString == null || hashtagString.isEmpty()) {
			return;
		}

		// twitter stream
		if (twitterStream == null) {
			twitterStream = new TwitterStreamFactory().getInstance();
			// authorize stream
			twitterStream.setOAuthConsumer(CONSUMER_KEY, CONSUMER_KEY_SECRET);
			authorizeUsingExistingToken(twitterStream);
		}
		twitterStream.clearListeners();

		// add listener to stream
		HashtagStreamListener listener = new HashtagStreamListener(hashtagString, session);
		twitterStream.addListener(listener);

		// filter stream based on hashtag
		String[] trackArray = { hashtagString };
		twitterStream.filter(new FilterQuery(0, null, trackArray));
	}
}