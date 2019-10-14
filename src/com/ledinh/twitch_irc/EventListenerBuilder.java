package com.ledinh.twitch_irc;

import com.ledinh.twitch_irc.listener.SocketConnectionEventListener;
import com.ledinh.twitch_irc.listener.MessageEventListener;

public class EventListenerBuilder {
	MessageEventListener mMessageEventListener;
	SocketConnectionEventListener mConnectionEventListener;
	
	public EventListenerBuilder(String name) {
		
	}
	
	public EventListenerBuilder addListener(MessageEventListener listener) {
		mMessageEventListener = listener;
		
		return this;
	}
	
	public EventListenerBuilder addListener(SocketConnectionEventListener listener) {
		mConnectionEventListener = listener;
		
		return this;
	}
}
