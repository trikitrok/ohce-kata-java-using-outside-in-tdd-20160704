package com.dodevjutsu.katas.ohce;

public class Ohce {
    private static final Phrase STOP_PHRASE = new Phrase("Stop!");
    private GreetingsSelector selector;
    private Notifier notifier;
    private PhraseReader phraseReader;

    public Ohce(Console console, InputReader inputReader, Clock clock) {
        selector = new DayPeriodGreetingsSelector(clock);
        NotificationsConfiguration config = new NotificationsConfiguration("Adios", "¡Bonita palabra!");
        notifier = new ConsoleNotifier(console, config);
        phraseReader = new ConsolePhraseReader(inputReader);
    }

    public Ohce(GreetingsSelector selector, Notifier notifier, PhraseReader phraseReader) {
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
        return STOP_PHRASE.equals(phrase);
    }
}
