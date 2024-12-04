package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {
        Handler h = new Handler();  // Create a new Handler object to handle the delay

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Create an Intent to launch the LoginActivity class
                Intent i = new Intent(SplashActivity.this,LoginActivity.class);
                // Start the LoginActivity and remove the SplashActivity from the activity stack
                startActivity(i);
                finish();
            }
        },5000); // Delay for 5 seconds
    }
}


