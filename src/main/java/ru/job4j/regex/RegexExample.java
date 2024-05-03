package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("123");
        String text = "1231 и 1232 и 1233";
        Matcher matcher = pattern.matcher(text);
        String result = matcher.replaceAll("Job4j");
        System.out.println(result);
    }
}
