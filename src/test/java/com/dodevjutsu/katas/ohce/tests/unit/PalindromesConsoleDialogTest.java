package com.dodevjutsu.katas.ohce.tests.unit;

import com.dodevjutsu.katas.ohce.core.*;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class PalindromesConsoleDialogTest {

    private Mockery context;
    private Notifier notifier;
    private PhraseReader phraseReader;
    private Phrase stopPhrase;
    private PalindromesConsoleDialog dialog;

    @Before
    public void setUp() {
        context = new Mockery();
        notifier = context.mock(Notifier.class);
        phraseReader = context.mock(PhraseReader.class);
        String stopPhraseContent = "Stop!";
        stopPhrase = new Phrase(stopPhraseContent);
        dialog = new PalindromesConsoleDialog(phraseReader, notifier, stopPhraseContent);
    }

    @Test
    public void echoes_the_reversed_user_phrase() {
        Phrase phrase = new Phrase("hola");
        Phrase reversedPhrase = new Phrase("aloh");

        context.checking(new Expectations() {{
            exactly(2).of(phraseReader).read();
            will(onConsecutiveCalls(
                returnValue(phrase),
                returnValue(stopPhrase)
            ));

            oneOf(notifier).echoReversedPhrase(reversedPhrase);
        }});

        dialog.start();

        context.assertIsSatisfied();
    }

    @Test
    public void identifies_palindromes() {
        Phrase palindrome = new Phrase("ana");

        context.checking(new Expectations() {{
            exactly(2).of(phraseReader).read();
            will(onConsecutiveCalls(
                returnValue(palindrome),
                returnValue(stopPhrase)
            ));

            oneOf(notifier).echoReversedPhrase(palindrome);
            oneOf(notifier).palindromesRock();
        }});

        dialog.start();

        context.assertIsSatisfied();
    }

    @Test
    public void keeps_asking_for_the_user_input_until_told_to_stop() {
        context.checking(new Expectations() {{
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
        }});

        dialog.start();

        context.assertIsSatisfied();
    }
}
