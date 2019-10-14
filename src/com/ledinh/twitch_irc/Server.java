package com.ledinh.twitch_irc;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.ledinh.twitch_irc.command.Command;

public class Server {
	private String mHost;
	private int mPort;
	private Charset mCharset;
	private List<Command> onConnectionCommands;
	
	public Server() {
		onConnectionCommands = new ArrayList<>();
	}
	
	public Server(String host, int port) {
		onConnectionCommands = new ArrayList<>();
		mHost = host;
		mPort = port;
		mCharset = Charset.forName("UTF-8");
	}

	public String getHost() {
		return mHost;
	}

	public int getPort() {
		return mPort;
	}

	public Charset getCharset() {
		return mCharset;
	}
	
	public void addOnConnectionCommand(Command command) {
		onConnectionCommands.add(command);
	}

	public List<Command> getOnConnectionCommands() {
		return onConnectionCommands;
	}
}
