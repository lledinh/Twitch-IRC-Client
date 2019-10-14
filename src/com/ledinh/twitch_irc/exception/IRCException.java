package com.ledinh.twitch_irc.exception;

public class IRCException extends Exception {
	private static final long serialVersionUID = -6468996354578256298L;

	public IRCException() {
    	
    }

    public IRCException(String detailMessage) {
        super(detailMessage);
    }

    public IRCException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
    
    public IRCException(Throwable throwable) {
        super(throwable);
    }

}
