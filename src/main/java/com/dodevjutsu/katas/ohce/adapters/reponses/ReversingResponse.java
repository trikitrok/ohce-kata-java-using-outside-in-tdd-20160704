package com.dodevjutsu.katas.ohce.adapters.reponses;

import com.dodevjutsu.katas.ohce.core.Notifier;
import com.dodevjutsu.katas.ohce.core.Phrase;
import com.dodevjutsu.katas.ohce.core.Response;

public class ReversingResponse implements Response {
    private final Notifier notifier;

    public ReversingResponse(Notifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public void respondTo(Phrase phrase) {
        notifier.echoReversedPhrase(phrase.reversed());
    }
}
