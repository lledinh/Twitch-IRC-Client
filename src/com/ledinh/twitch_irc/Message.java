package com.ledinh.twitch_irc;

import com.ledinh.twitch_irc.event.*;
import com.ledinh.twitch_irc.exception.ParsingException;
import com.ledinh.utils.UtilsString;

public class Message {
	MessageType messageType;

	public String rawMessage;
	public String tags;
	public String origin;
	public String target;
	public String message;

	public static boolean hasTags(String rawLine){
        return rawLine.charAt(0) == '@';
    }
    
	public static String getTags(String rawLine){
        int posFirstSpace = rawLine.indexOf(' ');
        String messageTags = rawLine.substring(0, posFirstSpace);

        return messageTags;
    }

	public static String stripTags(String rawLine){
        int posFirstSpace = rawLine.indexOf(' ');
        rawLine = rawLine.substring(posFirstSpace + 1);

        return rawLine;
    }

	@Override
	public String toString() {
		return "Message [messageType=" + messageType + ", tags=" + tags + ", origin="
				+ origin + ", target=" + target + ", message=" + message + "]";
	}
	
	public GenericEvent toEvent() {
		GenericEvent event = null;
		
		switch(messageType) {
			case RPL_WELCOME:
				event = new IRCServerConnectionEvent(this);
				break;
			case MSG_PING:
				event = new PingEvent(this);
				break;
			case MSG_CAP:
				event = new CapEvent(this);
				break;
			case MSG_JOIN:
				event = new JoinEvent(this);
				break;
			case MSG_PART:
				event = new PartEvent(this);
				break;
			case MSG_NOTICE:
				break;
			case MSG_PRIVMSG:
				event = new MessageEvent(this);
				break;
			case MSG_HOSTTARGET:
				break;
			case MSG_CLEARCHAT:
				break;
			case MSG_ROOMSTATE:
				break;
			case MSG_USERSTATE:
				break;
			case MSG_RECONNECT:
				break;
			case MSG_USERNOTICE:
				break;
			case MSG_GLOBALUSERSTATE:
				break;
				
			default:
				break;
		}
		
		return event;
	}
	
}

enum MessageCategory {
	REPLY, MESSAGE
}

enum MessageType {
	RPL_WELCOME(MessageCategory.REPLY),
	RPL_YOURHOST(MessageCategory.REPLY),
	RPL_CREATED(MessageCategory.REPLY),
	RPL_MYINFO(MessageCategory.REPLY),
	RPL_MOTDSTART(MessageCategory.REPLY),
    RPL_MOTD(MessageCategory.REPLY),
    RPL_ENDOFMOTD(MessageCategory.REPLY),
    RPL_NAMREPLY(MessageCategory.REPLY),
    RPL_ENDOFNAME(MessageCategory.REPLY),
	RPL_UNKNOWNCOMMAND(MessageCategory.REPLY),
	
	MSG_PING(MessageCategory.MESSAGE),
	MSG_CAP(MessageCategory.MESSAGE),
	MSG_JOIN(MessageCategory.MESSAGE),
	MSG_PART(MessageCategory.MESSAGE),
	MSG_PRIVMSG(MessageCategory.MESSAGE),
	MSG_NAMES(MessageCategory.MESSAGE),
	MSG_NOTICE(MessageCategory.MESSAGE),
	MSG_HOSTTARGET(MessageCategory.MESSAGE),
	MSG_CLEARCHAT(MessageCategory.MESSAGE),
	MSG_USERSTATE(MessageCategory.MESSAGE),
	MSG_RECONNECT(MessageCategory.MESSAGE),
	MSG_USERNOTICE(MessageCategory.MESSAGE),
	MSG_GLOBALUSERSTATE(MessageCategory.MESSAGE),
	MSG_ROOMSTATE(MessageCategory.MESSAGE);

	MessageCategory mCategory;

	private MessageType(MessageCategory category) {
		mCategory = category;
	}
	
	public static MessageType getFromMessage(String messageType) {
		MessageType message = null;
		
		if (UtilsString.isNumeric(messageType)) {
			switch(messageType) {
				case "001":
					message = MessageType.RPL_WELCOME;
					break;
				case "002":
					message = MessageType.RPL_YOURHOST;
					break;
				case "003":
					message = MessageType.RPL_CREATED;
					break;
				case "004":
					message = MessageType.RPL_MYINFO;
					break;
				case "375":
					message = MessageType.RPL_MOTDSTART;
					break;
				case "372":
					message = MessageType.RPL_MOTD;
					break;
				case "376":
					message = MessageType.RPL_ENDOFMOTD;
					break;
				case "353":
					message = MessageType.RPL_NAMREPLY;
					break;
				case "366":
					message = MessageType.RPL_ENDOFNAME;
					break;
				case "421":
					message = MessageType.RPL_UNKNOWNCOMMAND;
					break;	
				default:
					message = null;
					break;	
			}
		}
		else {
			try {
				message = MessageType.valueOf("MSG_" + messageType);
			}
			catch(Exception e) {
				message = null;
			}
		}
		
		return message;
	}
	
	// https://github.com/justintv/Twitch-API/blob/master/IRC.md
	public Message parse(String rawLine) throws ParsingException {
		Message message = new Message();
		message.messageType = this;
		message.rawMessage = rawLine;
		String[] tokens;
		
		switch(this) {
			case RPL_WELCOME:
				// :tmi.twitch.tv 001 twitch_username :Welcome, GLHF!
			case RPL_YOURHOST:
				// :tmi.twitch.tv 002 twitch_username :Your host is tmi.twitch.tv
			case RPL_CREATED:
				// :tmi.twitch.tv 003 twitch_username :This server is rather new
			case RPL_MYINFO:
				// :tmi.twitch.tv 004 twitch_username :-
			case RPL_MOTDSTART:
				// :tmi.twitch.tv 375 twitch_username :-
			case RPL_MOTD:
				// :tmi.twitch.tv 372 twitch_username :You are in a maze of twisty passages, all alike.
			case RPL_ENDOFMOTD:
				// :tmi.twitch.tv 376 twitch_username :>
			case RPL_NAMREPLY:
				// :twitch_username.tmi.twitch.tv 353 twitch_username = #channel :twitch_username
			case RPL_ENDOFNAME:
				// :twitch_username.tmi.twitch.tv 366 twitch_username #channel :End of /NAMES list
			case RPL_UNKNOWNCOMMAND:
				// :tmi.twitch.tv 421 twitch_username CMD :Unknown command
				tokens = rawLine.split(" ", 4);
				if (tokens.length != 4) throw new ParsingException("Error parsing message of type " + this.name());
				message.origin = tokens[0];
				// tokens[1] = type message
				message.target = tokens[2];
				message.message = tokens[3];
				break;
				
			case MSG_PING:
				// PING :tmi.twitch.tv
				tokens = rawLine.split(" ", 2);
				if (tokens.length != 2) throw new ParsingException("Error parsing message of type " + this.name());
				message.origin = tokens[0];
				message.target = tokens[1];
				break;
				
			case MSG_CAP:
				
				break;
			case MSG_JOIN:
				// :twitch_username!twitch_username@twitch_username.tmi.twitch.tv JOIN #channel
			case MSG_PART:
				// :twitch_username!twitch_username@twitch_username.tmi.twitch.tv PART #channel
				tokens = rawLine.split(" ", 3);
				if (tokens.length != 3) throw new ParsingException("Error parsing message of type " + this.name());
				message.origin = tokens[0];
				message.target = tokens[2];
				break;
				
			case MSG_NOTICE:
				// [TAGS] :tmi.twitch.tv NOTICE #channel :message
			case MSG_PRIVMSG:
				// [TAGS] :twitch_username!twitch_username@twitch_username.tmi.twitch.tv PRIVMSG #channel :message
				tokens = rawLine.split(" ", 5);
				if (tokens.length != 5) throw new ParsingException("Error parsing message of type " + this.name());
				message.tags = tokens[0];
				message.origin = tokens[1];
				message.target = tokens[3];
				message.message = tokens[4];
				break;
				
			case MSG_NAMES:
				break;
				
			case MSG_HOSTTARGET:
				// :tmi.twitch.tv HOSTTARGET #hosting_channel :target_channel [number]
				// :tmi.twitch.tv HOSTTARGET #hosting_channel :- [number]
				tokens = rawLine.split(" ", 4);
				if (tokens.length != 4) throw new ParsingException("Error parsing message of type " + this.name());
				message.origin = tokens[0];
				message.target = tokens[2];
				message.message = tokens[3];
				break;
				
			case MSG_CLEARCHAT:
				tokens = rawLine.split(" ", 5);
				// :tmi.twitch.tv CLEARCHAT #channel
				if (tokens.length == 3) {
					message.origin = tokens[0];
					message.target = tokens[2];
				}
				// [TAGS] :tmi.twitch.tv CLEARCHAT #channel :target_username
				else if (tokens.length == 5) {
					message.tags = tokens[0];
					message.origin = tokens[1];
					message.target = tokens[3];
					message.message = tokens[4];
				}
				else {
					throw new ParsingException("Error parsing message of type " + this.name());
				}
				break;

			case MSG_ROOMSTATE:
				// [TAGS] :tmi.twitch.tv ROOMSTATE #channel
			case MSG_USERSTATE:
				// [TAGS] :tmi.twitch.tv USERSTATE #channel
				tokens = rawLine.split(" ", 4);
				if (tokens.length != 4) {
					throw new ParsingException("Error parsing message of type " + this.name());
				}
				message.tags = tokens[0];
				message.origin = tokens[1];
				message.target = tokens[3];
				break;
				
			case MSG_RECONNECT:
				// :tmi.twitch.tv RECONNECT ?
				tokens = rawLine.split(" ", 2);
				if (tokens.length != 2) {
					throw new ParsingException("Error parsing message of type " + this.name());
				}
				message.origin = tokens[0];
				break;
				
			case MSG_USERNOTICE:
				tokens = rawLine.split(" ", 5);
				// [TAGS] :tmi.twitch.tv USERNOTICE #channel
				if (tokens.length == 4) {
					message.tags = tokens[0];
					message.origin = tokens[1];
					message.target = tokens[3];
				}
				// [TAGS] :tmi.twitch.tv USERNOTICE #channel [:message]
				else if (tokens.length == 5) {
					message.tags = tokens[0];
					message.origin = tokens[1];
					message.target = tokens[3];
					message.message = tokens[4];
				}
				else {
					throw new ParsingException("Error parsing message of type " + this.name());
				}
				break;

			case MSG_GLOBALUSERSTATE:
				// [TAGS] :tmi.twitch.tv GLOBALUSERSTATE
				tokens = rawLine.split(" ", 3);
				if (tokens.length != 3) {
					throw new ParsingException("Error parsing message of type " + this.name());
				}
				message.tags = tokens[0];
				message.origin = tokens[1];
				break;
				
			default:
				break;
		}
		
		return message;
	}
}