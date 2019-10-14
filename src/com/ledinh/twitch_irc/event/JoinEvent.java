package com.ledinh.twitch_irc.event;

import com.ledinh.twitch_irc.Message;

public class JoinEvent extends IRCEvent {

	public JoinEvent(Message message) {
		super(message);
	}

}
