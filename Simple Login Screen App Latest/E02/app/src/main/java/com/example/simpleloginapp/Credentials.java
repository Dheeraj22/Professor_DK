package com.example.simpleloginapp;

import java.util.HashMap;

public class Credentials {

    /* Set default values for credentials */
    private String name = "Admin";
    private String password = "123456";

    /* Getters and Setters for name and password */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
