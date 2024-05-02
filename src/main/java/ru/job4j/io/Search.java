package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validateArgs(args);
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validateArgs(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        if (args.length > 2) {
            throw new IllegalArgumentException("Too many arguments. Should be 2.");
        }
        if (!".".equals(args[0])) {
            throw new IllegalArgumentException("Invalid root folder. Usage  ROOT_FOLDER.");
        }
        if (!".js".equals(args[1])) {
            throw new IllegalArgumentException("Incorrect file extension " + args[1]);
        }
    }
}
