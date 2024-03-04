package ru.job4j.generics;

import java.util.*;

public class GenericUsage {

    public void printResult(Collection<?> collection) {
        for (Iterator<?> iterator = collection.iterator(); iterator.hasNext();) {
            Object next = iterator.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printInfo(Collection<? extends Person> collection) {
        for (Iterator<? extends Person> iterator = collection.iterator(); iterator.hasNext();) {
            Person next = iterator.next();
            System.out.println(next);
        }
    }

    public void addAll(List<? super Integer> list) {
        for (int i = 1; i <= 5; i++) {
            list.add(i);
        }
        for (Object line : list) {
            System.out.println("Текущий элемент: " + line);
        }
    }

    public static void main(String[] args) {
//        List<Integer> list = List.of(1, 2, 3, 4, 5);
//        new GenericUsage().printResult(list);

//        List<Person> per = List.of(new Person("name", 21, new Date(913716000000L)));
//        new GenericUsage().printInfo(per);
//
//        List<Programmer> pro = List.of(new Programmer("name123", 23, new Date(913716000000L)));
//        new GenericUsage().printInfo(pro);

        List<? super Integer> list = new ArrayList<>();
        new GenericUsage().addAll(list);
    }
}
