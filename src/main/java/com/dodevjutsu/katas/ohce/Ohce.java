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
        notifier.greet(selector.select_greeting(userName));

        Phrase phrase = phraseInput.read();

        if(shouldStop(phrase)) {
            notifier.sayBye();
            return;
        }

        notifier.echoReversedPhrase(phrase.reversed());

        if(phrase.isPalindrome()) {
            notifier.palindromesRock();
        }
    }

    private boolean shouldStop(Phrase phrase) {
        return STOP_PHRASE.equals(phrase);
    }
}
