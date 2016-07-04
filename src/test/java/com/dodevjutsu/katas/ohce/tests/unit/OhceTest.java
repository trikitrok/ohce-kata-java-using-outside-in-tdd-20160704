package com.dodevjutsu.katas.ohce.tests.unit;

import com.dodevjutsu.katas.ohce.GreetingsSelector;
import com.dodevjutsu.katas.ohce.Notifier;
import com.dodevjutsu.katas.ohce.Ohce;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

public class OhceTest {

    private Mockery context;
    private GreetingsSelector selector;
    private Notifier notifier;

    @Test
    public void greets_user() {
        context = new Mockery();
        selector = context.mock(GreetingsSelector.class);
        notifier = context.mock(Notifier.class);
        final String userName = "Juan";
        String greeting = "¡Buenos días Juan!";

        Ohce ohce = new Ohce(selector, notifier);

        context.checking(new Expectations() {{
            oneOf(selector).select_greeting(userName);

            will(returnValue(greeting));

            oneOf(notifier).greet(greeting);
        }});

        ohce.run(userName);

        context.assertIsSatisfied();
    }
}
