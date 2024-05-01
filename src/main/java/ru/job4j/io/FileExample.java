package ru.job4j.io;

import java.io.File;
import java.io.IOException;

public class FileExample {
    public static void main(String[] args) throws IOException {
        File directory = new File("src/main/java/ru/job4j/io/files");
        directory.mkdir();
        File file = new File("src/main/java/ru/job4j/io/files/file.txt");
        file.createNewFile();
        System.out.println("Файл/директория существует?: " + file.exists());
        System.out.println("Это директория?: " + file.isDirectory());
        System.out.println("Это файл?: " + file.isFile());
        System.out.println("Имя файла: " + file.getName());
        System.out.println("Путь к файлу: " + file.getPath());
        System.out.println("Путь к файлу абсолютный?: " + file.isAbsolute());
        System.out.println("Относительный путь к родительской директории файла: " + file.getParent());
        System.out.println("Абсолютный путь к файлу: " + file.getAbsoluteFile());
        System.out.println("Абсолютный путь к директории: " + directory.getAbsolutePath());
        System.out.println("Доступен для чтения?: " + file.canRead());
        System.out.println("Доступен для записи?: " + file.canWrite());
        System.out.println("Длина файла: " + file.length());
        File newFile = new File("src/main/java/ru/job4j/io/files/newFile.txt");
        System.out.println("Переименование файла в newFile. Успешно?: " + file.renameTo(newFile));
    }
}
