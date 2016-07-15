package com.dodevjutsu.katas.ohce;

import com.dodevjutsu.katas.ohce.adapters.adapters.greeting_selectors.DayPeriodGreetingsSelector;
import com.dodevjutsu.katas.ohce.adapters.notifiers.ConsoleNotifier;
import com.dodevjutsu.katas.ohce.adapters.phrase_readers.ConsolePhraseReader;
import com.dodevjutsu.katas.ohce.core.PalindromesConsoleDialog;
import com.dodevjutsu.katas.ohce.core.NotificationsConfiguration;
import com.dodevjutsu.katas.ohce.core.Ohce;
import com.dodevjutsu.katas.ohce.infrastructure.clocks.SystemClock;
import com.dodevjutsu.katas.ohce.infrastructure.console.SystemConsole;
import com.dodevjutsu.katas.ohce.infrastructure.console.SystemInputReader;

public class Main {

    public static void main(String[] args) {
        String userName = args[0];
        String stopPhraseContent = "Stop!";
        NotificationsConfiguration config = new NotificationsConfiguration(
            "Adios", "¡Bonita palabra!"
        );
        final ConsolePhraseReader phraseReader = new ConsolePhraseReader(new SystemInputReader());
        final ConsoleNotifier notifier = new ConsoleNotifier(new SystemConsole(), config);
        Ohce ohce = new Ohce(
            new DayPeriodGreetingsSelector(new SystemClock()),
            notifier,
            new PalindromesConsoleDialog(phraseReader, notifier, stopPhraseContent));

        ohce.run(userName);
    }

}
