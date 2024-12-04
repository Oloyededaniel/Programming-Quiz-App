package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import com.example.quizapp.BatteryLevelReceiver;


import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class QuizActivity extends AppCompatActivity {
    ImageView backBtn;
    TextView timer,selectedTopicName,question,questions;
    AppCompatButton Option1,Option2,Option3,Option4,nextBtn;
    private Timer quizTimer;
    private int totalTimeinMinutes = 1;
    private int seconds = 0;
    private List<QuestionList> questionLists;
    private int currentQuestionPosition = 0;

    private String selectedOptionByUser = " ";
    private BroadcastReceiver BatteryLevelReceiver;

    // BroadcastReceiver class to listen for battery level changes
    private class BatteryBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int batteryLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            // Handle battery level change
        }
    }
    private BatteryBroadcastReceiver batteryReceiver;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        backBtn= findViewById(R.id.backBtn);
        timer = findViewById(R.id.timer);
        selectedTopicName = findViewById(R.id.topicName);
        question = findViewById(R.id.question);
        questions = findViewById(R.id.questions);
        Option1 = findViewById(R.id.option1);
        Option2 = findViewById(R.id.option2);
        Option3 = findViewById(R.id.option3);
        Option4 = findViewById(R.id.option4);
        nextBtn = findViewById(R.id.nextBtn);

        final String getSelectedTopicName = getIntent().getStringExtra("selectedTopic");

        selectedTopicName.setText(getSelectedTopicName);
        // Get the list of questions for the selected topic, start the quiz timer, and display the first question
        questionLists = QuestionBank.getQuestions(getSelectedTopicName);
        startTimer(timer);

        questions.setText((currentQuestionPosition +1)+ "/" + questionLists.size());
        question.setText(questionLists.get(0).getQuestion());
        Option1.setText(questionLists.get(0).getOption1());
        Option2.setText(questionLists.get(0).getOption2());
        Option3.setText(questionLists.get(0).getOption3());
        Option4.setText(questionLists.get(0).getOption4());
        // Set up onClickListeners for each option to check the user's answer and update accordingly
        Option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedOptionByUser = Option1.getText().toString();
                Option1.setBackgroundResource(R.drawable.round_back_red10);
                Option1.setTextColor(Color.WHITE);
                revealAnswer();
                questionLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
            }
        });

        Option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedOptionByUser = Option2.getText().toString();
                Option2.setBackgroundResource(R.drawable.round_back_red10);
                Option2.setTextColor(Color.WHITE);
                revealAnswer();
                questionLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
            }
        });

        Option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedOptionByUser = Option3.getText().toString();
                Option3.setBackgroundResource(R.drawable.round_back_red10);
                Option3.setTextColor(Color.WHITE);
                revealAnswer();
                questionLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
            }
        });

        Option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedOptionByUser = Option4.getText().toString();
                Option4.setBackgroundResource(R.drawable.round_back_red10);
                Option4.setTextColor(Color.WHITE);
                revealAnswer();
                questionLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
            }
        });

        // Register broadcast receiver to get battery level changes
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(BatteryLevelReceiver, filter);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty()){
                    Toast.makeText(QuizActivity.this, "Please select an Option!!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    changeNextQuestion();
                }
            }
        });




        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                quizTimer.purge();
                quizTimer.cancel();

                // Unregister the battery level receiver
                unregisterReceiver(BatteryLevelReceiver);

                startActivity(new Intent(QuizActivity.this,MainActivity.class));
                finish();
            }
        });


    }
    private void changeNextQuestion(){

        currentQuestionPosition++;
        if((currentQuestionPosition +1)==questionLists.size()) {
            nextBtn.setText("Submit Quiz");
        }
        if (currentQuestionPosition < questionLists.size()) {
                selectedOptionByUser = " ";
                Option1.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
                Option1.setTextColor(Color.parseColor("#1F6BB8"));

                Option2.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
                Option2.setTextColor(Color.parseColor("#1F6BB8"));

                Option3.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
                Option3.setTextColor(Color.parseColor("#1F6BB8"));

                Option4.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
                Option4.setTextColor(Color.parseColor("#1F6BB8"));

                questions.setText((currentQuestionPosition + 1) + "/" + questionLists.size());
                question.setText(questionLists.get(currentQuestionPosition).getQuestion());
                Option1.setText(questionLists.get(currentQuestionPosition).getOption1());
                Option2.setText(questionLists.get(currentQuestionPosition).getOption2());
                Option3.setText(questionLists.get(currentQuestionPosition).getOption3());
                Option4.setText(questionLists.get(currentQuestionPosition).getOption4());
        } else{
            Intent intent = new Intent(QuizActivity.this, QuizResults.class);
            intent.putExtra("correct", getCorrectAnswers());
            intent.putExtra("incorrect", getInCorrectAnswers());
            startActivity(intent);
            finish();
        }
    }
    private void startTimer(TextView timerTTV){
        quizTimer = new Timer();

        quizTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(seconds == 0 && totalTimeinMinutes > 0){
                    totalTimeinMinutes--;
                    seconds = 59;
                }
                else if(seconds == 0 && totalTimeinMinutes == 0){
                    // End the quiz
                    quizTimer.purge();
                    quizTimer.cancel();
                    Toast.makeText(QuizActivity.this, "Time Over!!!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(QuizActivity.this, QuizResults.class);
                    intent.putExtra("correct", getCorrectAnswers());
                    intent.putExtra("incorrect", getInCorrectAnswers());
                    startActivity(intent);
                    finish();
                }
                else{
                    seconds--;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String finalMinutes = String.valueOf(totalTimeinMinutes);
                        String finalseconds = String.valueOf(seconds);

                        if(finalMinutes.length() == 1){
                            finalMinutes = "0"+finalMinutes;
                        }
                        if (finalseconds.length() == 1){
                            finalseconds = "0"+finalseconds;
                        }
                        timerTTV.setText(finalMinutes+":"+finalseconds);

                    }
                });
            }
        },0,1000);
    }
    private int getCorrectAnswers() {
        int correctCount = 0;

        for (QuestionList question : questionLists) {
            if (question.getUserSelectedAnswer() != null && question.getUserSelectedAnswer().equals(question.getAnswer())) {
                correctCount++;
            }
        }

        return correctCount;
    }

    private int getInCorrectAnswers() {
        int incorrectCount = 0;

        for (QuestionList question : questionLists) {
            if (question.getUserSelectedAnswer() != null && !question.getUserSelectedAnswer().equals(question.getAnswer())) {
                incorrectCount++;
            }
        }

        return incorrectCount;
    }

    @Override
    public void onBackPressed() {

        quizTimer.purge();
        quizTimer.cancel();

        startActivity(new Intent(QuizActivity.this,MainActivity.class));
        finish();
    }
    public void revealAnswer(){
        // Show the correct answer
        final String getAnswer = questionLists.get(currentQuestionPosition).getAnswer();
        if(Option1.getText().toString().equals(getAnswer)){
            Option1.setBackgroundResource(R.drawable.round_back_green10);
            Option1.setTextColor(Color.WHITE);
        }
        else if (Option2.getText().toString().equals(getAnswer)) {
            Option2.setBackgroundResource(R.drawable.round_back_green10);
            Option2.setTextColor(Color.WHITE);
        }
        else if (Option3.getText().toString().equals(getAnswer)) {
            Option3.setBackgroundResource(R.drawable.round_back_green10);
            Option3.setTextColor(Color.WHITE);
        }
        else if (Option4.getText().toString().equals(getAnswer)) {
            Option4.setBackgroundResource(R.drawable.round_back_green10);
            Option4.setTextColor(Color.WHITE);
        }
    }
    protected void onDestroy() {
        super.onDestroy();
        // Cancel the quiz timer and unregister the battery receiver
        quizTimer.cancel();
        quizTimer.purge();
        unregisterReceiver(BatteryLevelReceiver);
    }
}
