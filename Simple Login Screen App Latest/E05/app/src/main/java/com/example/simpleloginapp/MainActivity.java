package com.example.simpleloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    /* Define the UI elements */
    private EditText eName;
    private EditText ePassword;
    private TextView eAttemptsInfo;
    private Button eLogin;
    private TextView eSignUp;
    private CheckBox eRememberMe;

    /* Number of attempts is held in this counter */
    private int counter = 5;

    /* Strings to hold user inputs */
    String userName = "";
    String userPassword = "";

    /* Flag used for validation */
    boolean isValid = false;

    /* Variables for storing data persistently */
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Bind the XML views to Java Code Elements */
        eName = findViewById(R.id.etName);
        ePassword = findViewById(R.id.etPassword);
        eAttemptsInfo = findViewById(R.id.tvAttempts);
        eLogin = findViewById(R.id.btnLogin);
        eSignUp = findViewById(R.id.tvRegister);
        eRememberMe = findViewById(R.id.cbRemember);

        /* Create a sharedpreferences file to hold our values in local phone offline storage */
        sharedPreferences = getApplicationContext().getSharedPreferences("CredentialDB", Context.MODE_PRIVATE);

        sharedPreferencesEditor = sharedPreferences.edit();

        /* If the retrieved local copy isn't NULL, add logic */
        if(sharedPreferences != null)
        {
            /* Retrieve all records from the local file */
            Map<String, ?> allEntries = sharedPreferences.getAll();

            /* Loop through each record and add to credentials */
            for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                RegistrationActivity.credentials.addCredentials(entry.getKey(), entry.getValue().toString());
            }

            if(sharedPreferences.getBoolean("RememberMeCheckBox", false)){

                String savedUsername = sharedPreferences.getString("Username", "");
                String savedPassword = sharedPreferences.getString("Password", "");

                eName.setText(savedUsername);
                ePassword.setText(savedPassword);
                eRememberMe.setChecked(true);
            }
        }

        /* Describe the logic when the login button is clicked */
        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Obtain user inputs */
                userName = eName.getText().toString();
                userPassword = ePassword.getText().toString();

                /* Check if the user inputs are empty */
                if(userName.isEmpty() || userPassword.isEmpty())
                {
                    /* Display a message toast to user to enter the details */
                    Toast.makeText(MainActivity.this, "Please enter name and password!", Toast.LENGTH_LONG).show();

                }else {

                    /* Validate the user inputs */
                    isValid = RegistrationActivity.credentials.checkCredentials(userName, userPassword);

                    /* Validate the user inputs */

                    /* If not valid */
                    if (!isValid) {

                        /* Decrement the counter */
                        counter--;

                        /* Show the attempts remaining */
                        eAttemptsInfo.setText("Attempts Remaining: " + String.valueOf(counter));

                        /* Disable the login button if there are no attempts left */
                        if (counter == 0) {
                            eLogin.setEnabled(false);
                            Toast.makeText(MainActivity.this, "You have used all your attempts try again later!", Toast.LENGTH_LONG).show();
                        }
                        /* Display error message */
                        else {
                            Toast.makeText(MainActivity.this, "Incorrect credentials, please try again!", Toast.LENGTH_LONG).show();
                        }
                    }
                    /* If valid */
                    else {

                        /* Save the checkbox remember me state */
                        sharedPreferencesEditor.putBoolean("RememberMeCheckBox", eRememberMe.isChecked());

                        if(eRememberMe.isChecked()){
                            sharedPreferencesEditor.putString("Username", eName.getText().toString());
                            sharedPreferencesEditor.putString("Password", ePassword.getText().toString());
                        }

                        sharedPreferencesEditor.apply();

                        /* Allow the user in to your app by going into the next activity */
                        startActivity(new Intent(MainActivity.this, HomePageActivity.class));
                    }

                }
            }
        });

        /* Go to Registration Activity when SignUp is clicked */
        eSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });
    }
}
