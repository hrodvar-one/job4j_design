package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {

    private static void validate(ArgsName name) {
        File file = new File(name.get("path"));
        if (!file.exists()) {
            throw new IllegalArgumentException("Such file does not exist.");
        }
        if (!file.isFile()) {
            throw new IllegalArgumentException("The specified path is not a file.");
        }
    }

    public static void handle(ArgsName argsName) throws Exception {
        validate(argsName);
        File file = new File(argsName.get("path"));
        String delimiter = argsName.get("delimiter");
        String output = argsName.get("out");
        String[] filters = argsName.get("filter").split(",");
        ArrayList<String> lines = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream(file))) {
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> list = new ArrayList<>();
        int index;
        ArrayList<Integer> indexesList = new ArrayList<>();

        for (String s : filters) {
            if (lines.get(0).contains(s)) {
                String[] tokens = lines.get(0).split(delimiter);
                index = Arrays.asList(tokens).indexOf(s);
                indexesList.add(index);
            }
        }

        for (String line : lines) {
            String[] tokens2 = line.split(delimiter);
            StringBuilder builder = new StringBuilder();
            for (Integer i : indexesList) {
                builder.append(tokens2[i]).append(delimiter);
            }
            list.add(builder.substring(0, builder.length() - 1));
        }

        if ("stdout".equals(output)) {
            for (String line : list) {
                System.out.println(line);
            }
        } else {
            try (PrintStream printStream = new PrintStream(new FileOutputStream(output))) {
                for (String line : list) {
                    printStream.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("Invalid number of arguments.");
        }
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
