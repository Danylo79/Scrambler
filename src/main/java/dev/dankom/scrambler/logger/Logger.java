package dev.dankom.scrambler.logger;

import dev.dankom.scrambler.ScrambleSettings;

public class Logger {
    public static void log(Object msg) {
        if (ScrambleSettings.showLogs()) {
            System.out.println("> [Scrambler] " + msg);
        }
    }

    public static void testLog(Object msg) {
        if (ScrambleSettings.showTestLogs()) {
            System.out.println("> [Scrambler] " + msg);
        }
    }
}
