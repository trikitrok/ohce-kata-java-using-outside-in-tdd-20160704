package com.dodevjutsu.katas.ohce.tests.unit;

import com.dodevjutsu.katas.ohce.Console;
import com.dodevjutsu.katas.ohce.ConsoleNotifier;
import com.dodevjutsu.katas.ohce.Notifier;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

public class ConsoleNotifierTest {
    @Test
    public void greets_the_user() {
        Mockery context = new Mockery();
        Console console = context.mock(Console.class);
        Notifier notifier = new ConsoleNotifier(console);

        final String someGreeting = "hola";

        context.checking(new Expectations() {{
            oneOf(console).print(someGreeting);
        }});

        notifier.greet(someGreeting);

        context.assertIsSatisfied();
    }
}
