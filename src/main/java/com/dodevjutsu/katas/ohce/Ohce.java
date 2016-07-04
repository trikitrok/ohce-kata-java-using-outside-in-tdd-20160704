package com.dodevjutsu.katas.ohce;

public class Ohce {
    private GreetingsSelector selector;
    private Notifier notifier;

    public Ohce(Console console, InputReader inputReader, Clock clock) {

    }

    public Ohce(GreetingsSelector selector, Notifier notifier) {
        this.selector = selector;
        this.notifier = notifier;
    }

    public void run(String userName) {
        notifier.greet(selector.select_greeting(userName));
    }
}
