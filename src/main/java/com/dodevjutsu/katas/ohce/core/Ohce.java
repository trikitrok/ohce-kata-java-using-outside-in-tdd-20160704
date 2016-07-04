package com.dodevjutsu.katas.ohce.core;

public class Ohce {
    private Phrase stopPhrase;
    private GreetingsSelector selector;
    private Notifier notifier;
    private PhraseReader phraseReader;

    public Ohce(String stopPhraseContent, GreetingsSelector selector, Notifier notifier, PhraseReader phraseReader) {
        this.stopPhrase = new Phrase(stopPhraseContent);
        this.selector = selector;
        this.notifier = notifier;
        this.phraseReader = phraseReader;
    }

    public void run(String userName) {
        greet(userName);
        processPhrase(phraseReader.read());
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

        processPhrase(phraseReader.read());
    }

    private boolean shouldStop(Phrase phrase) {
        return stopPhrase.equals(phrase);
    }
}
