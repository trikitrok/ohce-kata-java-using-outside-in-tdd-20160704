package com.dodevjutsu.katas.ohce;

public class Ohce {
    private static final Phrase STOP_PHRASE = new Phrase("Stop!");
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
        greet(userName);
        processPhrase(phraseInput.read());
        sayBye(userName);
    }

    private void greet(String userName) {
        notifier.greet(selector.select_greeting(userName));
    }

    private void sayBye(String userName) {
        notifier.sayBye(userName);
    }

    private void processPhrase(Phrase phrase) {
        if(shouldStop(phrase)) {
            return;
        }

        notifier.echoReversedPhrase(phrase.reversed());

        if(phrase.isPalindrome()) {
            notifier.palindromesRock();
        }

        processPhrase(phraseInput.read());
    }

    private boolean shouldStop(Phrase phrase) {
        return STOP_PHRASE.equals(phrase);
    }
}
