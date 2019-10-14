package com.ledinh.twitch_irc;

import java.io.IOException;

import com.ledinh.twitch_irc.command.JoinCommand;

public class Channel {
	private String mName;
	
	public Channel(String name) {
		if(name != null) mName = name.toLowerCase();
	}

	public String getName() {
		return mName;
	}
	
	public String getNameWithPrefix() {
		return "#" + mName;
	}
}
