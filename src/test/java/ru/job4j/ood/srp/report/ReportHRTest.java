package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportHRTest {

    private Store store;
    private ReportDateTimeParser dateTimeParser;
    private ReportHR reportHR;

    @BeforeEach
    void setUp() {
        store = new MemoryStore();
        dateTimeParser = new ReportDateTimeParser();
        reportHR = new ReportHR(store, dateTimeParser);
    }

    @Test
    void whenGenerateReportWithEmployees() {
        Calendar now = Calendar.getInstance();
        Employee emp1 = new Employee("Alice", now, now, 30000);
        Employee emp2 = new Employee("Bob", now, now, 50000);
        Employee emp3 = new Employee("Charlie", now, now, 40000);
        store.add(emp1);
        store.add(emp2);
        store.add(emp3);

        String expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append("Bob 50000.0")
                .append(System.lineSeparator())
                .append("Charlie 40000.0")
                .append(System.lineSeparator())
                .append("Alice 30000.0")
                .append(System.lineSeparator())
                .toString();

        String result = reportHR.generate(e -> true);
        assertEquals(expected, result);
    }

    @Test
    void whenGenerateReportWithNoEmployees() {
        String expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .toString();

        String result = reportHR.generate(e -> true);
        assertEquals(expected, result);
    }
}