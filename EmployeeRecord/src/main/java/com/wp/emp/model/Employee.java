package com.wp.emp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "emp_id", nullable = false, updatable = false)
	private Long id;

    @Column(name = "firstname", nullable = false)
	private String firstName;

    @Column(name = "lastname", nullable = false)
	private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBoss() {
        return boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    @Column(name = "boss", nullable = false)
	private String boss;

    @Column(name = "address", nullable = false)
	private String address;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date", nullable = false)
	private Date birthDate;

    @Column(name = "salary", nullable = false)
	private Long salary;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", boss='" + boss + '\'' +
                ", address='" + address + '\'' +
                ", birthDate=" + birthDate +
                ", salary=" + salary +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }

    @JsonIgnore
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

     @JsonIgnore
    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Column(name = "created_date", nullable = false)
	private Date createdDate;

    @Column(name = "updated_date", nullable = false)
	private Date updatedDate;


}
