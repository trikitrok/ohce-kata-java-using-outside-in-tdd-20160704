package com.dodevjutsu.katas.ohce.tests.unit;

import com.dodevjutsu.katas.ohce.GreetingsSelector;
import com.dodevjutsu.katas.ohce.Notifier;
import com.dodevjutsu.katas.ohce.Ohce;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

public class OhceTest {

    private Mockery context;

    @Test
    public void greets_user() {
        context = new Mockery();
        GreetingsSelector selector = context.mock(GreetingsSelector.class);
        Notifier notifier = context.mock(Notifier.class);

        Ohce ohce = new Ohce(selector, notifier);

        context.checking(new Expectations() {{
            oneOf(selector).select_greeting("Juan");
            will(returnValue("¡Buenos días Juan!"));

            oneOf(notifier).greet("¡Buenos días Juan!");
        }});

        ohce.run("Juan");

        context.assertIsSatisfied();
    }
}
