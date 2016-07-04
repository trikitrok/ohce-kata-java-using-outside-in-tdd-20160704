package com.dodevjutsu.katas.ohce;

public class Phrase {
    private final String content;

    public Phrase(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phrase)) return false;

        Phrase phrase = (Phrase) o;

        return content != null ? content.equals(phrase.content) : phrase.content == null;
    }

    @Override
    public int hashCode() {
        return content != null ? content.hashCode() : 0;
    }

    public Phrase reversed() {
        return new Phrase("aloh");
    }
}