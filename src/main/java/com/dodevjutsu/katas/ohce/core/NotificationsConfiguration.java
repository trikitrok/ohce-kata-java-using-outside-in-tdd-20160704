package com.dodevjutsu.katas.ohce.core;

public class NotificationsConfiguration {
    private final String byePhrase;
    private final String celebrationPhrase;

    public NotificationsConfiguration(String byePhrase, String celebrationPhrase) {
        this.byePhrase = byePhrase;
        this.celebrationPhrase = celebrationPhrase;
    }

    public String celebrationPhrase() {
        return celebrationPhrase;
    }

    public String byePhrase() {
        return byePhrase;
    }
}
