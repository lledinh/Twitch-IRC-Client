package com.ledinh.twitch_irc.command;

import com.ledinh.twitch_irc.Channel;

/**
 * Created by Lam on 29/01/2016.
 */
public class JoinCommand extends Command {
    private String mChannel;

    public JoinCommand(String channel){
        mChannel = channel;
    }

    public JoinCommand(Channel channel){
        mChannel = channel.getName();
    }
    
    @Override
    public String getRawCommand() {
        return "JOIN #" + mChannel;
    }
}
