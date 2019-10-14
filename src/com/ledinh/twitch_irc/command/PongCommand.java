package com.ledinh.twitch_irc.command;

/**
 * Created by Lam on 31/01/2016.
 */
public class PongCommand extends Command {
    public String mOrigin;

    public PongCommand(String origin){
        this.mOrigin = origin;
    }

    @Override
    public String getRawCommand() {
        return "PONG " + mOrigin;
    }
}
