package com.ledinh.twitch_irc;

import com.ledinh.twitch_irc.exception.ParsingException;
public class MessageParser {
	
	public MessageParser() {
		
	}
	
    public Message parse(String rawLine) throws ParsingException {
        MessageType messageType = getMessageType(rawLine);
        
        if (messageType == null) {
        	throw new ParsingException("Unknown message type. Message = " + rawLine);
        }
        else {
            Message message = messageType.parse(rawLine);
            return message;
        }
    }

	private MessageType getMessageType(String rawLine) {
		// On enleve les tags du message pour avoir un message IRC "classique"
		if(Message.hasTags(rawLine)) {
			rawLine = Message.stripTags(rawLine);
		}
		
    	// Le type de message est situé dans le premier (cas PING) ou deuxième mot
    	// On récupère les deux premiers mots, on peut ignorer le reste.
    	String[] tokens = rawLine.split(" ", 3);

    	MessageType messageType = null;
    	// La majorité des messages ont leurs type de commande définie dans le deuxième mot.
    	// On commence donc par ce cas.
    	messageType = MessageType.getFromMessage(tokens[1]);
    	if(messageType == null) {
        	// Cas où le type de commande est définie dans le premier mot.
    		messageType = MessageType.getFromMessage(tokens[0]);
    	}
		
    	return messageType;
    }
	
	
}

/*
public class Parser {
	public Parser() {
	}
	
    public void parse(String rawLine) {
        String tags;
        if (hasMessageTags(rawLine)) {
            tags = getTags(rawLine);
            rawLine = stripTags(rawLine);
        }

        String[] tokens = splitLine(rawLine);
    }

    private boolean hasMessageTags(String rawLine){
        return rawLine.charAt(0) == '@';
    }
    
    private String getTags(String rawLine){
        int posFirstSpace = rawLine.indexOf(' ');
        String messageTags = rawLine.substring(0, posFirstSpace);

        return messageTags;
    }

    private String stripTags(String rawLine){
        int posFirstSpace = rawLine.indexOf(' ');
        rawLine = rawLine.substring(posFirstSpace + 1);

        return rawLine;
    }

    private String[] splitLine(String rawLine){
        return rawLine.split(" ", 4);
    }

    private void checkPingCommand(String rawLine, String[] tokens){
        String command = tokens[0].toUpperCase();
        String origin = tokens[1];
        if(tokens.length >= 2 && command.equals("PING")) {
            PingEvent pingEvent = new PingEvent(rawLine, origin, command);
        }
    }
}*/
