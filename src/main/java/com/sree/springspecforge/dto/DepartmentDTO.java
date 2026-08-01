package com.sree.springspecforge.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Data Transfer Object for Department details.
 * Used for creating and updating Department entities.
 */
public class DepartmentDTO {
    private Integer deptno;

    @NotBlank(message = "Department name cannot be empty")
    @Size(max = 255, message = "Department name cannot exceed 255 characters")
    private String deptname;

    @NotBlank(message = "Location cannot be blank")
    @Size(max = 255, message = "Location cannot exceed 255 characters")
    private String location; // New field: location

    @DecimalMin(value = "0.00", inclusive = true, message = "Salary must be a non-negative value")
    @DecimalMax(value = "99999999.99", inclusive = true, message = "Salary cannot exceed 99,999,999.99")
    private BigDecimal salary; // New field: salary

    /**
     * Default constructor.
     */
    public DepartmentDTO() {
    }

    /**
     * Parameterized constructor.
     * @param deptno The department number.
     * @param deptname The name of the department.
     * @param location The location of the department.
     * @param salary The salary associated with the department.
     */
    public DepartmentDTO(Integer deptno, String deptname, String location, BigDecimal salary) {
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
        DepartmentDTO that = (DepartmentDTO) o;
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
        return "DepartmentDTO{" +
               "deptno=" + deptno +
               ", deptname='" + deptname + ''' +
               ", location='" + location + ''' +
               ", salary=" + salary +
               '}';
    }
}