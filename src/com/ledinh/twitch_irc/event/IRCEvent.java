package com.ledinh.twitch_irc.event;

import com.ledinh.twitch_irc.Message;

public abstract class IRCEvent extends GenericEvent {
	protected Message mMessage;

    public IRCEvent(Message message) {
    	super();
        this.mMessage = message;
    }

	public String getUser(){
		// :twitch_username!twitch_username@twitch_username.tmi.twitch.tv
		return mMessage.origin.substring(1, mMessage.origin.indexOf("!"));
	}

	public Message getOriginalIRCMessage(){
		return mMessage;
	}
	
}
