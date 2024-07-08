package ru.job4j.ood.srp.report;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.job4j.ood.srp.model.Employee;

import java.lang.reflect.Type;

public class EmployeeAdapter implements JsonSerializer<Employee> {
    @Override
    public JsonElement serialize(Employee employee, Type type, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", employee.getName());
        jsonObject.addProperty("hired", employee.getHiredString());
        jsonObject.addProperty("fired", employee.getFiredString());
        jsonObject.addProperty("salary", employee.getSalary());
        return jsonObject;
    }
}
