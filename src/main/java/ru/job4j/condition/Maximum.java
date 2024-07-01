package ru.job4j.condition;

public class Maximum {
    public static int getMax(int left, int right) {
        return left > right ? left : right;
    }

    public static int max(int first, int second, int third, int forth) {
        return getMax(
                getMax(first, second),
                getMax(third, forth)
        );
    }
}
