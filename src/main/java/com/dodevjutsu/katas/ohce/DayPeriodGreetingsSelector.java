package com.dodevjutsu.katas.ohce;

public class DayPeriodGreetingsSelector implements GreetingsSelector {
    private final Clock clock;

    public DayPeriodGreetingsSelector(Clock clock) {
        this.clock = clock;
    }

    @Override
    public String select_greeting(String userName) {
        int hour = clock.hour();

        return "¡Buenos días " + userName + "!";
    }
}
