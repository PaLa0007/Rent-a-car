package com.course_project01_rent_a_car.rent_a_car.entities;

//package com.example.rentacar.model;

import jakarta.persistence.*;

@Entity
@Table(name = "APP_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String phone;

    private int age;

    private boolean hasIncidents; // True if the user has previous accidents

    private boolean isDeleted = false; // Default to false

    // Getter за isDeleted
    public boolean isDeleted() {
        return isDeleted;
    }

    // Setter за isDeleted
    @Column(name = "is_deleted", nullable = false)
    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    // Останалите getter и setter методи
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isHasIncidents() {
        return hasIncidents;
    }

    public void setHasIncidents(boolean hasIncidents) {
        this.hasIncidents = hasIncidents;
    }
}