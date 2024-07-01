package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        var input = new Scanner(System.in);
        var startAt = 1;

        while (startAt <= 100) {
            String computerAnswer = getExpectedAnswer(startAt);
            System.out.println(computerAnswer);
            startAt++;

            if (startAt > 100) {
                break;
            }

            String userAnswer = input.nextLine();

            if (!getExpectedAnswer(startAt).equals(userAnswer)) {
                System.out.println("Ошибка. Начинай снова.");
                startAt = 1;
                continue;
            }
            startAt++;
        }
        System.out.println("Игра завершена.");
    }

    public static String getExpectedAnswer(int number) {
        if (number % 3 == 0 && number % 5 == 0) {
            return "FizzBuzz";
        } else if (number % 3 == 0) {
            return "Fizz";
        } else if (number % 5 == 0) {
            return "Buzz";
        } else {
            return String.valueOf(number);
        }
    }
}
