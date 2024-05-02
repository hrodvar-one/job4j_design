package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    private static void validateArgs(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (String arg : args) {
            if (!arg.startsWith("-")) {
                throw new IllegalArgumentException(
                        "Error: This argument '" + arg + "' does not start with a '-' character");
            }
            if (!arg.contains("=")) {
                throw new IllegalArgumentException(
                        "Error: This argument '" + arg + "' does not contain an equal sign");
            }
            if (arg.endsWith("=") && !arg.matches((".+=.+=.+"))) {
                throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain a value");
            }
        }
    }

    private void parse(String[] args) {
        for (String arg : args) {
            String[] pair = arg.substring(1).split("=", 2);
            if ("".equals(pair[0]) || "".equals(pair[1])) {
                throw new IllegalArgumentException(
                        "Error: This argument '" + arg + "' does not contain a key"
                );
            }
            if (pair.length == 2) {
                values.put(pair[0], pair[1]);
            } else {
                throw new IllegalArgumentException(
                        "Error: This argument + '" + arg + "' does not contain a value");
            }
        }
    }

    public String get(String key) {
        if (values.get(key) == null) {
            throw new IllegalArgumentException("This key: '" + key + "' is missing");
        }
        return values.get(key);
    }

    public static ArgsName of(String[] args) {
        validateArgs(args);
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
