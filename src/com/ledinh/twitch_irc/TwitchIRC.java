package com.ledinh.twitch_irc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

import com.ledinh.twitch_irc.Session.State;
import com.ledinh.twitch_irc.command.CAPCommand;
import com.ledinh.twitch_irc.command.Command;
import com.ledinh.twitch_irc.command.JoinCommand;
import com.ledinh.twitch_irc.command.MessageCommand;
import com.ledinh.twitch_irc.command.PartCommand;
import com.ledinh.twitch_irc.command.CAPCommand.SubCommand;
import com.ledinh.twitch_irc.exception.AuthentificationException;
import com.ledinh.twitch_irc.exception.ConnectionException;

interface ISendableCommands {
	void join(Channel channel) throws IOException;
	void message(Channel channel) throws IOException;
	void leave(Channel channel) throws IOException;
}

public class TwitchIRC implements ISendableCommands {

	private Session mSession;
	private Server mTwitchServer;
	private ISendableCommands mSendableCommands;
	
	public TwitchIRC() {
		mSession = new Session();
		mTwitchServer = new Server("irc.twitch.tv", 6667);
		mTwitchServer.addOnConnectionCommand(new CAPCommand(SubCommand.REQ, ":twitch.tv/commands"));
		mTwitchServer.addOnConnectionCommand(new CAPCommand(SubCommand.REQ, ":twitch.tv/membership"));
		mTwitchServer.addOnConnectionCommand(new CAPCommand(SubCommand.REQ, ":twitch.tv/tags"));
    	
		// Filtre message par nom...?
	}
	
	public void connect(User user) throws ConnectionException, AuthentificationException, IOException {
		mSession.begin(mTwitchServer, user);
	}

	@Override
	public void join(Channel channel) throws IOException {
		mSession.send(new JoinCommand(channel));
	}

	@Override
	public void message(Channel channel) throws IOException {
		mSession.send(new MessageCommand(mSession.getLoggedUser(), channel));
	}

	@Override
	public void leave(Channel channel) throws IOException {
		mSession.send(new PartCommand(channel));
	}
}
