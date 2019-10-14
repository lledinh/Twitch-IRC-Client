package com.ledinh.twitch_irc.event;

import com.ledinh.twitch_irc.Message;

public class IRCServerConnectionEvent extends IRCEvent {

	public IRCServerConnectionEvent(Message message) {
		super(message);
	}

}
