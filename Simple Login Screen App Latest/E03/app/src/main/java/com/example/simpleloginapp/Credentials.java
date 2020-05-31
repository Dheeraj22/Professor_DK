package com.example.simpleloginapp;

import java.util.HashMap;

public class Credentials {

    private String Username;
    private String Password;

    /* Constructor */
    Credentials(String username, String password){
        this.Username = username;
        this.Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
