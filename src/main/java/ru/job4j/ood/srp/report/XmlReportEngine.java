package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.Employees;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class XmlReportEngine implements Report {

    private final MemoryStore memoryStore;
    private final DateTimeParser<Calendar> dateTimeParser;

    public XmlReportEngine(MemoryStore memoryStore) {
        this.memoryStore = memoryStore;
        this.dateTimeParser = new ReportDateTimeParser();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = memoryStore.findBy(filter);
        employees.forEach(employee -> {
            employee.setHiredString(dateTimeParser.parse(employee.getHired()));
            employee.setFiredString(dateTimeParser.parse(employee.getFired()));
        });
        Employees employeesWrapper = new Employees(employees);
        try {
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter writer = new StringWriter();
            marshaller.marshal(employeesWrapper, writer);
            return writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            return "";
        }
    }
}
