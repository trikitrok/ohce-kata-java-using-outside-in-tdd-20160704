package com.dodevjutsu.katas.ohce.adapters.reponses;

import com.dodevjutsu.katas.ohce.core.Phrase;
import com.dodevjutsu.katas.ohce.core.Response;

import java.util.List;

public class SequenceOfResponses implements Response {
    private final List<Response> responses;

    public SequenceOfResponses(List<Response> responses) {
        this.responses = responses;
    }

    @Override
    public void respondTo(Phrase phrase) {
        responses.stream()
            .forEach(response -> response.respondTo(phrase));
    }
}
