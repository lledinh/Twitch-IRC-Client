package com.ledinh.twitch_irc.command;

/**
 * Created by Lam on 25/01/2016.
 */
public abstract class Command {
    public static final String COMMAND_TERMINATION = "\r\n";

    public abstract String getRawCommand();
}
