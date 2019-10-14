package com.ledinh.twitch_irc;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import com.ledinh.twitch_irc.command.NickCommand;
import com.ledinh.twitch_irc.command.PasswordCommand;
import com.ledinh.twitch_irc.event.AuthenticationSuccessEvent;
import com.ledinh.twitch_irc.exception.AuthentificationException;
import com.ledinh.twitch_irc.exception.ParsingException;

public class Authentication {
	private Session mSession;
	private int mAnswerTimeout = 10000;
	
	public Authentication(Session session) {
		mSession = session;
	}
	
	public void auth(User user) throws AuthentificationException {
		try {
			PasswordCommand passCommand = new PasswordCommand(user.getPassword());
			NickCommand nickCommand = new NickCommand(user.getName());

			mSession.send(passCommand);
			mSession.send(nickCommand);
			waitServerResponse();
		} catch (Exception e) {
			throw new AuthentificationException(e.getMessage());
		}
	}
	
	private void waitServerResponse() throws AuthentificationException, IOException, ParsingException {
		mSession.getConnection().enableReadTimeout(mAnswerTimeout);
		String answer = mSession.getConnection().read();
		MessageParser messageParser = new MessageParser();
		Message message = messageParser.parse(answer);
		
		if (!authenticationSuccess(message)) {
			throw new AuthentificationException("Failed to authenticate. Answer: " + answer);
		}
		else {
			EventManager.INSTANCE.send(new AuthenticationSuccessEvent());
			EventManager.INSTANCE.send(message.toEvent());
		}
	}
	
	private boolean authenticationSuccess(Message message) {
		return message.messageType == MessageType.RPL_WELCOME;
	}
}
