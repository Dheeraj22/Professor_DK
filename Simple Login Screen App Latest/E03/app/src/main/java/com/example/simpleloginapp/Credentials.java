package com.example.simpleloginapp;

import java.util.HashMap;

public class Credentials {

    /* Hashmap to store credentials */
    private static HashMap<String, String> credentialsMapper = new HashMap<String, String>();

    /* Function to add new credentials */
    public static void addCredentials(String name, String password)
    {
        credentialsMapper.put(name, password);
    }

    /* Function to check if the credentials entered are valid */
    public static boolean checkCredentials(String name, String password)
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
