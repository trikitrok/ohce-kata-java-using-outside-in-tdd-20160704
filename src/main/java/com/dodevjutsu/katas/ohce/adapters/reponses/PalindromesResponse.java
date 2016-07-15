package com.dodevjutsu.katas.ohce.adapters.reponses;

import com.dodevjutsu.katas.ohce.core.Notifier;
import com.dodevjutsu.katas.ohce.core.Phrase;
import com.dodevjutsu.katas.ohce.core.Response;

public class PalindromesResponse implements Response {
    private final Notifier notifier;

    public PalindromesResponse(Notifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public void respondTo(Phrase phrase) {
        if(phrase.isPalindrome()) {
            notifier.palindromesRock();
        }
    }
}
