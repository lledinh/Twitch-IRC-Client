package com.ledinh.twitch_irc;

public class User {
	private String mName;
	private String mPassword;
	
	public User(String name, String password) {
		mName = name;
		mPassword = password;
	}

	public String getName() {
		return mName;
	}
	
	public String getPassword() {
		return mPassword;
	}
}
