package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText ed_username, ed_password;
    Button btn_login, btn_signup;

    private final String CREDENTIAL_SHARED_PREF = "our_shared_pref";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed_username = findViewById(R.id.ed_username);
        ed_password = findViewById(R.id.ed_password);
        btn_login = findViewById(R.id.btn_login);
        btn_signup = findViewById(R.id.btn_signup);

        // Set up the OnClickListener for the signup button
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to launch the SignUpActivity class
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the SharedPreferences object
                SharedPreferences credentials = getSharedPreferences(CREDENTIAL_SHARED_PREF,MODE_PRIVATE);
                // Get the saved username and password from SharedPreferences
                String strUsername = credentials.getString("Username",null);
                String strPassword = credentials.getString("Password",null);
                // Get the username and password entered by the user
                String username_from_ed = ed_username.getText().toString();
                String password_from_ed = ed_password.getText().toString();
                // Check if either field is empty and show an error if necessary
                boolean isUsernameEmpty = username_from_ed.trim().isEmpty();
                boolean isPasswordEmpty = password_from_ed.trim().isEmpty();

                if (isUsernameEmpty) {
                    ed_username.setError("Username cannot be empty");
                }
                if (isPasswordEmpty) {
                    ed_password.setError("Password cannot be empty");
                }
                // If both fields are filled, check the username and password
                if(!isUsernameEmpty && !isPasswordEmpty) {
                    if(strUsername != null && strUsername.equalsIgnoreCase(username_from_ed)){
                        if(strPassword != null && strPassword.equalsIgnoreCase(password_from_ed)){
                            // Show a toast indicating successful login
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            // Create an Intent to launch the MainActivity class
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            // Show a toast indicating unsuccessful login
                            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        // Show a toast indicating unsuccessful login
                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}
