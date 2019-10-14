package com.ledinh.twitch_irc.command;

/**
 * Created by Lam on 25/01/2016.
 */
public class RawCommand extends Command {
    private String mRawLine;

    public RawCommand(String rawLine){
        mRawLine = rawLine;
    }

    @Override
    public String getRawCommand() {
        return mRawLine;
    }
}
