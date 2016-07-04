package com.dodevjutsu.katas.ohce.adapters.notifiers;

import com.dodevjutsu.katas.ohce.infrastructure.console.Console;
import com.dodevjutsu.katas.ohce.core.NotificationsConfiguration;
import com.dodevjutsu.katas.ohce.core.Notifier;
import com.dodevjutsu.katas.ohce.core.Phrase;

public class ConsoleNotifier implements Notifier {
    private final Console console;
    private final NotificationsConfiguration config;

    public ConsoleNotifier(Console console, NotificationsConfiguration config) {
        this.console = console;
        this.config = config;
    }

    @Override
    public void greet(String greeting) {
        console.print(greeting);
    }

    @Override
    public void echoReversedPhrase(Phrase reversedPhrase) {
        console.print(reversedPhrase.content());
    }

    @Override
    public void palindromesRock() {
        console.print(config.celebrationPhrase());
    }

    @Override
    public void sayBye(String userName) {
        console.print(config.byePhrase() + " " + userName);
    }
}
