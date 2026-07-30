package com.sree.springspecforge.dto;

import java.util.Objects;

/**
 * Data Transfer Object for User response, including department and address details.
 * This DTO is used to send user information back to the client.
 * <p>
 * Department fields remain flat ({@code deptno}, {@code deptname}) for backward compatibility.
 * Address is nested as {@link AddressDTO} and may be {@code null} when no address is linked.
 * </p>
 */
public class UserResponseDTO {
    private Long id;
    private String name;
    private String role;
    private String email;
    private Integer deptno;
    private String deptname;
    private AddressDTO address;

    /**
     * Default constructor.
     */
    public UserResponseDTO() {
    }

    /**
     * Parameterized constructor without address (backward compatible).
     *
     * @param id       The unique identifier of the user.
     * @param name     The name of the user.
     * @param role     The role of the user (e.g., USER, ADMIN, GUEST).
     * @param email    The email address of the user.
     * @param deptno   The department number the user belongs to. Can be null.
     * @param deptname The name of the department the user belongs to. Can be null.
     */
    public UserResponseDTO(Long id, String name, String role, String email, Integer deptno, String deptname) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.email = email;
        this.deptno = deptno;
        this.deptname = deptname;
    }

    /**
     * Full parameterized constructor including nested address.
     *
     * @param id       The unique identifier of the user.
     * @param name     The name of the user.
     * @param role     The role of the user (e.g., USER, ADMIN, GUEST).
     * @param email    The email address of the user.
     * @param deptno   The department number the user belongs to. Can be null.
     * @param deptname The name of the department the user belongs to. Can be null.
     * @param address  Nested address details, or null if none linked.
     */
    public UserResponseDTO(Long id, String name, String role, String email, Integer deptno, String deptname,
                           AddressDTO address) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.email = email;
        this.deptno = deptno;
        this.deptname = deptname;
        this.address = address;
    }

    // Getters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public String getDeptname() {
        return deptname;
    }

    public AddressDTO getAddress() {
        return address;
    }

    // Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserResponseDTO that = (UserResponseDTO) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(role, that.role)
                && Objects.equals(email, that.email)
                && Objects.equals(deptno, that.deptno)
                && Objects.equals(deptname, that.deptname)
                && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, role, email, deptno, deptname, address);
    }

    @Override
    public String toString() {
        return "UserResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", deptno=" + deptno +
                ", deptname='" + deptname + '\'' +
                ", address=" + address +
                '}';
    }
}
