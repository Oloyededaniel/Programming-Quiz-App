package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class QuizResults extends AppCompatActivity {
    AppCompatButton startNewBtn;
    TextView  correctAnswer,incorrectAnswer;
    MediaPlayer mp;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        // Create a media player and start playing the congratulations sound
        mp = MediaPlayer.create(this, R.raw.congratulations2);
        mp.start();

        startNewBtn = findViewById(R.id.startNewQuizBtn);
        correctAnswer = findViewById(R.id.correctAnswers);
        incorrectAnswer = findViewById(R.id.incorrectAnswer);
        // Get the number of correct and incorrect answers from the intent
        final int getCorrectAnswer = getIntent().getIntExtra("correct",0);
        final int getIncorrectAnswer = getIntent().getIntExtra("incorrect",0);

        correctAnswer.setText(String.valueOf(getCorrectAnswer));
        incorrectAnswer.setText(String.valueOf(getIncorrectAnswer));

        // Set a click listener on the start new quiz button
        startNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuizResults.this,MainActivity.class));
                finish();
            }
        });
    }

    // Override the onBackPressed method to go back to the main activity
    @Override
    public void onBackPressed() {
        startActivity(new Intent(QuizResults.this,MainActivity.class));
        finish();
    }
    // Release the media player when the activity is destroyed
    protected void onDestroy() {
        super.onDestroy();
        mp.release();
    }
}