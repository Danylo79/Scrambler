package dev.dankom.scrambler;

import dev.dankom.scrambler.file.FileManager;
import dev.dankom.scrambler.type.Operation;
import dev.dankom.scrambler.util.Key;

import java.io.File;
import java.util.UUID;

public class Scrambler {
    public Object run(Operation operation, String... args) {
        if (operation.equals(Operation.ENCODE)) {
            String id = args[0];
            if (!new File(FileManager.root, id + "-scrambleset").exists()) {
                Key key = new Key(UUID.fromString(id), 10);
                key.save();
            }
            Key key = Key.getFromId(id);
            return key.encode(args[1]);
        } else if (operation.equals(Operation.DECODE)) {
            String id = args[0];
            if (!new File(FileManager.root, id + "-scrambleset").exists()) {
                Key key = new Key(UUID.fromString(id), 10);
                key.save();
            }
            Key key = Key.getFromId(id);
            return key.decode(args[1]);
        } else if (operation.equals(Operation.GENERATE_KEY)) {
            Key key = new Key(Integer.parseInt(args[0]));
            key.save();
            return key.getId();
        }
        return null;
    }
}
