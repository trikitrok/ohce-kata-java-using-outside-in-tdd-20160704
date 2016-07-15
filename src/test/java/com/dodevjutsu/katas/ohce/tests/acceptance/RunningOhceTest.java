package com.dodevjutsu.katas.ohce.tests.acceptance;

import com.dodevjutsu.katas.ohce.adapters.adapters.greeting_selectors.DayPeriodGreetingsSelector;
import com.dodevjutsu.katas.ohce.adapters.notifiers.ConsoleNotifier;
import com.dodevjutsu.katas.ohce.adapters.phrase_readers.ConsolePhraseReader;
import com.dodevjutsu.katas.ohce.core.PalindromesConsoleDialog;
import com.dodevjutsu.katas.ohce.core.NotificationsConfiguration;
import com.dodevjutsu.katas.ohce.core.Ohce;
import com.dodevjutsu.katas.ohce.infrastructure.clocks.Clock;
import com.dodevjutsu.katas.ohce.infrastructure.console.Console;
import com.dodevjutsu.katas.ohce.infrastructure.console.InputReader;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class RunningOhceTest {

    private Clock clock;
    private InputReader inputReader;
    private Console console;
    private Mockery context;
    private NotificationsConfiguration config;
    private Ohce ohce;

    @Before
    public void setUp() {
        context = new Mockery();
        console = context.mock(Console.class);
        inputReader = context.mock(InputReader.class);
        clock = context.mock(Clock.class);
        config = new NotificationsConfiguration("Adios", "¡Bonita palabra!");
        final ConsolePhraseReader phraseReader = new ConsolePhraseReader(inputReader);
        final ConsoleNotifier notifier = new ConsoleNotifier(console, config);
        ohce = new Ohce(
            new DayPeriodGreetingsSelector(clock),
            notifier,
            new PalindromesConsoleDialog(phraseReader, notifier, "Stop!"));
    }

    @Test
    public void running_ohce_during_the_morning() {
        context.checking(new Expectations() {{
            oneOf(clock).hour();
            will(returnValue(8));

            exactly(4).of(inputReader).read();
            will(onConsecutiveCalls(
                returnValue("Hola"),
                returnValue("oto"),
                returnValue("stop"),
                returnValue("Stop!")
            ));

            oneOf(console).print("¡Buenos días Pedro!");
            oneOf(console).print("aloH");
            oneOf(console).print("oto");
            oneOf(console).print("¡Bonita palabra!");
            oneOf(console).print("pots");
            oneOf(console).print("Adios Pedro");
        }});

        ohce.run("Pedro");

        context.assertIsSatisfied();
    }

    @Test
    public void running_ohce_during_the_afternoon() {
        context.checking(new Expectations() {{
            oneOf(clock).hour();
            will(returnValue(16));

            oneOf(inputReader).read();
            will(onConsecutiveCalls(
                returnValue("Stop!")
            ));

            oneOf(console).print("¡Buenas tardes Koko!");
            oneOf(console).print("Adios Koko");
        }});

        ohce.run("Koko");

        context.assertIsSatisfied();
    }

    @Test
    public void running_ohce_during_the_night() {
        context.checking(new Expectations() {{
            oneOf(clock).hour();
            will(returnValue(21));

            oneOf(inputReader).read();
            will(onConsecutiveCalls(
                returnValue("Stop!")
            ));

            oneOf(console).print("¡Buenas noches Juan!");
            oneOf(console).print("Adios Juan");
        }});

        ohce.run("Juan");

        context.assertIsSatisfied();
    }
}
