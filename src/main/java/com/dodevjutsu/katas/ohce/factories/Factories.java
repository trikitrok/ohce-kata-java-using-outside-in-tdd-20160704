package com.dodevjutsu.katas.ohce.factories;

import com.dodevjutsu.katas.ohce.adapters.greeting_selectors.DayPeriodGreetingsSelector;
import com.dodevjutsu.katas.ohce.adapters.dialogs.InfiniteDialog;
import com.dodevjutsu.katas.ohce.adapters.notifiers.ConsoleNotifier;
import com.dodevjutsu.katas.ohce.adapters.phrase_readers.ConsolePhraseReader;
import com.dodevjutsu.katas.ohce.adapters.reponses.PalindromesResponse;
import com.dodevjutsu.katas.ohce.adapters.reponses.ReversingResponse;
import com.dodevjutsu.katas.ohce.adapters.reponses.SequenceOfResponses;
import com.dodevjutsu.katas.ohce.core.*;
import com.dodevjutsu.katas.ohce.infrastructure.clocks.Clock;
import com.dodevjutsu.katas.ohce.infrastructure.console.Console;
import com.dodevjutsu.katas.ohce.infrastructure.console.InputReader;

public class Factories {
    public static Ohce createOhce(InputReader inputReader,
                                  Console console,
                                  Clock clock,
                                  NotificationsConfiguration config,
                                  String stopPhraseContent) {
        Notifier notifier = new ConsoleNotifier(console, config);
        return new Ohce(
            new DayPeriodGreetingsSelector(clock),
            notifier,
            createInfiniteDialog(stopPhraseContent, inputReader, notifier)
        );
    }

    private static InfiniteDialog createInfiniteDialog(String stopPhraseContent, InputReader inputReader, Notifier notifier) {
        PhraseReader phraseReader = new ConsolePhraseReader(inputReader);
        Response response = createOhceResponse(notifier);
        return new InfiniteDialog(phraseReader, response, stopPhraseContent);
    }

    private static Response createOhceResponse(Notifier notifier) {
        return new SequenceOfResponses(
            new ReversingResponse(notifier),
            new PalindromesResponse(notifier)
        );
    }
}
