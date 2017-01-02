package com.twittertest.websocket;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.twittertest.streamhandler.HashtagStreamHandler;

/**
 * This websocket server accepts given hashtag and calls handlers to subscribe
 * for the given hashtag.
 * 
 * @author Nidhi_Bhola
 *
 */
@ServerEndpoint("/hashtagtweets")
public class HashtagWebSocketServer {

	@OnOpen
	public void open(Session session) {
	}

	@OnClose
	public void close(Session session) {
		HashtagStreamHandler.stopStream();
	}

	@OnError
	public void onError(Throwable error) {
		HashtagStreamHandler.stopStream();
		Logger.getLogger(HashtagWebSocketServer.class.getName()).log(Level.SEVERE, null, error);
	}

	@OnMessage
	public void handleMessage(String hashtag, Session session) {
		HashtagStreamHandler.startStream(hashtag, session);
	}
}
