package com.example.cs3200_hw3.models;

public class User {
    private String name;
    private String password;

    public User() {}

    public User(String email, String password) {
        this.name = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
