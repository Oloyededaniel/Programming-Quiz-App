//package com.example.quizapp;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//public class SignUpActivity extends AppCompatActivity {
//    EditText Confirm_pwd,ed_username, ed_password;
//    Button btnCreateUser;
//
//    private final String CREDENTIAL_SHARED_PREF = "our_shared_pref";
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_up);
//
//        ed_username = findViewById(R.id.edUsername);
//        ed_password =findViewById(R.id.edPassword);
//        Confirm_pwd = findViewById(R.id.Confirm_pwd);
//        btnCreateUser = findViewById(R.id.btn_create_user);
//
//        btnCreateUser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String strPassword = ed_password.getText().toString();
//                String strConfirmPassword = Confirm_pwd.getText().toString();
//                String strUsername = ed_username.getText().toString();
//
//                if(strPassword!= null && strConfirmPassword != null && strPassword.equalsIgnoreCase(strConfirmPassword)){
//                    SharedPreferences credentials = getSharedPreferences(CREDENTIAL_SHARED_PREF,MODE_PRIVATE);
//                    SharedPreferences. Editor editor = credentials.edit();
//                    editor.putString("Password",strPassword);
//                    editor.putString("Username",strUsername);
//                    editor.commit();
//
//                    Toast.makeText(SignUpActivity.this, "User Created", Toast.LENGTH_SHORT).show();
//
//                    SignUpActivity.this.finish();
//
//                    Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
//                    startActivity(intent);
//                }
//            }
//        });
//    }
//}

package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    EditText Confirm_pwd,ed_username, ed_password;
    Button btnCreateUser;

    private final String CREDENTIAL_SHARED_PREF = "our_shared_pref";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ed_username = findViewById(R.id.edUsername);
        ed_password = findViewById(R.id.edPassword);
        Confirm_pwd = findViewById(R.id.Confirm_pwd);
        btnCreateUser = findViewById(R.id.btn_create_user);

        // Set a listener for the "Create User" button
        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strPassword = ed_password.getText().toString();
                String strConfirmPassword = Confirm_pwd.getText().toString();
                String strUsername = ed_username.getText().toString();

                // Check if all fields are filled
                if (TextUtils.isEmpty(strUsername)) {
                    ed_username.setError("Please enter a username");
                    return;
                }
                if (TextUtils.isEmpty(strPassword)) {
                    ed_password.setError("Please enter a password");
                    return;
                }
                if (TextUtils.isEmpty(strConfirmPassword)) {
                    Confirm_pwd.setError("Please confirm your password");
                    return;
                }

                // Check if passwords match
                if (strPassword.equalsIgnoreCase(strConfirmPassword)) {
                    SharedPreferences credentials = getSharedPreferences(CREDENTIAL_SHARED_PREF,MODE_PRIVATE);
                    SharedPreferences.Editor editor = credentials.edit();
                    editor.putString("Password",strPassword);
                    editor.putString("Username",strUsername);
                    editor.apply();
                    // Show a success message
                    Toast.makeText(SignUpActivity.this, "User Created", Toast.LENGTH_SHORT).show();

                    SignUpActivity.this.finish();
                    // Finish this activity and start the MainActivity
                    Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                    startActivity(intent);
                } else {
                    // If the passwords don't match, show an error message
                    Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
