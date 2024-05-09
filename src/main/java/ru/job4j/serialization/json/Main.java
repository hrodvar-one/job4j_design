package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");

        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);

        final Person person = new Person(false, 30,
                new Contact("11-111"),
                new String[] {"Worker", "Married"}
        );
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", person.getSex());
        jsonObject.put("age", person.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);

        System.out.println(jsonObject.toString());

        System.out.println(new JSONObject(person).toString());

        System.out.println("--------------- My example ---------------");

        JSONObject jsonFueltank = new JSONObject("{\"capacity\":\"90\"}");

        List<String> listMyExample = new ArrayList<>();
        listMyExample.add("Student");
        listMyExample.add("Free");
        JSONArray jsonMyStatuses = new JSONArray(listMyExample);

        final Car car = new Car(false, 1999,
                new FuelTank(60),
                new String[] {"Broken", "Sold"}
        );
        JSONObject jsonMyObject = new JSONObject();
        jsonMyObject.put("isDiesel", car.getIsDiesel());
        jsonMyObject.put("yearOfManufacture", car.getYearOfManufacture());
        jsonMyObject.put("fuelTank", jsonFueltank);
        jsonMyObject.put("statuses", jsonMyStatuses);

        System.out.println(jsonMyObject.toString());

        System.out.println(new JSONObject(car).toString());
    }
}
