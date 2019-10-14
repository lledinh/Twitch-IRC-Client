package com.ledinh.twitch_irc.command;

/**
 * Created by Lam on 29/01/2016.
 */
public class QuitCommand extends Command {

    public QuitCommand(){

    }

    @Override
    public String getRawCommand() {
        return "QUIT";
    }
}
