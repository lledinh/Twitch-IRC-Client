package com.ledinh.twitch_irc.command;

/**
 * Created by Lam on 28/01/2016.
 */
public class ReplyCodes {
    public static Integer[] CONNECT_REPLIES = new Integer[] {
            ReplyCodes.RPL_WELCOME,
            ReplyCodes.RPL_YOURHOST,
            ReplyCodes.RPL_CREATED,
            ReplyCodes.RPL_MYINFO
    };

    public static final int RPL_WELCOME = 001;
    public static final int RPL_YOURHOST = 002;
    public static final int RPL_CREATED = 003;
    public static final int RPL_MYINFO = 004;

    public static final int RPL_MOTDSTART = 375;
    public static final int RPL_MOTD = 372;
    public static final int RPL_ENDOFMOTD = 376;

    public static final int ERR_UNKNOWNCOMMAND 	= 421;
}
