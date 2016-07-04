package com.dodevjutsu.katas.ohce;

public class ConsoleNotifier implements Notifier {
    private final Console console;

    public ConsoleNotifier(Console console) {
        this.console = console;
    }

    @Override
    public void greet(String greeting) {
        console.print(greeting);
    }

    @Override
    public void echoReversedPhrase(Phrase reversedPhrase) {

    }

    @Override
    public void palindromesRock() {

    }

    @Override
    public void sayBye() {

    }
}
