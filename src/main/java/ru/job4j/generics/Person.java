package ru.job4j.generics;

import java.util.Date;

public class Person {
    private String name;

    private int age;

    private Date birthday;

    public Person(String name, int age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    /* getters and setters
       equals and hashcode
       toString */
}
