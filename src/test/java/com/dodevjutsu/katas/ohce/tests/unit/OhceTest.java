package com.dodevjutsu.katas.ohce.tests.unit;

import com.dodevjutsu.katas.ohce.core.*;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class OhceTest {

    private Mockery context;
    private GreetingsSelector selector;
    private Notifier notifier;
    private Ohce ohce;
    private PhraseReader phraseReader;
    private String userName;
    private Phrase stopPhrase;

    @Before
    public void setUp() {
        context = new Mockery();
        selector = context.mock(GreetingsSelector.class);
        notifier = context.mock(Notifier.class);
        phraseReader = context.mock(PhraseReader.class);
        ohce = new Ohce(selector, notifier, phraseReader);
        userName = "Juan";
        stopPhrase = new Phrase("Stop!");
    }

    @Test
    public void greets_user() {
        String greeting = "¡Buenos días Juan!";

        context.checking(new Expectations() {{
            allowing(phraseReader);
            will(onConsecutiveCalls(
                returnValue(new Phrase("not used")),
                returnValue(stopPhrase)
            ));

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
        Phrase phrase = new Phrase("hola");
        Phrase reversedPhrase = new Phrase("aloh");

        context.checking(new Expectations() {{
            ignoring(selector);

            exactly(2).of(phraseReader).read();
            will(onConsecutiveCalls(
                returnValue(phrase),
                returnValue(stopPhrase)
            ));

            oneOf(notifier).echoReversedPhrase(reversedPhrase);
            ignoring(notifier);
        }});

        ohce.run(userName);

        context.assertIsSatisfied();
    }

    @Test
    public void identifies_palindromes() {
        Phrase palindrome = new Phrase("ana");

        context.checking(new Expectations() {{
            ignoring(selector);

            exactly(2).of(phraseReader).read();
            will(onConsecutiveCalls(
                returnValue(palindrome),
                returnValue(stopPhrase)
            ));

            oneOf(notifier).echoReversedPhrase(palindrome);
            oneOf(notifier).palindromesRock();
            ignoring(notifier);
        }});

        ohce.run(userName);

        context.assertIsSatisfied();
    }

    @Test
    public void says_bye_when_told_to_stop() {
        context.checking(new Expectations() {{
            ignoring(selector);

            oneOf(phraseReader).read();
            will(returnValue(stopPhrase));

            oneOf(notifier).sayBye(userName);
            ignoring(notifier);
        }});

        ohce.run(userName);

        context.assertIsSatisfied();
    }

    @Test
    public void keeps_asking_for_the_user_input_until_told_to_stop() {
        context.checking(new Expectations() {{
            ignoring(selector);

            exactly(3).of(phraseReader).read();
            will(
                onConsecutiveCalls(
                    returnValue(new Phrase("pepe")),
                    returnValue(new Phrase("moko")),
                    returnValue(stopPhrase)
                )
            );

            oneOf(notifier).echoReversedPhrase(new Phrase("epep"));
            oneOf(notifier).echoReversedPhrase(new Phrase("okom"));
            oneOf(notifier).sayBye(userName);
            ignoring(notifier);
        }});

        ohce.run(userName);

        context.assertIsSatisfied();
    }
}
