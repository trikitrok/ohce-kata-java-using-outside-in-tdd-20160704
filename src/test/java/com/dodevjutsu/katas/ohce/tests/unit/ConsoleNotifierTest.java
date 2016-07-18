package com.dodevjutsu.katas.ohce.tests.unit;

import com.dodevjutsu.katas.ohce.adapters.notifiers.ConsoleNotifier;
import com.dodevjutsu.katas.ohce.core.NotificationsConfiguration;
import com.dodevjutsu.katas.ohce.core.Notifier;
import com.dodevjutsu.katas.ohce.core.Phrase;
import com.dodevjutsu.katas.ohce.infrastructure.console.Console;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class ConsoleNotifierTest {

    private Notifier notifier;
    private Console console;
    private Mockery context;

    @Before
    public void setUp() {
        context = new Mockery();
        console = context.mock(Console.class);
        NotificationsConfiguration config = new NotificationsConfiguration("Adios", "¡Bonita palabra!");
        notifier = new ConsoleNotifier(console, config);
    }

    @Test
    public void greets_the_user() {
        final String someGreeting = "hola";
        context.checking(new Expectations() {{
            oneOf(console).print(someGreeting);
        }});

        notifier.greet(someGreeting);

        context.assertIsSatisfied();
    }

    @Test
    public void echoes_reversed_phrases() {
        final String someInput = "okom";
        context.checking(new Expectations() {{
            oneOf(console).print(someInput);
        }});

        notifier.echoReversedPhrase(new Phrase(someInput));

        context.assertIsSatisfied();
    }

    @Test
    public void celebrates_palindromes() {
        final String celebration = "¡Bonita palabra!";
        context.checking(new Expectations() {{
            oneOf(console).print(celebration);
        }});

        notifier.palindromesRock();

        context.assertIsSatisfied();
    }

    @Test
    public void says_bye() {
        final String bye_message = "Adios Pedro";
        context.checking(new Expectations() {{
            oneOf(console).print(bye_message);
        }});

        notifier.sayBye("Pedro");

        context.assertIsSatisfied();
    }
}
