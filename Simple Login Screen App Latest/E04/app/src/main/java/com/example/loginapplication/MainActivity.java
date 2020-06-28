package com.example.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText eName;
    private EditText ePassword;
    private Button eLogin;
    private TextView eAttemptsInfo;
    private TextView eRegister;
    private CheckBox eRememberMe;

    boolean isValid = false;
    private int counter = 5;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eName = findViewById(R.id.etName);
        ePassword = findViewById(R.id.etPassword);
        eLogin = findViewById(R.id.btnLogin);
        eAttemptsInfo = findViewById(R.id.tvAttemptsInfo);
        eRegister = findViewById(R.id.tvRegister);
        eRememberMe = findViewById(R.id.cbRememberMe);

        sharedPreferences = getApplicationContext().getSharedPreferences("CredentialsDB", MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        if(sharedPreferences != null){

            String savedUsername = sharedPreferences.getString("Username", "");
            String savedPassword = sharedPreferences.getString("Password", "");

            RegistrationActivity.credentials = new Credentials(savedUsername, savedPassword);

            if(sharedPreferences.getBoolean("RememberMeCheckbox", false)){
                eName.setText(savedUsername);
                ePassword.setText(savedPassword);
                eRememberMe.setChecked(true);
            }
        }

        eRememberMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferencesEditor.putBoolean("RememberMeCheckbox", eRememberMe.isChecked());

                sharedPreferencesEditor.apply();
            }
        });

        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputName = eName.getText().toString();
                String inputPassword = ePassword.getText().toString();

                if(inputName.isEmpty() || inputPassword.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please enter all the details correctly!", Toast.LENGTH_SHORT).show();
                }else{

                    isValid = validate(inputName, inputPassword);

                    if(!isValid){

                        counter--;

                        Toast.makeText(MainActivity.this, "Incorrect credentials entered!", Toast.LENGTH_SHORT).show();

                        eAttemptsInfo.setText("No. of attempts remaining: " + counter);

                        if(counter == 0){
                            eLogin.setEnabled(false);
                        }

                    }else{

                        Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();

                        // Add the code to go to new activity
                        Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                        startActivity(intent);
                    }

                }

            }
        });
    }

    private boolean validate(String name, String password){

        if(RegistrationActivity.credentials != null){
            if(name.equals(RegistrationActivity.credentials.getUsername()) && password.equals(RegistrationActivity.credentials.getPassword())){
                return true;
            }
        }

        return false;
    }
}
