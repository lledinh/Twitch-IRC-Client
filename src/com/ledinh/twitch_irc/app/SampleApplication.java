package com.ledinh.twitch_irc.app;

import java.io.IOException;

import com.ledinh.twitch_irc.Channel;
import com.ledinh.twitch_irc.EventManager;
import com.ledinh.twitch_irc.TwitchIRC;
import com.ledinh.twitch_irc.User;
import com.ledinh.twitch_irc.command.CAPCommand;
import com.ledinh.twitch_irc.command.Command;
import com.ledinh.twitch_irc.event.MessageEvent;
import com.ledinh.twitch_irc.event.SocketConnectionEvent;
import com.ledinh.twitch_irc.command.CAPCommand.SubCommand;
import com.ledinh.twitch_irc.exception.AuthentificationException;
import com.ledinh.twitch_irc.exception.ConnectionException;
import com.ledinh.twitch_irc.listener.MessageEventListener;
import com.ledinh.twitch_irc.listener.SocketConnectionEventListener;

public class SampleApplication {

	public static void main(String args[]) {
		// http://twitchapps.com/tmi/
		User user = new User("tachikomakun", "oauth:mana04ei8vd5na6fagyju0ezu67ylu");
		Channel channel = new Channel("imaqtpie");
		Channel channel2 = new Channel("lirik");

		MessageEventListener l1 = new MessageEventListener() {
			@Override
			public void onEvent(MessageEvent event) {
				System.out.println(event.getUser() + " " + event.getOriginalIRCMessage().message);
			}
		};
		
		SocketConnectionEventListener l2 = new SocketConnectionEventListener() {
			@Override
			public void onEvent(SocketConnectionEvent event) {
				System.out.println("SampleApplication --- SocketConnectionEventListener");
			}
		};

		EventManager.INSTANCE.registerListener(l1);
		EventManager.INSTANCE.registerListener(l2);
		
		TwitchIRC irc = new TwitchIRC();
		try {
			irc.connect(user);
			irc.join(channel);
			irc.join(channel2);
		} catch (ConnectionException | AuthentificationException | IOException e) {
			e.printStackTrace();
		}
	}
}
