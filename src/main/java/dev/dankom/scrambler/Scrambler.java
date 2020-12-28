package dev.dankom.scrambler;

import dev.dankom.scrambler.file.FileManager;
import dev.dankom.scrambler.logger.Logger;
import dev.dankom.scrambler.type.Operation;
import dev.dankom.scrambler.util.Key;

import java.io.File;
import java.util.UUID;

public class Scrambler {
    public Object run(Operation operation, String... args) {
        Logger.log("Running " + operation.getClass().getSimpleName() + "." + operation.name());
        if (operation.equals(Operation.ENCODE)) {
            String id = args[0];
            Key key = Key.getFromId(id);
            return key.encode(args[1]);
        } else if (operation.equals(Operation.DECODE)) {
            String id = args[0];
            Key key = Key.getFromId(id);
            return key.decode(args[1]);
        } else if (operation.equals(Operation.GENERATE_KEY)) {
            Key key = new Key(Integer.parseInt(args[0]));
            key.save();
            return key.getId();
        }
        return null;
    }

    public UUID generateScrambleSet(int length) {
        return (UUID) run(Operation.GENERATE_KEY, String.valueOf(length));
    }

    public String encode(UUID id, String s) {
        return (String) run(Operation.ENCODE, id.toString(), s);
    }

    public String decode(UUID id, String s) {
        return (String) run(Operation.DECODE, id.toString(), s);
    }
}
