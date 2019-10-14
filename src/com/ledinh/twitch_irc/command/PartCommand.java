package com.ledinh.twitch_irc.command;

import com.ledinh.twitch_irc.Channel;

/**
 * Created by Lam on 30/01/2016.
 */
public class PartCommand extends Command {
    private Channel mChannel;

    public PartCommand(Channel channel){
        mChannel = channel;
    }

    @Override
    public String getRawCommand() {
        return "PART " + mChannel.getNameWithPrefix();
    }
}