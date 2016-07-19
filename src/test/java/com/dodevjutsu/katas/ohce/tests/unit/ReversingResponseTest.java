package com.dodevjutsu.katas.ohce.tests.unit;

import com.dodevjutsu.katas.ohce.adapters.reponses.ReversingResponse;
import com.dodevjutsu.katas.ohce.core.Notifier;
import com.dodevjutsu.katas.ohce.core.Phrase;
import com.dodevjutsu.katas.ohce.core.Response;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class ReversingResponseTest {
    private Mockery context;
    private Notifier notifier;
    private Response response;

    @Before
    public void setUp() {
        context = new Mockery();
        notifier = context.mock(Notifier.class);
        response = new ReversingResponse(notifier);
    }

    @Test
    public void echoes_the_reversed_user_phrase() {
        Phrase phrase = new Phrase("hola");
        Phrase reversedPhrase = new Phrase("aloh");
        context.checking(new Expectations() {{
            oneOf(notifier).echoReversedPhrase(reversedPhrase);
        }});

        response.respondTo(phrase);

        context.assertIsSatisfied();
    }
}
