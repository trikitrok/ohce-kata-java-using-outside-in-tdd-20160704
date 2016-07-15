package com.dodevjutsu.katas.ohce;

import com.dodevjutsu.katas.ohce.core.NotificationsConfiguration;
import com.dodevjutsu.katas.ohce.core.Ohce;
import com.dodevjutsu.katas.ohce.factories.Factories;
import com.dodevjutsu.katas.ohce.infrastructure.clocks.SystemClock;
import com.dodevjutsu.katas.ohce.infrastructure.console.SystemConsole;
import com.dodevjutsu.katas.ohce.infrastructure.console.SystemInputReader;

public class Main {

    public static void main(String[] args) {
        String userName = args[0];
        Ohce ohce = Factories.createOhce(
            new SystemInputReader(),
            new SystemConsole(),
            new SystemClock(),
            new NotificationsConfiguration("Adios", "¡Bonita palabra!"),
            "Stop!"
        );
        ohce.run(userName);
    }

}
