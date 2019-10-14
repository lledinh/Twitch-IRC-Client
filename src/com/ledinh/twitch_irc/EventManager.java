package com.ledinh.twitch_irc;

import java.util.ArrayList;
import java.util.List;

import com.ledinh.twitch_irc.event.*;
import com.ledinh.twitch_irc.exception.UnknownEventException;
import com.ledinh.twitch_irc.listener.*;

public enum EventManager {
	INSTANCE;

	// todo event listener builderbuilder
	private List<AuthenticationFailedEventListener> authenticationFailedEventListeners = new ArrayList<>();
	private List<AuthenticationSuccessEventListener> authenticationSuccessEventListeners = new ArrayList<>();
	private List<CapEventListener> capEventListeners = new ArrayList<>();
	private List<ChannelSuspendedEventListener> channelSuspendedEventListeners = new ArrayList<>();
	private List<DisconnectionEventListener> disconnectionEventListeners = new ArrayList<>();
	private List<IRCServerConnectionEventListener> ircServerConnectionEventListeners = new ArrayList<>();
	private List<JoinEventListener> joinEventListeners = new ArrayList<>();
	private List<MessageEventListener> messageEventListeners = new ArrayList<>();
	private List<PartEventListener> partEventListeners = new ArrayList<>();
	private List<PingEventListener> pingEventListeners = new ArrayList<>();
	private List<SocketConnectionEventListener> socketConnectionEventListeners = new ArrayList<>();
	private List<UserBannedEventListener> userBannedEventListeners = new ArrayList<>();
	private List<UserTimeoutEventListener> userTimeoutEventListeners = new ArrayList<>();
	private List<UserUnbannedEventListener> userUnbannedEventListeners = new ArrayList<>();
	
	private List<EmoteOnlyModeOffEventListener> emoteOnlyModeOffEventListeners = new ArrayList<>();
	private List<EmoteOnlyModeOnEventListener> emoteOnlyModeOnEventListeners = new ArrayList<>();
	private List<HostModeOffEventListener> hostModeOffEventListeners = new ArrayList<>();
	private List<HostModeOnEventListener> hostModeOnEventListeners = new ArrayList<>();
	private List<R9KModeOffEventListener> r9kModeOffEventListeners = new ArrayList<>();
	private List<R9KModeOnEventListener> r9kModeOnEventListeners = new ArrayList<>();
	private List<SlowModeOffEventListener> slowModeOffEventListeners = new ArrayList<>();
	private List<SlowModeOnEventListener> slowModeOnEventListeners = new ArrayList<>();
	private List<SubscriberOnlyModeOffEventListener> subscriberOnlyModeOffEventListeners = new ArrayList<>();
	private List<SubscriberOnlyModeOnEventListener> subscriberOnlyModeOnEventListeners = new ArrayList<>();

	public void registerListener(AuthenticationSuccessEventListener eventListener) {
		authenticationSuccessEventListeners.add(eventListener);
	}

	public void registerListener(AuthenticationFailedEventListener eventListener) {
		authenticationFailedEventListeners.add(eventListener);
	}
	
	public void registerListener(CapEventListener eventListener) {
		capEventListeners.add(eventListener);
	}

	public void registerListener(ChannelSuspendedEventListener eventListener) {
		channelSuspendedEventListeners.add(eventListener);
	}
	
	public void registerListener(SocketConnectionEventListener eventListener) {
		socketConnectionEventListeners.add(eventListener);
	}

	public void registerListener(DisconnectionEventListener eventListener) {
		disconnectionEventListeners.add(eventListener);
	}

	public void registerListener(JoinEventListener eventListener) {
		joinEventListeners.add(eventListener);
	}

	public void registerListener(MessageEventListener eventListener) {
		messageEventListeners.add(eventListener);
	}

	public void registerListener(PartEventListener eventListener) {
		partEventListeners.add(eventListener);
	}

	public void registerListener(PingEventListener eventListener) {
		pingEventListeners.add(eventListener);
	}

	public void registerListener(UserBannedEventListener eventListener) {
		userBannedEventListeners.add(eventListener);
	}
	
	public void registerListener(UserTimeoutEventListener eventListener) {
		userTimeoutEventListeners.add(eventListener);
	}

	public void registerListener(UserUnbannedEventListener eventListener) {
		userUnbannedEventListeners.add(eventListener);
	}
	
	public void registerListener(EmoteOnlyModeOffEventListener eventListener) {
		emoteOnlyModeOffEventListeners.add(eventListener);
	}
	
	public void registerListener(EmoteOnlyModeOnEventListener eventListener) {
		emoteOnlyModeOnEventListeners.add(eventListener);
	}
	
	public void registerListener(HostModeOffEventListener eventListener) {
		hostModeOffEventListeners.add(eventListener);
	}
	
	public void registerListener(HostModeOnEventListener eventListener) {
		hostModeOnEventListeners.add(eventListener);
	}
	
	public void registerListener(R9KModeOffEventListener eventListener) {
		r9kModeOffEventListeners.add(eventListener);
	}
	
	public void registerListener(R9KModeOnEventListener eventListener) {
		r9kModeOnEventListeners.add(eventListener);
	}
	
	public void registerListener(SlowModeOffEventListener eventListener) {
		slowModeOffEventListeners.add(eventListener);
	}
	
	public void registerListener(SlowModeOnEventListener eventListener) {
		slowModeOnEventListeners.add(eventListener);
	}
	
	public void registerListener(SubscriberOnlyModeOffEventListener eventListener) {
		subscriberOnlyModeOffEventListeners.add(eventListener);
	}
	
	public void registerListener(SubscriberOnlyModeOnEventListener eventListener) {
		subscriberOnlyModeOnEventListeners.add(eventListener);
	}

	public boolean send(GenericEvent event) {
		boolean handled = true;
		
		if (event instanceof AuthenticationFailedEvent) 
			send((AuthenticationFailedEvent) event);

		else if (event instanceof AuthenticationSuccessEvent) 
			send((AuthenticationSuccessEvent) event);
		
		else if (event instanceof CapEvent) 
			send((CapEvent) event);
		
		else if (event instanceof ChannelSuspendedEvent) 
			send((ChannelSuspendedEvent) event);
		
		else if (event instanceof DisconnectionEvent) 
			send((DisconnectionEvent) event);
		
		else if (event instanceof EmoteOnlyModeOffEvent) 
			send((EmoteOnlyModeOffEvent) event);
		
		else if (event instanceof EmoteOnlyModeOnEvent) 
			send((EmoteOnlyModeOnEvent) event);
		
		else if (event instanceof HostModeOffEvent) 
			send((HostModeOffEvent) event);
		
		else if (event instanceof HostModeOnEvent) 
			send((HostModeOnEvent) event);

		else if (event instanceof IRCServerConnectionEvent) 
			send((IRCServerConnectionEvent) event);
		
		else if (event instanceof JoinEvent) 
			send((JoinEvent) event);
		
		else if (event instanceof MessageEvent) 
			send((MessageEvent) event);
		
		else if (event instanceof PartEvent) 
			send((PartEvent) event);
		
		else if (event instanceof PingEvent) 
			send((PingEvent) event);
		
		else if (event instanceof R9KModeOffEvent) 
			send((R9KModeOffEvent) event);
		
		else if (event instanceof R9KModeOnEvent) 
			send((R9KModeOnEvent) event);
		
		else if (event instanceof SlowModeOffEvent) 
			send((SlowModeOffEvent) event);
		
		else if (event instanceof SlowModeOnEvent) 
			send((SlowModeOnEvent) event);

		else if (event instanceof SocketConnectionEvent) 
			send((SocketConnectionEvent) event);
		
		else if (event instanceof SubscriberOnlyModeOffEvent) 
			send((SubscriberOnlyModeOffEvent) event);
		
		else if (event instanceof SubscriberOnlyModeOnEvent) 
			send((SubscriberOnlyModeOnEvent) event);
		
		else if (event instanceof UserBannedEvent) 
			send((UserBannedEvent) event);
		
		else if (event instanceof UserTimeoutEvent) 
			send((UserTimeoutEvent) event);
		
		else if (event instanceof UserUnbannedEvent) 
			send((UserUnbannedEvent) event);
		
		else {
			handled = false;
		}
		
		return handled;
	}

	private void send(AuthenticationFailedEvent event) {
		for (AuthenticationFailedEventListener eventListener : authenticationFailedEventListeners) {
			eventListener.onEvent(event);
		}
	}
	
	private void send(AuthenticationSuccessEvent event) {
		for (AuthenticationSuccessEventListener eventListener : authenticationSuccessEventListeners) {
			eventListener.onEvent(event);
		}
	}
	
	private void send(CapEvent event) {
		for (CapEventListener eventListener : capEventListeners) {
			eventListener.onEvent(event);
		}
	}
	
	private void send(ChannelSuspendedEvent event) {
		for (ChannelSuspendedEventListener eventListener : channelSuspendedEventListeners) {
			eventListener.onEvent(event);
		}
	}

	private void send(DisconnectionEvent event) {
		for (DisconnectionEventListener eventListener : disconnectionEventListeners) {
			eventListener.onEvent(event);
		}
	}
	
	private void send(EmoteOnlyModeOffEvent event) {
		for (EmoteOnlyModeOffEventListener eventListener : emoteOnlyModeOffEventListeners) {
			eventListener.onEvent(event);
		}
	}
	
	private void send(EmoteOnlyModeOnEvent event) {
		for (EmoteOnlyModeOnEventListener eventListener : emoteOnlyModeOnEventListeners) {
			eventListener.onEvent(event);
		}
	}
	
	private void send(HostModeOffEvent event) {
		for (HostModeOffEventListener eventListener : hostModeOffEventListeners) {
			eventListener.onEvent(event);
		}
	}
	
	private void send(HostModeOnEvent event) {
		for (HostModeOnEventListener eventListener : hostModeOnEventListeners) {
			eventListener.onEvent(event);
		}
	}
	
	private void send(IRCServerConnectionEvent event) {
		for (IRCServerConnectionEventListener eventListener : ircServerConnectionEventListeners) {
			eventListener.onEvent(event);
		}
	}
	
	private void send(JoinEvent event) {
		for (JoinEventListener eventListener : joinEventListeners) {
			eventListener.onEvent(event);
		}
	}
	
	private void send(MessageEvent event) {
		for (MessageEventListener eventListener : messageEventListeners) {
			eventListener.onEvent(event);
		}
	}
	
	private void send(PartEvent event) {
		for (PartEventListener eventListener : partEventListeners) {
			eventListener.onEvent(event);
		}
	}
	
	private void send(PingEvent event) {
		for (PingEventListener eventListener : pingEventListeners) {
			eventListener.onEvent(event);
		}
	}
	
	private void send(R9KModeOffEvent event) {
		for (R9KModeOffEventListener eventListener : r9kModeOffEventListeners) {
			eventListener.onEvent(event);
		}
	}
	
	private void send(R9KModeOnEvent event) {
		for (R9KModeOnEventListener eventListener : r9kModeOnEventListeners) {
			eventListener.onEvent(event);
		}
	}
	
	private void send(SlowModeOffEvent event) {
		for (SlowModeOffEventListener eventListener : slowModeOffEventListeners) {
			eventListener.onEvent(event);
		}
	}
	
	private void send(SlowModeOnEvent event) {
		for (SlowModeOnEventListener eventListener : slowModeOnEventListeners) {
			eventListener.onEvent(event);
		}
	}
	
	private void send(SocketConnectionEvent event) {
		for (SocketConnectionEventListener eventListener : socketConnectionEventListeners) {
			eventListener.onEvent(event);
		}
	}
	
	private void send(SubscriberOnlyModeOffEvent event) {
		for (SubscriberOnlyModeOffEventListener eventListener : subscriberOnlyModeOffEventListeners) {
			eventListener.onEvent(event);
		}
	}
	
	private void send(SubscriberOnlyModeOnEvent event) {
		for (SubscriberOnlyModeOnEventListener eventListener : subscriberOnlyModeOnEventListeners) {
			eventListener.onEvent(event);
		}
	}
	
	private void send(UserBannedEvent event) {
		for (UserBannedEventListener eventListener : userBannedEventListeners) {
			eventListener.onEvent(event);
		}
	}
	
	private void send(UserTimeoutEvent event) {
		for (UserTimeoutEventListener eventListener : userTimeoutEventListeners) {
			eventListener.onEvent(event);
		}
	}
	
	private void send(UserUnbannedEvent event) {
		for (UserUnbannedEventListener eventListener : userUnbannedEventListeners) {
			eventListener.onEvent(event);
		}
	}
	
}
