package com.dodevjutsu.katas.ohce.tests.unit;

import com.dodevjutsu.katas.ohce.*;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class OhceTest {

    private Mockery context;
    private GreetingsSelector selector;
    private Notifier notifier;
    private Ohce ohce;
    private PhraseInput phraseInput;
    private String userName;

    @Before
    public void setUp() {
        context = new Mockery();
        selector = context.mock(GreetingsSelector.class);
        notifier = context.mock(Notifier.class);
        phraseInput = context.mock(PhraseInput.class);
        ohce = new Ohce(selector, notifier, phraseInput);
        userName = "Juan";
    }

    @Test
    public void greets_user() {
        String greeting = "¡Buenos días Juan!";

        context.checking(new Expectations() {{
            ignoring(phraseInput);
            will(returnValue(new Phrase("not used")));

            oneOf(selector).select_greeting(userName);
            will(returnValue(greeting));

            oneOf(notifier).greet(greeting);
            ignoring(notifier);
        }});

        ohce.run(userName);

        context.assertIsSatisfied();
    }

    @Test
    public void echoes_the_reversed_user_phrase() {
        Phrase reversedPhrase = new Phrase("aloh");

        context.checking(new Expectations() {{
            ignoring(selector);

            oneOf(phraseInput).read();
            will(returnValue(reversedPhrase));

            oneOf(notifier).echoReversedPhrase(reversedPhrase);
            ignoring(notifier);
        }});

        ohce.run(userName);

        context.assertIsSatisfied();
    }
}
