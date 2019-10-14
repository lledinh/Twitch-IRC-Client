package com.ledinh.twitch_irc.command;

/**
 * Created by Lam on 25/01/2016.
 */
public class CAPCommand extends Command {
    public enum SubCommand {
        LS("LS"), LIST("LIST"), REQ("REQ"), ACK("ACK"), END("END");

        private String mName;

        SubCommand(String name){
            mName = name;
        }

        @Override
        public String toString() {
            return mName;
        }
    }

    private SubCommand mSubCommand;
    private String mArgument;

    public CAPCommand(SubCommand subCommand){
        mSubCommand = subCommand;
    }

    public CAPCommand(SubCommand subCommand, String argument){
        mSubCommand = subCommand;
        mArgument = argument;
    }

    @Override
    public String getRawCommand() {
        return mArgument == null ? "CAP " + mSubCommand : "CAP " + mSubCommand + " " + mArgument;
    }
}
