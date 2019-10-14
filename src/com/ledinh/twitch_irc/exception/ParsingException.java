package com.ledinh.twitch_irc.exception;

public class ParsingException extends Exception {
	private static final long serialVersionUID = 942114492076453231L;

	public ParsingException() {
    	
    }

    public ParsingException(String detailMessage) {
        super(detailMessage);
    }

    public ParsingException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
    
    public ParsingException(Throwable throwable) {
        super(throwable);
    }
}
