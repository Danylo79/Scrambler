package dev.dankom.scrambler.util;

import dev.dankom.scrambler.file.FileManager;
import dev.dankom.scrambler.file.json.JsonFile;
import dev.dankom.scrambler.logger.Logger;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Key extends JSONObject implements JSONAware {
    private int keysetLength;
    private UUID id;
    private Map<String, String> keyset = new HashMap<>();

    public Key(JSONObject json) {
        getFromJSONObject(json);
    }

    public Key(int keysetLength) {
        if (new File(FileManager.root, getId() + "-scrambler").exists()) {
            getFromJSONObject(new JsonFile(FileManager.root, id + "-scrambler", this).get());
        } else {
            setKeysetLength(keysetLength);
            setId(UUID.randomUUID());
            for (int i = 0; i < StringUtil.getChars().size(); i++) {
                keyset.put(StringUtil.getChars().get(i), new KeyGenerator(keysetLength).get());
            }
        }
    }

    public Key(UUID id, int keysetLength) {
        if (new File(FileManager.root, getId() + "-scrambler").exists()) {
            getFromJSONObject(new JsonFile(FileManager.root, id + "-scrambler", this).get());
        } else {
            setKeysetLength(keysetLength);
            setId(id);
            for (int i = 0; i < StringUtil.getChars().size(); i++) {
                keyset.put(StringUtil.getChars().get(i), new KeyGenerator(keysetLength).get());
            }
        }
    }

    public String getChar(String key) {
        return keyset.get(key);
    }

    public String getValue(String value) {
        Logger.log("Search: " + value);
        for (Map.Entry entry : keyset.entrySet()) {
            if (((String)entry.getValue()).equalsIgnoreCase(value)) {
                Logger.log("Found key " + value + "!");
                return (String) entry.getKey();
            }
        }
        Logger.log("Failed to find " + value + "!");
        return null;
    }

    public void getFromJSONObject(JSONObject json) {
        JSONObject jsonKeyset = (JSONObject) json.get("keyset");
        setKeysetLength(((Long) json.get("length")).intValue());
        setId(UUID.fromString((String) json.get("id")));
        keyset = jsonKeyset;
    }

    public int getKeysetLength() {
        return keysetLength;
    }

    public void setKeysetLength(int keysetLength) {
        this.keysetLength = keysetLength;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toJSONString() {
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        JsonObjectBuilder jsonObjectBuilder1 = new JsonObjectBuilder();
        jsonObjectBuilder.addKeyValuePair("length", getKeysetLength());
        jsonObjectBuilder.addKeyValuePair("id", getId().toString());
        for (Map.Entry entry : keyset.entrySet()) {
            jsonObjectBuilder1.addKeyValuePair((String) entry.getKey(), entry.getValue());
        }
        jsonObjectBuilder.addKeyValuePair("keyset", jsonObjectBuilder1.build());
        return jsonObjectBuilder.build().toJSONString();
    }

    public JsonFile getJson() {
        return new JsonFile(FileManager.root, id + "-scrambleset", this);
    }

    public void save() {
        getJson().save();
    }

    public static Key getFromId(String id) {
        JSONObject json = new JsonFile(FileManager.root, id + "-scrambleset").get();
        return new Key(json);
    }

    public String encode(String s) {
        String out = "";
        Logger.log("Encoding " + s);
        for (int i = 0; i < s.chars().count(); i++) {
            out += getChar(((Character) s.charAt(i)).toString());
        }
        Logger.log("Encoded " + s + " to " + out);
        return out;
    }

    public String decode(String s) {
        Logger.log("Decoding " + s);
        String out = "";
        List<String> chars = new ArrayList<>();
        for (int j = 0; j < s.length(); j += getKeysetLength()) {
            int beginIndex = j;
            int endIndex = beginIndex + getKeysetLength();
            chars.add(s.substring(beginIndex, endIndex));
        }
        for (String code : chars) {
            out += getValue(code);
        }
        if (out.contains("null")) {
            Logger.log("Failed to decode using the " + getId() + " scramble set!");
            Runtime.getRuntime().exit(-1);
        }
        return out;
    }

    class KeyGenerator {
        private int length;

        public KeyGenerator(int length) {
            this.length = length;
        }

        public String get() {
            String out = "";
            for (int i = 0; i < length; i++) {
                out += StringUtil.getChars().get(rand(1, StringUtil.getChars().size()));
            }
            return out;
        }

        private int rand(int min, int max) {
            return ThreadLocalRandom.current().nextInt(min, max);
        }
    }
}
