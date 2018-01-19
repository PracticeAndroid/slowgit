package com.miuty.slowgit.util;

public enum FeedsType {

    PUSH_EVENT("PushEvent"),
    FORK_EVENT("ForkEvent"),
    PUBLIC_EVENT("PublicEvent"),
    CREATE_EVENT("CreateEvent"),
    WATCH_EVENT("WatchEvent");

    public String key;

    FeedsType(String key) {
        this.key = key;
    }
}
