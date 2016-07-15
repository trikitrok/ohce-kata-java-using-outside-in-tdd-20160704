package com.dodevjutsu.katas.ohce.adapters.dialogs;

import com.dodevjutsu.katas.ohce.core.Dialog;
import com.dodevjutsu.katas.ohce.core.Response;
import com.dodevjutsu.katas.ohce.core.Phrase;
import com.dodevjutsu.katas.ohce.core.PhraseReader;

public class InfiniteDialog implements Dialog {

    private final PhraseReader phraseReader;
    private final Response response;
    private final Phrase stopPhrase;

    public InfiniteDialog(PhraseReader phraseReader, Response response, Phrase stopPhrase) {

        this.phraseReader = phraseReader;
        this.response = response;
        this.stopPhrase = stopPhrase;
    }

    @Override
    public void start() {
        processPhrase(phraseReader.read());
    }

    private void processPhrase(Phrase phrase) {
        if(shouldStop(phrase)) {
            return;
        }

        response.respondTo(phrase);

        processPhrase(phraseReader.read());
    }

    private boolean shouldStop(Phrase phrase) {
        return stopPhrase.equals(phrase);
    }
}
