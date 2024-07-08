package ru.job4j.ood.srp.model;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.util.Calendar;
import java.util.Objects;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "hiredString", "firedString", "salary"})
public class Employee {
    @XmlElement
    @Expose
    private String name;
    @XmlTransient
    private Calendar hired;
    @XmlTransient
    private Calendar fired;
    @XmlElement
    @Expose
    private double salary;
    @XmlElement(name = "hired")
    @Expose
    private String hiredString;
    @XmlElement(name =  "fired")
    @Expose
    private String firedString;

    public Employee() {
    }

    public Employee(String name, Calendar hired, Calendar fired, double salary) {
        this.name = name;
        this.hired = hired;
        this.fired = fired;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public Calendar getHired() {
        return hired;
    }

    public Calendar getFired() {
        return fired;
    }

    public double getSalary() {
        return salary;
    }

    public String getHiredString() {
        return hiredString;
    }

    public void setHiredString(String hiredString) {
        this.hiredString = hiredString;
    }

    public String getFiredString() {
        return firedString;
    }

    public void setFiredString(String firedString) {
        this.firedString = firedString;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHired(Calendar hired) {
        this.hired = hired;
    }

    public void setFired(Calendar fired) {
        this.fired = fired;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
