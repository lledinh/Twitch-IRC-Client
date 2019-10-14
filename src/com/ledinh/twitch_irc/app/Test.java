package com.ledinh.twitch_irc.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String tags = "@badges=global_mod/1,turbo/1;color=#0D4200;display-name=TWITCH_UserNaME;emotes=25:0-4,12-16/1902:6-10;mod=0;room-id=1337;subscriber=0;turbo=1;user-id=1337;user-type=global_mod";
		
		tags = tags.substring(1);
		// par1=val1;par2=val2;... => [0] = par1=val1 [1] = par2=val2...
		String[] semicolonTokens = tags.split(";");

		// badges=val
		String badgeToken = semicolonTokens[0];
		// badges=val => val
		String badgeValues = badgeToken.substring(badgeToken.indexOf("=") + 1);

		// 
		Pattern p = Pattern.compile("([^,]*?)/(\\d)");
		Matcher matcher = p.matcher(badgeValues);
		
		while(matcher.find()) {
			System.out.println(matcher.group(0));
			System.out.println(matcher.group(1));
			System.out.println(matcher.group(2));	
		}
	}

}
