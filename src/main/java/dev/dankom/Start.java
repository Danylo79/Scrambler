package dev.dankom;

import dev.dankom.scrambler.Scrambler;
import dev.dankom.scrambler.logger.Logger;

import java.util.UUID;

public class Start {
    public static void main(String[] args) {
        Scrambler scrambler = new Scrambler();
        String encoded = scrambler.encode(UUID.fromString("9ede260c-07af-4a86-a3bf-ffbe3ecc1d07"), "This is my encoded message! If you are reading this good job, You have decoded my secret message!");
        String decoded = scrambler.decode(UUID.fromString("9ede260c-07af-4a86-a3bf-ffbe3ecc1d07"), encoded);
        Logger.testLog("The Decoded String is \"" + decoded + "\"");
    }
}
