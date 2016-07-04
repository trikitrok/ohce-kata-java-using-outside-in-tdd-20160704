package com.dodevjutsu.katas.ohce.tests.unit;

import com.dodevjutsu.katas.ohce.ConsolePhraseReader;
import com.dodevjutsu.katas.ohce.InputReader;
import com.dodevjutsu.katas.ohce.Phrase;
import com.dodevjutsu.katas.ohce.PhraseReader;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ConsolePhraseReaderTest {
    @Test
    public void reads_user_phrases() {
        Mockery context = new Mockery();
        InputReader inputReader = context.mock(InputReader.class);
        PhraseReader phraseReader = new ConsolePhraseReader(inputReader);

        context.checking(new Expectations() {{
            oneOf(inputReader).read();
            will(returnValue("some input"));
        }});

        assertThat(phraseReader.read(), is(new Phrase("some input")));
    }
}
