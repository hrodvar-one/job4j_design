package ru.job4j.io;

import java.io.File;
import java.io.IOException;

public class DirectoryExample {
    public static void main(String[] args) throws IOException {
        File directory = new File("src/main/java/ru/job4j/io/files/directory");
        directory.mkdirs();
        File target = new File("src/main/java/ru/job4j/io/files");
        File fileOne = new File("src/main/java/ru/job4j/io/files/file1.txt");
        fileOne.createNewFile();
        File fileTwo = new File("src/main/java/ru/job4j/io/files/directory/file2.txt");
        fileTwo.createNewFile();
        String[] list = target.list();
        for (String fileName : list) {
            System.out.println(fileName);
        }
        File[] listFiles = target.listFiles();
        for (File file : listFiles) {
            System.out.println(file);
        }
    }
}
