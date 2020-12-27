package dev.dankom;

import dev.dankom.scrambler.Scrambler;
import dev.dankom.scrambler.type.Operation;

import java.util.UUID;

public class Start {
    public static void main(String[] args) {
        UUID id = (UUID) new Scrambler().run(Operation.GENERATE_KEY, "10");
        String encoded = (String) new Scrambler().run(Operation.ENCODE, id.toString(), "Wow");
        String decoded = (String) new Scrambler().run(Operation.DECODE, id.toString(), encoded);
        System.out.println(decoded);
    }
}
