package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] lines = output.toString().split(System.lineSeparator());
        for (String line : lines) {
            if (line.startsWith("#")) {
                continue;
            }
            if (line.isEmpty()) {
                continue;
            }
            if (line.matches("^.+=.+=.+")
                    || line.matches("^.+=.+=")) {
                String[] keyValue = line.split("=", 2);
                values.put(keyValue[0], keyValue[1]);
                continue;
            }
            if (line.matches("^=.+")
                    || line.matches("^.+=")
                    || !line.contains("=")
                    || line.matches("^=$")) {
                throw new IllegalArgumentException("Pattern violation key=value found!");
            }
            String[] keyValue = line.split("=", 2);
            values.put(keyValue[0], keyValue[1]);
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}
