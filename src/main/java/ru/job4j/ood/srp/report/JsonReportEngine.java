package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class JsonReportEngine implements Report {

    private final MemoryStore memoryStore;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final Gson gson;

    public JsonReportEngine(MemoryStore memoryStore, DateTimeParser<Calendar> dateTimeParser) {
        this.memoryStore = memoryStore;
        this.dateTimeParser = dateTimeParser;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Employee.class, new EmployeeAdapter())
                .create();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = memoryStore.findBy(filter);
        employees.forEach(employee -> {
            employee.setHiredString(dateTimeParser.parse(employee.getHired()));
            employee.setFiredString(dateTimeParser.parse(employee.getFired()));
        });
        return gson.toJson(employees);
    }
}
