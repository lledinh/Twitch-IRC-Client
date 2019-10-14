package com.ledinh.twitch_irc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import com.ledinh.twitch_irc.Session.State;
import com.ledinh.twitch_irc.command.Command;
import com.ledinh.twitch_irc.command.JoinCommand;
import com.ledinh.twitch_irc.command.MessageCommand;
import com.ledinh.twitch_irc.command.PartCommand;
import com.ledinh.twitch_irc.exception.AuthentificationException;
import com.ledinh.twitch_irc.exception.ConnectionException;

public class Connection {
    private Socket mSocket;
	private BufferedReader mReader;
	private	BufferedWriter mWriter;
	
	private IConnectionBehaviour mConnectionBehaviour;
	
	public Connection() {
		mSocket = new Socket();
		mConnectionBehaviour = new BehaviourNotConnected(this);
	}
	
	public Socket getSocket() {
		return mSocket;
	}

	public BufferedReader getInput() {
		return mReader;
	}

	public BufferedWriter getOutput() {
		return mWriter;
	}

	public void setInput(BufferedReader reader) {
		mReader = reader;
	}

	public void setOutput(BufferedWriter writer) {
		mWriter = writer;
	}
	
	public void setBehaviour(IConnectionBehaviour state) {
		mConnectionBehaviour = state;
	}
	
	public void enableReadTimeout(int timeout) throws SocketException {
		mSocket.setSoTimeout(timeout);
	}
	
	public void disableReadTimeout() throws SocketException {
		mSocket.setSoTimeout(0);
	}
	
	public void connect(Server server) throws ConnectionException {
		mConnectionBehaviour.connect(server);
	}

	public String read() throws IOException {
		return mConnectionBehaviour.read();
    }
	
	public void send(String message) throws IOException {
		mConnectionBehaviour.send(message);
    }
	
	public void close() throws IOException {
		mConnectionBehaviour.close();
	}
}

interface IConnectionBehaviour {
	void connect(Server server) throws ConnectionException;
	String read() throws IOException;
	void send(String command) throws IOException;
	void close() throws IOException;
}

class BehaviourNotConnected implements IConnectionBehaviour {
	private Connection mConnection;
    private int mSocketTimeout = 10 * 1000;
	
    public BehaviourNotConnected(Connection connection) {
    	mConnection = connection;
	}
    
	@Override
	public void connect(Server server) throws ConnectionException {
		try {
			Socket socket = mConnection.getSocket();
		    SocketAddress address = new InetSocketAddress(server.getHost(), server.getPort());
		    socket.connect(address, mSocketTimeout);
		    
		    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(), server.getCharset()));
		    BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), server.getCharset()));
		    mConnection.setInput(input);
		    mConnection.setOutput(output);
		    mConnection.setBehaviour(new BehaviourConnected(mConnection));
		}
		catch(IOException e) {
			throw new ConnectionException(e.getMessage());
		}
	}

	@Override
	public String read() throws IOException {
		throw new IOException("Can't read input, socket is not connected.");
	}

	@Override
	public void send(String command) throws IOException {
		throw new IOException("Can't write to output, socket is not connected.");
	}

	@Override
	public void close() throws IOException{
		throw new IOException("Can't close socket, socket is not connected.");
	}
	
}

class BehaviourConnected implements IConnectionBehaviour {
	private Connection mConnection;

    public BehaviourConnected(Connection connection) {
    	mConnection = connection;
	}
    
	@Override
	public void connect(Server server) throws ConnectionException {
		throw new ConnectionException("Socket is already connected.");
	}

	@Override
	public String read() throws IOException {
		return mConnection.getInput().readLine();
	}

	@Override
	public void send(String command) throws IOException {
		mConnection.getOutput().write(command + Command.COMMAND_TERMINATION);
		mConnection.getOutput().flush();
	}

	@Override
	public void close() throws IOException {
		try {
			mConnection.getSocket().close();
		}
		finally {
		    mConnection.setBehaviour(new BehaviourNotConnected(mConnection));
		}
	}
	
}

