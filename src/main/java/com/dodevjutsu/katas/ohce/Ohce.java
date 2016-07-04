package com.dodevjutsu.katas.ohce;

public class Ohce {
    private GreetingsSelector selector;
    private Notifier notifier;
    private PhraseInput phraseInput;

    public Ohce(Console console, InputReader inputReader, Clock clock) {

    }

    public Ohce(GreetingsSelector selector, Notifier notifier, PhraseInput phraseInput) {
        this.selector = selector;
        this.notifier = notifier;
        this.phraseInput = phraseInput;
    }

    public void run(String userName) {
        notifier.greet(selector.select_greeting(userName));

        Phrase reversedPhrase = phraseInput.read();

        notifier.echoReversedPhrase(reversedPhrase);
    }
}
