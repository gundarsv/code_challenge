package com.code.challenge.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = Account.TABLE_NAME)
public class Account implements Serializable {
    public static final String TABLE_NAME= "user_accounts";

    @Id
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 12)
    private Long phone;

    @Column(nullable = false, length = 200)
    private String email;

    @Column(nullable = true, length = 200)
    private String address;

    @Column(nullable = false, length = 56)
    private String country;

    @Column(nullable = false, length = 50)
    private String department;

    public Account() {
    }

    public Account(Long id, String name, Long phone, String email, String address, String country, String department) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.country = country;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
