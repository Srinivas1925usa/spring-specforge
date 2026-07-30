package com.sree.springspecforge.dto;

import java.util.Objects;

/**
 * Data Transfer Object for Department details.
 * Used for creating and updating Department entities.
 */
public class DepartmentDTO {
    private Integer deptno;
    private String deptname;

    /**
     * Default constructor.
     */
    public DepartmentDTO() {
    }

    /**
     * Parameterized constructor.
     * @param deptno The department number.
     * @param deptname The name of the department.
     */
    public DepartmentDTO(Integer deptno, String deptname) {
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
        DepartmentDTO that = (DepartmentDTO) o;
        return Objects.equals(deptno, that.deptno) && Objects.equals(deptname, that.deptname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptno, deptname);
    }

    @Override
    public String toString() {
        return "DepartmentDTO{" +
               "deptno=" + deptno +
               ", deptname='" + deptname + ''' +
               '}';
    }
}