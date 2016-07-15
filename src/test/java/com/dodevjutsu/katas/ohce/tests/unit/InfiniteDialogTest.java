package com.dodevjutsu.katas.ohce.tests.unit;

import com.dodevjutsu.katas.ohce.adapters.dialogs.DialogResponse;
import com.dodevjutsu.katas.ohce.adapters.dialogs.InfiniteDialog;
import com.dodevjutsu.katas.ohce.core.Dialog;
import com.dodevjutsu.katas.ohce.core.Phrase;
import com.dodevjutsu.katas.ohce.core.PhraseReader;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class InfiniteDialogTest {

    private Mockery context;
    private Phrase stopPhrase;
    private Dialog dialog;
    private DialogResponse dialogResponse;
    private PhraseReader phraseReader;

    @Before
    public void setUp() {
        context = new Mockery();
        phraseReader = context.mock(PhraseReader.class);
        dialogResponse = context.mock(DialogResponse.class);
        String stopPhraseContent = "Stop!";
        stopPhrase = new Phrase(stopPhraseContent);
        dialog = new InfiniteDialog(phraseReader, dialogResponse, stopPhrase);
    }

    @Test
    public void keeps_asking_for_input_until_told_to_stop() {
        context.checking(new Expectations() {{
            exactly(3).of(phraseReader).read();
            will(
                onConsecutiveCalls(
                    returnValue(new Phrase("pepe")),
                    returnValue(new Phrase("moko")),
                    returnValue(stopPhrase)
                )
            );

            oneOf(dialogResponse).respondTo(new Phrase("pepe"));
            oneOf(dialogResponse).respondTo(new Phrase("moko"));
        }});

        dialog.start();

        context.assertIsSatisfied();
    }
}
