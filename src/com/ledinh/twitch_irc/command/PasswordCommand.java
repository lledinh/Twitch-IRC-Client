package com.ledinh.twitch_irc.command;

/**
 * Created by Lam on 25/01/2016.
 */
public class PasswordCommand extends Command {
    private String mPassword;

    public PasswordCommand(String password){
        mPassword = password;
    }

    @Override
    public String getRawCommand() {
        return "PASS " + mPassword;
    }
}
