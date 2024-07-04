package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportProgrammersTest {

    private Store store;
    private ReportDateTimeParser dateTimeParser;
    private ReportProgrammers reportProgrammers;

    @BeforeEach
    void setUp() {
        store = new MemoryStore();
        dateTimeParser = new ReportDateTimeParser();
        reportProgrammers = new ReportProgrammers(store, dateTimeParser);
    }

    @Test
    void whenGenerateReportWithEmployees() {
        Calendar hired = Calendar.getInstance();
        hired.set(2022, Calendar.JANUARY, 1);
        Calendar fired = Calendar.getInstance();
        fired.set(2022, Calendar.DECEMBER, 31);

        Employee emp1 = new Employee("Alice", hired, fired, 30000);
        Employee emp2 = new Employee("Bob", hired, fired, 50000);
        Employee emp3 = new Employee("Charlie", hired, fired, 40000);
        store.add(emp1);
        store.add(emp2);
        store.add(emp3);

        String expected = new StringBuilder()
                .append("Name,Hired,Fired,Salary")
                .append(System.lineSeparator())
                .append("Alice,").append(dateTimeParser.parse(hired)).append(",")
                .append(dateTimeParser.parse(fired)).append(",")
                .append("30000.0")
                .append(System.lineSeparator())
                .append("Bob,").append(dateTimeParser.parse(hired)).append(",")
                .append(dateTimeParser.parse(fired)).append(",")
                .append("50000.0")
                .append(System.lineSeparator())
                .append("Charlie,").append(dateTimeParser.parse(hired)).append(",")
                .append(dateTimeParser.parse(fired)).append(",")
                .append("40000.0")
                .append(System.lineSeparator())
                .toString();

        String result = reportProgrammers.generate(e -> true);
        assertEquals(expected, result);
    }

    @Test
    void whenGenerateReportWithNoEmployees() {
        String expected = new StringBuilder()
                .append("Name,Hired,Fired,Salary")
                .append(System.lineSeparator())
                .toString();

        String result = reportProgrammers.generate(e -> true);
        assertEquals(expected, result);
    }
}