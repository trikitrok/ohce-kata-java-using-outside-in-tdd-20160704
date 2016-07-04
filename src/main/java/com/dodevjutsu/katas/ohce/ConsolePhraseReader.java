package com.dodevjutsu.katas.ohce;

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
