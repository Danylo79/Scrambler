package dev.dankom.scrambler.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Key {

    private List<String> pool;
    private Map<String, String> keyset;

    public Key() {
        this.pool = StringUtil.getChars();
        this.keyset = new HashMap<>();
        for (int i = 0; i < pool.size(); i++) {
            String value = pool.get(rand(1, pool.size()));
            String key = pool.get(i);
            keyset.put(key, value);
            System.out.println("Mapper: Mapped key " + key + " to " + value + "!");
        }
    }

    public Key(File keyset) {
        //Get Keyset from file
    }

    public String get(int key) {
        return keyset.get(key);
    }

    private int rand(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
