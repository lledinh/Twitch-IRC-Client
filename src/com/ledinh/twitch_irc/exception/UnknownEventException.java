package com.ledinh.twitch_irc.exception;

public class UnknownEventException extends Exception {
	private static final long serialVersionUID = 3773404082477405275L;

	public UnknownEventException() {
    	
    }

    public UnknownEventException(String detailMessage) {
        super(detailMessage);
    }

    public UnknownEventException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
    
    public UnknownEventException(Throwable throwable) {
        super(throwable);
    }
}
