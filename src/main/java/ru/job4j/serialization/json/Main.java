package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Person person = new Person(false, 30, new Contact("11-111"),
                new String[] {"Worker", "Married"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));

        final String personJson =
                "{"
                        + "\"sex\":false,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";

        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);

        System.out.println("--------------- My example ---------------");

        final Car car = new Car(false, 1999, new FuelTank(60),
                new String[] {"On repair", "On sale"});

        final Gson gson2 = new GsonBuilder().create();
        System.out.println(gson2.toJson(car));

        final String carJson =
                "{"
                        + "\"isDiesel\":true,"
                        + "\"yearOfManufacture\":2020,"
                        + "\"fuelTank\":"
                        + "{"
                        + "\"capacity\":\"80\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Broken\",\"Sold\"]"
                        + "}";

        final Car carMod = gson2.fromJson(carJson, Car.class);
        System.out.println(carMod);
    }
}
