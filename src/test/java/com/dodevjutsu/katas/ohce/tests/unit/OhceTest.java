package com.dodevjutsu.katas.ohce.tests.unit;

import com.dodevjutsu.katas.ohce.core.Dialog;
import com.dodevjutsu.katas.ohce.core.GreetingsSelector;
import com.dodevjutsu.katas.ohce.core.Notifier;
import com.dodevjutsu.katas.ohce.core.Ohce;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class OhceTest {

    private Mockery context;
    private GreetingsSelector selector;
    private Notifier notifier;
    private Ohce ohce;
    private String userName;
    private Dialog dialog;

    @Before
    public void setUp() {
        context = new Mockery();
        selector = context.mock(GreetingsSelector.class);
        notifier = context.mock(Notifier.class);
        dialog = context.mock(Dialog.class);
        ohce = new Ohce(selector, notifier, dialog);
        userName = "Juan";
    }

    @Test
    public void greets_user() {
        String greeting = "any greeting";
        context.checking(new Expectations() {{
            ignoring(dialog);

            oneOf(selector).selectGreeting(userName);
            will(returnValue(greeting));

            oneOf(notifier).greet(greeting);
            ignoring(notifier);
        }});

        ohce.run(userName);

        context.assertIsSatisfied();
    }

    @Test
    public void starts_a_dialog_with_the_user() {
        context.checking(new Expectations() {{
            ignoring(notifier);
            ignoring(selector);

            oneOf(dialog).start();
        }});

        ohce.run(userName);

        context.assertIsSatisfied();
    }

    @Test
    public void says_bye_when_told_to_stop() {
        context.checking(new Expectations() {{
            ignoring(selector);
            ignoring(dialog);

            oneOf(notifier).sayBye(userName);
            ignoring(notifier);
        }});

        ohce.run(userName);

        context.assertIsSatisfied();
    }
}
