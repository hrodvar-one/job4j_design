package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportAccountingTest {

    private Store store;
    private ReportDateTimeParser dateTimeParser;
    private ReportAccounting reportAccounting;

    @BeforeEach
    void setUp() {
        store = new MemoryStore();
        dateTimeParser = new ReportDateTimeParser();
        reportAccounting = new ReportAccounting(store, dateTimeParser);
    }

    @Test
    void whenGenerateReportWithEmployees() {
        Calendar hired = Calendar.getInstance();
        hired.set(2022, Calendar.JANUARY, 1);
        Calendar fired = Calendar.getInstance();
        fired.set(2022, Calendar.DECEMBER, 31);

        Employee employee = new Employee("John Doe", hired, fired, 100000);
        store.add(employee);

        String expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append("John Doe ").append(dateTimeParser.parse(hired)).append(" ")
                .append(dateTimeParser.parse(fired)).append(" ")
                .append(new InMemoryCurrencyConverter().convert(Currency.RUB, 100000, Currency.USD))
                .append(System.lineSeparator())
                .toString();

        String result = reportAccounting.generate(e -> true);
        assertEquals(expected, result);
    }

    @Test
    void whenGenerateReportWithNoEmployees() {
        String expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .toString();

        String result = reportAccounting.generate(e -> true);
        assertEquals(expected, result);
    }
}