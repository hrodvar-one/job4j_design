package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            try (BufferedReader input = new BufferedReader(new FileReader(source))) {
                String start = null;
                String end = null;
                String line = input.readLine();
                while (line != null) {
                    String[] words = line.split(" ");
                    String status = words[0];
                    String time = words[1];
                    if (("400").equals(status) || ("500").equals(status)) {
                        if (start == null) {
                            start = time;
                        }
                    } else if (("200").equals(status) || ("300").equals(status)) {
                        if (start != null) {
                            end = time;
                            out.println(start + " " + end);
                            start = null;
                        }
                    }
                    line = input.readLine();
                }
                if (start != null) {
                    out.println(start + ";" + end + ";");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
