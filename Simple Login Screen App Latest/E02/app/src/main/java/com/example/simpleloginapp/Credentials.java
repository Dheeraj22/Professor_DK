package com.example.simpleloginapp;

public class Credentials {

    /* Set default values for credentials */
    private String Username;
    private String Userpassword;

    /* Constructor */
    Credentials(String username, String userpassword){
        this.Username = username;
        this.Userpassword = userpassword;
    }

    /* Getters and Setters for name and password */

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getUserpassword() {
        return Userpassword;
    }

    public void setUserpassword(String userpassword) {
        Userpassword = userpassword;
    }
}
