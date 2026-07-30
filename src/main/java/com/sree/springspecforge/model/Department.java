package com.sree.springspecforge.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

    /**
     * Default constructor for JPA.
     */
    public Department() {
    }

    /**
     * Parameterized constructor to create a Department instance.
     * @param deptno The unique department number.
     * @param deptname The name of the department.
     */
    public Department(Integer deptno, String deptname) {
        this.deptno = deptno;
        this.deptname = deptname;
    }

    // Getters

    public Integer getDeptno() {
        return deptno;
    }

    public String getDeptname() {
        return deptname;
    }

    // Setters

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(deptno, that.deptno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptno);
    }

    @Override
    public String toString() {
        return "Department{" +
               "deptno=" + deptno +
               ", deptname='" + deptname + ''' +
               '}';
    }
}