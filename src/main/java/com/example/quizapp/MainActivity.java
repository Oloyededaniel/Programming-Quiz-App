package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LinearLayout java,php,html,Android;
    Button startBtn;

    private String selectedTopicName = " ";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         java = findViewById(R.id.javaLayout);
         php = findViewById(R.id.phpLayout);
         html = findViewById(R.id.htmlLayout);
         Android = findViewById(R.id.androidLayout);
         startBtn = findViewById(R.id.StartQuizBtn);

        // Set an onClickListener to the java layout and update selectedTopicName and background color when clicked
         java.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 selectedTopicName ="java";
                 java.setBackgroundResource(R.drawable.round_back_white_stroke10);

                 php.setBackgroundResource(R.drawable.round_back_white10);
                 html.setBackgroundResource(R.drawable.round_back_white10);
                 Android.setBackgroundResource(R.drawable.round_back_white10);
             }
         });
        // Set an onClickListener to the php layout and update selectedTopicName and background color when clicked
         php.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 selectedTopicName ="php";
                 php.setBackgroundResource(R.drawable.round_back_white_stroke10);

                 java.setBackgroundResource(R.drawable.round_back_white10);
                 html.setBackgroundResource(R.drawable.round_back_white10);
                 Android.setBackgroundResource(R.drawable.round_back_white10);

             }
         });
        // Set an onClickListener to the html layout and update selectedTopicName and background color when clicked
         html.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 selectedTopicName ="html";
                 html.setBackgroundResource(R.drawable.round_back_white_stroke10);

                 php.setBackgroundResource(R.drawable.round_back_white10);
                 java.setBackgroundResource(R.drawable.round_back_white10);
                 Android.setBackgroundResource(R.drawable.round_back_white10);
             }
         });
        // Set an onClickListener to the Android layout and update selectedTopicName and background color when clicked
         Android.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 selectedTopicName ="android";
                 Android.setBackgroundResource(R.drawable.round_back_white_stroke10);

                 php.setBackgroundResource(R.drawable.round_back_white10);
                 html.setBackgroundResource(R.drawable.round_back_white10);
                 java.setBackgroundResource(R.drawable.round_back_white10);
             }
         });
        // Set an onClickListener to the startBtn and start a new QuizActivity with the selected topic name when clicked
         startBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(selectedTopicName.isEmpty()){
                     // Display a toast message if no topic is selected
                     Toast.makeText(MainActivity.this, "Please select a topic", Toast.LENGTH_SHORT).show();
                 }
                 else{
                     // Start a new QuizActivity with the selected topic name
                     Intent intent = new Intent(MainActivity.this,QuizActivity.class);
                     intent.putExtra("selectedTopic",selectedTopicName);
                     startActivity(intent);
                 }
             }
         });
    }
}