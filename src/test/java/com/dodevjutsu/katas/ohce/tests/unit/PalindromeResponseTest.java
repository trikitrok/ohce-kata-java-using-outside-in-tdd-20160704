package com.dodevjutsu.katas.ohce.tests.unit;

import com.dodevjutsu.katas.ohce.adapters.reponses.PalindromesResponse;
import com.dodevjutsu.katas.ohce.core.Notifier;
import com.dodevjutsu.katas.ohce.core.Phrase;
import com.dodevjutsu.katas.ohce.core.Response;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class PalindromeResponseTest {
    private Mockery context;
    private Notifier notifier;
    private Response response;

    @Before
    public void setUp() {
        context = new Mockery();
        notifier = context.mock(Notifier.class);
        response = new PalindromesResponse(notifier);
    }

    @Test
    public void notices_palindromes() {
        Phrase palindrome = new Phrase("ana");

        context.checking(new Expectations() {{
            oneOf(notifier).palindromesRock();
        }});

        response.respondTo(palindrome);

        context.assertIsSatisfied();
    }

    @Test
    public void ignores_non_palindromes() {
        Phrase nonPalindrome = new Phrase("koko");

        context.checking(new Expectations() {{
            never(notifier).palindromesRock();
        }});

        response.respondTo(nonPalindrome);

        context.assertIsSatisfied();
    }
}
