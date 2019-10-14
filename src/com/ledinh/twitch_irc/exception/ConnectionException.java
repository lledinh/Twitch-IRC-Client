package com.ledinh.twitch_irc.exception;

public class ConnectionException extends Exception {
	private static final long serialVersionUID = 9013293782213363523L;

	public ConnectionException() {
    	
    }

    public ConnectionException(String detailMessage) {
        super(detailMessage);
    }

    public ConnectionException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
    
    public ConnectionException(Throwable throwable) {
        super(throwable);
    }
}
