package com.dodevjutsu.katas.ohce.adapters.reponses;

import com.dodevjutsu.katas.ohce.core.Notifier;
import com.dodevjutsu.katas.ohce.core.Phrase;
import com.dodevjutsu.katas.ohce.core.Response;

public class ReversingResponse implements Response {
    private final Notifier notifier;
    private final Response nextResponse;

    public ReversingResponse(Notifier notifier, Response nextResponse) {
        this.notifier = notifier;
        this.nextResponse = nextResponse;
    }

    @Override
    public void respondTo(Phrase phrase) {
        notifier.echoReversedPhrase(phrase.reversed());
        nextResponse.respondTo(phrase);
    }
}
