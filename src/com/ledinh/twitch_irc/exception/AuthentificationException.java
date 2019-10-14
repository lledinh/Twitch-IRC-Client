package com.ledinh.twitch_irc.exception;

public class AuthentificationException extends Exception {
	private static final long serialVersionUID = -7012959027704820609L;

	public AuthentificationException() {
    	
    }

    public AuthentificationException(String detailMessage) {
        super(detailMessage);
    }

    public AuthentificationException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
    
    public AuthentificationException(Throwable throwable) {
        super(throwable);
    }
}