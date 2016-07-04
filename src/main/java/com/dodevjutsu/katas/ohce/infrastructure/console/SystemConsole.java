package com.dodevjutsu.katas.ohce.infrastructure.console;

public class SystemConsole implements Console {
    @Override
    public void print(String line) {
        System.out.println(line);
    }
}
