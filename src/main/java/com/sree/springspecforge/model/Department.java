package com.sree.springspecforge.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents a Department entity in the system.
 * This entity maps to the 'dept' table in the database.
 */
@Entity
@Table(name = "dept")
public class Department {

    @Id
    @Column(name = "deptno")
    private Integer deptno;

    @Column(name = "deptname", nullable = false)
    private String deptname;

    @Column(name = "location", nullable = false)
    private String location; // New column: location

    @Column(name = "salary", nullable = false, precision = 10, scale = 2)
    private BigDecimal salary; // New column: salary

    /**
     * Default constructor for JPA.
     */
    public Department() {
    }

    /**
     * Parameterized constructor to create a Department instance.
     * @param deptno The unique department number.
     * @param deptname The name of the department.
     * @param location The location of the department.
     * @param salary The salary associated with the department.
     */
    public Department(Integer deptno, String deptname, String location, BigDecimal salary) {
        this.deptno = deptno;
        this.deptname = deptname;
        this.location = location;
        this.salary = salary;
    }

    // Getters

    public Integer getDeptno() {
        return deptno;
    }

    public String getDeptname() {
        return deptname;
    }

    public String getLocation() {
        return location;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    // Setters

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(deptno, that.deptno) &&
               Objects.equals(deptname, that.deptname) &&
               Objects.equals(location, that.location) &&
               Objects.equals(salary, that.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptno, deptname, location, salary);
    }

    @Override
    public String toString() {
        return "Department{" +
               "deptno=" + deptno +
               ", deptname='" + deptname + '\'' +
               ", location='" + location + '\'' +
               ", salary=" + salary +
               '}';
    }
}