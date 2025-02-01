package com.example.crud.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students_details")
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;
    private String name;
    private String email;
    private String phone;
    private int age;
    private String address;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Students(int id, String name, String email, String phone, int age, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.address = address;
    }
    public Students() {

    }



}
