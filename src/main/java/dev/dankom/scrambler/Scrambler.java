package dev.dankom.scrambler;

import dev.dankom.scrambler.type.Operation;
import dev.dankom.scrambler.util.Key;

public class Scrambler {
    private Operation operation;

    public Scrambler(Operation operation) {
        this.operation = operation;
    }

    public void run() {
        if (operation.equals(Operation.ENCODE)) {

        } else if (operation.equals(Operation.DECODE)) {

        } else if (operation.equals(Operation.GENERATE_KEY)) {
            Key key = new Key();
        }
    }
}
