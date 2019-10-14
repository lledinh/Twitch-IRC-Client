package com.ledinh.twitch_irc.event;

import com.ledinh.twitch_irc.Message;

public class GenericEvent {

    protected long mTimestamp;

    public GenericEvent() {
        this.mTimestamp = System.currentTimeMillis();
    }

    public long getTimestamp() {
        return mTimestamp;
    }
}
