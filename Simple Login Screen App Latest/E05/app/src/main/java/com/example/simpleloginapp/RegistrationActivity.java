package com.example.simpleloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    /* Define the UI elements */
    private EditText eRegName;
    private EditText eRegPassword;
    private Button eRegister;

    /* Create an object of the class Credentials */
    public static Credentials credentials = new Credentials();

    /* Local Storage variables */
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        /* Bind the UI elements with the XML layout elements */
        eRegName = findViewById(R.id.etRegName);
        eRegPassword = findViewById(R.id.etRegPassword);
        eRegister = findViewById(R.id.btnRegister);

        /* Create a sharedpreferences file to hold our values in local phone offline storage */
        sharedPreferences = getApplicationContext().getSharedPreferences("CredentialDB", Context.MODE_PRIVATE);

        /* Add an editor to add records to this file */
        sharedPreferencesEditor = sharedPreferences.edit();

        /* A listener for click events on the Register Button */
        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Obtain the inputs from the fields */
                String registeredName = eRegName.getText().toString();
                String registeredPassword = eRegPassword.getText().toString();

                /* Check if the fields are valid */
                if(validate(registeredName, registeredPassword))
                {
                    /* Add the credentials into our database */
                    credentials.addCredentials(registeredName, registeredPassword);

                    /* Save a local copy of the credentials */
                    sharedPreferencesEditor.putString(registeredName, registeredPassword);
                    sharedPreferencesEditor.apply();

                    Toast.makeText(RegistrationActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();

                    /* Go to Login Activity */
                    startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                }
            }
        });
    }

    /* Function for validating the input credentials from the user */
    boolean validate(String name, String password)
    {
        /* Check if the name is empty and password field is 8 characters long */
        if(name.isEmpty() || (password.length() < 8))
        {
            Toast.makeText(this, "Please enter your name and ensure password is more than 8 characters long!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
