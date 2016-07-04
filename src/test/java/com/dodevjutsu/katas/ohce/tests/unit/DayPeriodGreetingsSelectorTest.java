package com.dodevjutsu.katas.ohce.tests.unit;

import com.dodevjutsu.katas.ohce.infrastructure.clocks.Clock;
import com.dodevjutsu.katas.ohce.adapters.adapters.greeting_selectors.DayPeriodGreetingsSelector;
import com.dodevjutsu.katas.ohce.core.GreetingsSelector;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DayPeriodGreetingsSelectorTest {

    private Mockery context;
    private Clock clock;
    private GreetingsSelector greetingsSelector;

    @Before
    public void setUp() {
        context = new Mockery();
        clock = context.mock(Clock.class);
        greetingsSelector = new DayPeriodGreetingsSelector(clock);
    }

    @Test
    public void selecting_greeting_during_the_morning() {
        context.checking(new Expectations() {{
            exactly(3).of(clock).hour();
            will(onConsecutiveCalls(
                returnValue(6),
                returnValue(10),
                returnValue(11)
            ));
        }});

        assertThat(greetingsSelector.select_greeting("Juan"), is("¡Buenos días Juan!"));
        assertThat(greetingsSelector.select_greeting("Koko"), is("¡Buenos días Koko!"));
        assertThat(greetingsSelector.select_greeting("Juan"), is("¡Buenos días Juan!"));

        context.assertIsSatisfied();
    }

    @Test
    public void selecting_greeting_during_the_afternoon() {
        context.checking(new Expectations() {{
            exactly(3).of(clock).hour();
            will(onConsecutiveCalls(
                returnValue(12),
                returnValue(16),
                returnValue(19)
            ));
        }});

        assertThat(greetingsSelector.select_greeting("Juan"), is("¡Buenas tardes Juan!"));
        assertThat(greetingsSelector.select_greeting("Koko"), is("¡Buenas tardes Koko!"));
        assertThat(greetingsSelector.select_greeting("Juan"), is("¡Buenas tardes Juan!"));

        context.assertIsSatisfied();
    }

    @Test
    public void selecting_greeting_during_the_night() {
        context.checking(new Expectations() {{
            exactly(3).of(clock).hour();
            will(onConsecutiveCalls(
                returnValue(20),
                returnValue(24),
                returnValue(3)
            ));
        }});

        assertThat(greetingsSelector.select_greeting("Juan"), is("¡Buenas noches Juan!"));
        assertThat(greetingsSelector.select_greeting("Koko"), is("¡Buenas noches Koko!"));
        assertThat(greetingsSelector.select_greeting("Juan"), is("¡Buenas noches Juan!"));

        context.assertIsSatisfied();
    }

}
