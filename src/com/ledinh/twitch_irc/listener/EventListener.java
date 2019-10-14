package com.ledinh.twitch_irc.listener;

import com.ledinh.twitch_irc.event.GenericEvent;;

interface EventListener<T extends GenericEvent> {
	void onEvent(T event);
}
