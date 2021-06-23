package com.code.challenge.presentation.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Validated
public class AccountDTO {
    @NotNull(message = "Id is required")
    private Long id;

    @Size(min = 3, max = 150, message = "Name must be between 3 and 150 characters long")
    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "Phone is required")
    private Long phone;

    @Size(max = 200, message = "Email can not be more than 200 characters long")
    @NotNull(message = "Email is required")
    private String email;

    @Size(max = 200,message = "Address can not be more than 200 characters long")
    private String address;

    @Size(max = 200, message = "Country can not be more than 200 characters long")
    @NotNull(message = "Country is required")
    private String country;

    @Size(max = 50, message = "Departament can not be more than 50 characters long")
    private String department;

    public AccountDTO() {
    }

    public AccountDTO(Long id, String name, Long phone, String email, String address, String country, String department) {
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

    @JsonIgnore
    @AssertTrue(message = "Phone number is not valid")
    public boolean isPhoneValid() {
        if (this.phone == null)
        {
            return true;
        }

        return 100000000L <= this.phone && this.phone <= 999999999999L;
    }
}
