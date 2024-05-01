package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, Set<Path>> filesMap = new HashMap<>();
    private Set<FileProperty> printedFiles = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
        FileProperty fileProperty = new FileProperty(attributes.size(), file.getFileName().toString());
        filesMap.putIfAbsent(fileProperty, new HashSet<>());
        filesMap.get(fileProperty).add(file.toAbsolutePath());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        filesMap.forEach((fileProperty, paths) -> {
            if (paths.size() > 1 && !printedFiles.contains(fileProperty)) {
                System.out.println(fileProperty.getName() + " - " + fileProperty.getSize());
                paths.forEach(path -> System.out.println(path.toString().replace(".\\", "")));
                printedFiles.add(fileProperty);
            }
        });
        return FileVisitResult.CONTINUE;
    }
}
