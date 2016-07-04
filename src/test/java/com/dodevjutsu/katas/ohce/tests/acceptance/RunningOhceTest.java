package com.dodevjutsu.katas.ohce.tests.acceptance;

import com.dodevjutsu.katas.ohce.Clock;
import com.dodevjutsu.katas.ohce.Console;
import com.dodevjutsu.katas.ohce.InputReader;
import com.dodevjutsu.katas.ohce.Ohce;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class RunningOhceTest {

    private Clock clock;
    private InputReader inputReader;
    private Console console;
    private Mockery context;

    @Before
    public void setUp() {
        context = new Mockery();
        console = context.mock(Console.class);
        inputReader = context.mock(InputReader.class);
        clock = context.mock(Clock.class);
    }

    @Test
    public void running_ohce_during_the_morning() {
        Ohce ohce = new Ohce(console, inputReader, clock);

        context.checking(new Expectations() {{
            oneOf(clock).hour();
            will(returnValue(8));

            exactly(4).of(inputReader).read();
            will(onConsecutiveCalls(
                returnValue("Hola"),
                returnValue("oto"),
                returnValue("stop"),
                returnValue("Stop!")
            ));

            oneOf(console).print("¡Buenos días Pedro!");
            oneOf(console).print("aloH");
            oneOf(console).print("oto");
            oneOf(console).print("¡Bonita palabra!");
            oneOf(console).print("pots");
            oneOf(console).print("Adios Pedro");
        }});

        ohce.run("Pedro");

        context.assertIsSatisfied();
    }
}
