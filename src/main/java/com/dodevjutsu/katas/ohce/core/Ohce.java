package com.dodevjutsu.katas.ohce.core;

public class Ohce {
    private final Dialog dialog;
    private GreetingsSelector selector;
    private Notifier notifier;

    public Ohce(GreetingsSelector selector, Notifier notifier, Dialog dialog) {
        this.selector = selector;
        this.notifier = notifier;
        this.dialog = dialog;
    }

    public void run(String userName) {
        greet(userName);
        dialog.start();
        sayBye(userName);
    }

    private void greet(String userName) {
        notifier.greet(selector.selectGreeting(userName));
    }

    private void sayBye(String userName) {
        notifier.sayBye(userName);
    }
}
