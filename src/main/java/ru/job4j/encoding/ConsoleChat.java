package ru.job4j.encoding;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {

    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    List<String> log = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        boolean stopTrigger = false;
        label:
        while (true) {
            Scanner input = new Scanner(System.in);
            String userInput = input.nextLine();
            log.add(userInput);
            switch (userInput) {
                case OUT:
                    saveLog(log);
                    break label;
                case STOP:
                    stopTrigger = true;
                    continue;
                case CONTINUE:
                    if (stopTrigger) {
                        stopTrigger = false;
                    }
                    break;
            }
            if (!stopTrigger) {
                List<String> phrases = readPhrases();
                Random random = new Random();
                int randomIndex = random.nextInt(phrases.size());
                String randomString = phrases.get(randomIndex);
                System.out.println(randomString);
                log.add(randomString);
                saveLog(log);
            }
            saveLog(log);
        }
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(botAnswers))) {
            input.lines().forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter output = new BufferedWriter(new FileWriter(path))) {
            for (String phrase : log) {
                output.write(phrase);
                output.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat(
                "data/encoding/botLog.txt",
            "data/encoding/botAnswers.txt");
        consoleChat.run();
    }
}
