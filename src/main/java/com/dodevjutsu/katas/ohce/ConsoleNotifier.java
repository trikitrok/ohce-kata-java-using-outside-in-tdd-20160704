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
        console.print(reversedPhrase.content());
    }

    @Override
    public void palindromesRock() {
        console.print("¡Bonita palabra!");
    }

    @Override
    public void sayBye(String userName) {
        console.print("Adios " + userName);
    }
}
