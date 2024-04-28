package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class ResultFile {
    public static void main(String[] args) {
        try (PrintWriter output = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("data/dataresult.txt")
                ))) {
            output.println("""
                        1 * 2 = 2
                        1 * 3 = 3
                        1 * 4 = 4
                        1 * 5 = 5
                        1 * 6 = 6
                        1 * 7 = 7
                        1 * 8 = 8
                        1 * 9 = 9
                        """);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
