package com.ledinh.twitch_irc;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import com.ledinh.twitch_irc.command.Command;
import com.ledinh.twitch_irc.command.PongCommand;
import com.ledinh.twitch_irc.event.AuthenticationSuccessEvent;
import com.ledinh.twitch_irc.event.PingEvent;
import com.ledinh.twitch_irc.event.SocketConnectionEvent;
import com.ledinh.twitch_irc.exception.AuthentificationException;
import com.ledinh.twitch_irc.exception.ConnectionException;
import com.ledinh.twitch_irc.exception.ParsingException;
import com.ledinh.twitch_irc.exception.UnknownEventException;
import com.ledinh.twitch_irc.listener.SocketConnectionEventListener;
import com.ledinh.twitch_irc.listener.AuthenticationSuccessEventListener;
import com.ledinh.twitch_irc.listener.PingEventListener;


public class Session {
	public enum State {
		DISCONNECTED, 
		CONNECTING, 
		AUTHENTIFICATION,
		CONNECTED
	}
	
	class History {
		class Received {
			
		}
		
		class Sent {
			
		}
	}

	private AuthenticationSuccessEventListener mAuthenticationSuccessListener;
	private PingEventListener mPingEventListener;
	
	private Connection mConnection;
	private State mState;
	private User mLoggedUser;
	private Server mServer;
	private List<Channel> mChannelsJoined;
	
	private Authentication mAuthentication;
	private boolean mEnableHistory;
	
	public Session() {
		this(true);
	}
	
	public Session(boolean enableHistory) {
		mState = State.DISCONNECTED;
		mChannelsJoined = new ArrayList<>();
		mEnableHistory = enableHistory;
		mConnection = new Connection();
		
		mAuthenticationSuccessListener = new AuthenticationSuccessEventListener() {
			@Override
			public void onEvent(AuthenticationSuccessEvent event) {
				onAuthenticationSuccessEvent();
			}
		};
		
		mPingEventListener = new PingEventListener() {
			@Override
			public void onEvent(PingEvent event) {
				onPingEvent(event);
			}
		};

		EventManager.INSTANCE.registerListener(mAuthenticationSuccessListener);
		EventManager.INSTANCE.registerListener(mPingEventListener);
	}

	private void onAuthenticationSuccessEvent() {
		try {
			getConnection().disableReadTimeout();
			mState = State.CONNECTED;
		    listen();
		    for(Command command : mServer.getOnConnectionCommands()) {
		    	send(command);
		    }
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void onPingEvent(PingEvent event) {
		System.out.println("Session --- PingEvent");
		try {
			send(new PongCommand(event.getOriginalIRCMessage().origin));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return mConnection;
	}
	
	public List<Channel> getChannelsJoined() {
		return mChannelsJoined;
	}
	
	public User getLoggedUser() {
		return mLoggedUser;
	}
	
	public void begin(Server server, User user) throws ConnectionException, AuthentificationException {
		try {
			mServer = server;
			System.out.println("Connecting to server...");
			connect(server);
			System.out.println("Authenticating...");
			mLoggedUser = authenticate(user);
			System.out.println("Authenticating done..");
			System.out.println("User = " + mLoggedUser.getName());
		}
		catch(ConnectionException | AuthentificationException e) {
			close();
			throw(e);
		}
	}
	
	private void connect(Server server) throws ConnectionException {
		mState = State.CONNECTING;
		mConnection.connect(server);
		EventManager.INSTANCE.send(new SocketConnectionEvent());
	}
	
	private User authenticate(User user) throws AuthentificationException {
		mState = State.AUTHENTIFICATION;
		mAuthentication = new Authentication(this);
		mAuthentication.auth(user);
		return user;
	}

	private void listen() {
		MessageParser parser = new MessageParser();
		new Thread() {
			@Override
			public void run() {
				try {
					String inputMessage;
					while ((inputMessage = mConnection.read()) != null) {
						try {
							Message message = parser.parse(inputMessage);
							//System.out.println(inputMessage);
							EventManager.INSTANCE.send(message.toEvent());
						}
						catch(ParsingException e) {
							e.printStackTrace();
						}
					}
				}
				catch(IOException e) {
					e.printStackTrace();
				}
				finally {
					close();
				}
			}
		}.start();
		//mState = State.CONNECTED;
	}
	
	public void send(Command command) throws IOException {
		mConnection.send(command.getRawCommand());
    }
	
	public void close() {
		try {
			mState = State.DISCONNECTED;
			mConnection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

interface ISessionBehaviour {
	void open(Server server, User user) throws ConnectionException, AuthentificationException, IOException;
	void close() throws IOException;
	void send(Command command) throws IOException;
}
