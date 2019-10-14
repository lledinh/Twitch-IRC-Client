package com.ledinh.twitch_irc.command;

import com.ledinh.twitch_irc.Channel;
import com.ledinh.twitch_irc.User;

public class MessageCommand extends Command {
	private User mUser;
	private Channel mChannel;

    public MessageCommand(User user, Channel channel){
    	mUser = user;
    	mChannel = channel;
    }

    @Override
    public String getRawCommand() {
    	return "PRIVMSG " + mChannel.getNameWithPrefix() + mUser.getName();
    }
}
