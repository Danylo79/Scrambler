package dev.dankom;

import dev.dankom.scrambler.Scrambler;
import dev.dankom.scrambler.type.Operation;

public class Start {
    public static void main(String[] args) {
        Scrambler scrambler = new Scrambler(Operation.GENERATE_KEY);
        scrambler.run();
    }
}
