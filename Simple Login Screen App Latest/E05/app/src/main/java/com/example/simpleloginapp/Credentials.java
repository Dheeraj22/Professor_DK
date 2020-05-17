package com.example.simpleloginapp;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

class Credentials {

    /* Hashmap to store credentials */
    private HashMap<String, String> credentialsMapper = new HashMap<String, String>();

    /* Function to add new credentials */
    void addCredentials(String name, String password)
    {
        credentialsMapper.put(name, password);
    }

    /* Function to check if the credentials entered are valid */
    boolean checkCredentials(String name, String password)
    {
        /* Check if the name of this user is stored or not */
        if(credentialsMapper.containsKey(name))
        {
            /* Check if the password stored against that user matches */
            if(password.equals(credentialsMapper.get(name)))
            {
                return true;
            }
        }

        return false;
    }
}
