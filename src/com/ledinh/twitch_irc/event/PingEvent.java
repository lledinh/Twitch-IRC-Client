package com.ledinh.twitch_irc.event;

import com.ledinh.twitch_irc.Message;

public class PingEvent extends IRCEvent {
	
	public PingEvent(Message message) {
		super(message);
	}

}
