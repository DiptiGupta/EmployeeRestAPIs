package com.wp.emp.dto;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

public class EmployeeDTO {

    @NotNull(message="First Name is required")
	@NotBlank(message="First Name is required")
	private String firstname;

    @NotNull(message="Last Name is required")
	@NotBlank(message="Last Name is required")
	private String lastname;

    @NotNull(message="Boss Name is required")
	@NotBlank(message="Boss Name is required")
	private String boss;

    @NotNull(message="Salary is required")
	private Long salary;

    @NotNull(message="Address is required")
	@NotBlank(message="Address is required")
	private String address;

    @NotNull(message="Date of Birth is required")
	@Past(message="Date of Birth must be in past")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateOfBirth;

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", boss='" + boss + '\'' +
                ", salary=" + salary +
                ", address='" + address + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBoss() {
        return boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
