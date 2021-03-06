package com.dodevjutsu.katas.ohce.adapters.greeting_selectors;

import com.dodevjutsu.katas.ohce.infrastructure.clocks.Clock;
import com.dodevjutsu.katas.ohce.core.GreetingsSelector;

public class DayPeriodGreetingsSelector implements GreetingsSelector {
    private final Clock clock;

    public DayPeriodGreetingsSelector(Clock clock) {
        this.clock = clock;
    }

    @Override
    public String selectGreeting(String userName) {
        return String.format(selectGreetingTemplateFor(clock.hour()), userName);
    }

    private String selectGreetingTemplateFor(int hour) {
        if (6 <= hour && hour < 12) {
            return "¡Buenos días %s!";
        } else if (12 <= hour && hour < 20) {
            return "¡Buenas tardes %s!";
        } else {
            return "¡Buenas noches %s!";
        }
    }
}
