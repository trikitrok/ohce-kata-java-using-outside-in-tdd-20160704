package com.dodevjutsu.katas.ohce.adapters.phrase_readers;

import com.dodevjutsu.katas.ohce.infrastructure.console.InputReader;
import com.dodevjutsu.katas.ohce.core.Phrase;
import com.dodevjutsu.katas.ohce.core.PhraseReader;

public class ConsolePhraseReader implements PhraseReader {
    private final InputReader inputReader;

    public ConsolePhraseReader(InputReader inputReader) {
        this.inputReader = inputReader;
    }

    @Override
    public Phrase read() {
        return new Phrase(inputReader.read());
    }
}
