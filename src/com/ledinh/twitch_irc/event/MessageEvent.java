package com.ledinh.twitch_irc.event;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ledinh.twitch_irc.Message;

public class MessageEvent extends IRCEvent {
	public enum Badge {
		STAFF, 
		ADMIN,
		GLOBAL_MOD,
		MODERATOR,
		SUBSCRIBER,
		TURBO
	}

	public enum UserType {
		STAFF, 
		ADMIN,
		GLOBAL_MOD,
		MODERATOR,
	}
	
	public class MessageEmote {
		private int mEmoteId; 
		private int mEmotePosBegin; 
		private int mEmotePosEnd; 
	}
	
	private String mUserName;
	private Badge[] mBadges;
	private String mColor;
	private String mUserDisplayName;
	private MessageEmote[] mEmotes;
	private String mMessageId;
	private String mRoomId;
	private String mUserId;
	private UserType mUserType;
	private String mUserMessage;
	
	// > @badges=global_mod/1,turbo/1;color=#0D4200;display-name=TWITCH_UserNaME;emotes=25:0-4,12-16/1902:6-10;mod=0;room-id=1337;subscriber=0;turbo=1;user-id=1337;user-type=global_mod 
	//:twitch_username!twitch_username@twitch_username.tmi.twitch.tv 
	// PRIVMSG #channel :Kappa Keepo Kappa
	public MessageEvent(Message message) {
		super(message);
		mUserName = getUser();
	}

	private void parseTags() {
		if(mMessage.tags != null) {
			// @tags => tags
			String tags = mMessage.tags.substring(1);
			// par1=val1;par2=val2;... => [0] = par1=val1 [1] = par2=val2...
			String[] semicolonTokens = tags.split(";");

			// badges=val
			String badgeToken = semicolonTokens[0];
			// badges => [0] = par1=val1 [1] = par2=val2...
			String badgeValues = badgeToken.substring(badgeToken.indexOf("="));

			Pattern p = Pattern.compile("([^,]*?)/(\\d)");
			Matcher matcher = p.matcher(badgeValues);
			

			while(matcher.find()) {
				String badgeType = matcher.group(1).toLowerCase();
				String badgeTypeValue = matcher.group(2);
				
				if (badgeType != null && badgeTypeValue != null) {
					switch(badgeType) {
						
					}
				}
			}
			
		}		
	}
}
