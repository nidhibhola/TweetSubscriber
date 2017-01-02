package com.twittertest.authorization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

@RestController
public class AuthorizationAPI {
	private final static String CONSUMER_KEY = "aCtB3k8TVziCXI67V2GLCULan";
	private final static String CONSUMER_KEY_SECRET = "mqnIxPl54sHXh30lR54O2UDz1TZVPO5GNIvK58BPYTV2VTm75B";
	private final static String ACCESS_TOKEN = "815078418035273728-kZEJ41R5OyblNmiTcsKGKPOIybDnCui";
	private final static String ACCESS_TOKEN_SECRET = "iUorqYeK7dPGY17byatlefBErtSUEoYaBJbtCTtTyoie8";

	@RequestMapping("/authorize")
	public void start() throws TwitterException, IOException {

	}

	private void authorizeUsingExistingToken(Twitter twitter) {
		// try using existing token
		String accessToken = getSavedAccessToken();
		String accessTokenSecret = getSavedAccessTokenSecret();
		AccessToken oathAccessToken = new AccessToken(accessToken, accessTokenSecret);
		twitter.setOAuthAccessToken(oathAccessToken);
	}

	private void authorizeUsingNewToken(Twitter twitter) throws TwitterException, IOException {
		RequestToken requestToken = twitter.getOAuthRequestToken();
		System.out.println("Authorization URL: \n" + requestToken.getAuthorizationURL());
		AccessToken accessToken = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (null == accessToken) {
			try {
				System.out.print("Input PIN here: ");
				String pin = br.readLine();
				accessToken = twitter.getOAuthAccessToken(requestToken, pin);
			} catch (TwitterException te) {
				System.out.println("Failed to get access token, caused by: " + te.getMessage());
				System.out.println("Retry input PIN");
			}
		}
		System.out.println("Access Token: " + accessToken.getToken());
		System.out.println("Access Token Secret: " + accessToken.getTokenSecret());
	}

	private String getSavedAccessTokenSecret() {
		// consider this is method to get your previously saved Access Token
		// Secret
		return ACCESS_TOKEN_SECRET;
	}

	private String getSavedAccessToken() {
		// consider this is method to get your previously saved Access Token
		return ACCESS_TOKEN;
	}
}