package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> lines = List.of();
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            lines = input.lines()
                    .map(s -> s.split(" "))
                    .filter(s -> s[s.length -2].equals("404"))
                    .map(s -> String.join(" ", s))
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);
    }
}
