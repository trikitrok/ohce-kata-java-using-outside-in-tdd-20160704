package com.dodevjutsu.katas.ohce.tests.unit;

import com.dodevjutsu.katas.ohce.adapters.reponses.SequenceOfResponses;
import com.dodevjutsu.katas.ohce.core.Phrase;
import com.dodevjutsu.katas.ohce.core.Response;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SequenceOfResponsesTest {

    private Mockery context;
    private Response firstResponse;
    private Response secondResponse;
    private Response response;

    @Before
    public void setUp() {
        context = new Mockery();
        firstResponse = context.mock(Response.class, "firstResponse");
        secondResponse = context.mock(Response.class, "secondResponse");
        List<Response> responses = new ArrayList<>();
        responses.add(firstResponse);
        responses.add(secondResponse);
        response = new SequenceOfResponses(responses);
    }

    @Test
    public void uses_each_of_its_responses_in_order() {
        final Sequence responsesSequence = context.sequence("responsesSequence");
        Phrase anyPhrase = new Phrase("anything");
        context.checking(new Expectations() {{
            oneOf(firstResponse).respondTo(anyPhrase);
            inSequence(responsesSequence);
            oneOf(secondResponse).respondTo(anyPhrase);
            inSequence(responsesSequence);
        }});

        response.respondTo(anyPhrase);

        context.assertIsSatisfied();
    }
}
