package com.dodevjutsu.katas.ohce.core;

public class ConsoleDialog implements Dialog {
    private final PhraseReader phraseReader;
    private final Notifier notifier;
    private final Phrase stopPhrase;

    public ConsoleDialog(PhraseReader phraseReader, Notifier notifier, String stopPhraseContent) {
        this.phraseReader = phraseReader;
        this.notifier = notifier;
        this.stopPhrase = new Phrase(stopPhraseContent);
    }

    @Override
    public void start() {
        processPhrase(phraseReader.read());
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
