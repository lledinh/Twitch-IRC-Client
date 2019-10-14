package com.ledinh.twitch_irc.command;

import java.io.IOException;

/**
 * Created by Lam on 25/01/2016.
 */
public class NickCommand extends Command {
    private String mNick;

    public NickCommand(String nick){
        mNick = nick;
    }

    @Override
    public String getRawCommand() {
        return "NICK " + mNick;
    }
}
