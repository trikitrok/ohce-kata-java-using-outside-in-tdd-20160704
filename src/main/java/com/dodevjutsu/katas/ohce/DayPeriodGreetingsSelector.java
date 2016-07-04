package com.dodevjutsu.katas.ohce;

public class DayPeriodGreetingsSelector implements GreetingsSelector {
    private final Clock clock;

    public DayPeriodGreetingsSelector(Clock clock) {
        this.clock = clock;
    }

    @Override
    public String select_greeting(String userName) {
        int hour = clock.hour();

        String greetingTemplate = "";
        if(6 <= hour && hour < 12 ) {
            greetingTemplate = "¡Buenos días %s!";
        } else if (12 <= hour && hour < 20) {
            greetingTemplate = "¡Buenas tardes %s!";
        }
        else {
            greetingTemplate = "¡Buenas noches %s!";
        }

        return String.format(greetingTemplate, userName);
    }
}
