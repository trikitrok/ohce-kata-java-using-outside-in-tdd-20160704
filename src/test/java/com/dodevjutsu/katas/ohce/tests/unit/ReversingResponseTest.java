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
    private Response nextResponse;

    @Before
    public void setUp() {
        context = new Mockery();
        notifier = context.mock(Notifier.class);
        nextResponse = context.mock(Response.class);
        response = new ReversingResponse(notifier, nextResponse);
    }

    @Test
    public void echoes_the_reversed_user_phrase() {
        Phrase phrase = new Phrase("hola");
        Phrase reversedPhrase = new Phrase("aloh");

        context.checking(new Expectations() {{
            oneOf(notifier).echoReversedPhrase(reversedPhrase);
            ignoring(nextResponse);
        }});

        response.respondTo(phrase);

        context.assertIsSatisfied();
    }

    @Test
    public void triggers_next_response() {
        Phrase phrase = new Phrase("something");
        context.checking(new Expectations() {{
            ignoring(notifier);
            oneOf(nextResponse).respondTo(phrase);
        }});

        response.respondTo(phrase);

        context.assertIsSatisfied();
    }
}
