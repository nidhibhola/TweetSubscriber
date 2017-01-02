package com.twittertest.websocket;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.twittertest.streamhandler.HashtagStreamHandler;

@ServerEndpoint("/hashtagtweets")
public class HashtagWebSocketServer {

	@OnOpen
	public void open(Session session) {
	}

	@OnClose
	public void close(Session session) {
		try {
			session.close();
		} catch (IOException e) {
			Logger.getLogger(HashtagWebSocketServer.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	@OnError
	public void onError(Throwable error) {
		Logger.getLogger(HashtagWebSocketServer.class.getName()).log(Level.SEVERE, null, error);
	}

	@OnMessage
	public void handleMessage(String hashtag, Session session) {
		HashtagStreamHandler.startStream(hashtag, session);
	}
}
