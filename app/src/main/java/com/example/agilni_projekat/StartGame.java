package com.example.agilni_projekat;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class StartGame extends AppCompatActivity {
    Button btn_start, btn_answer0, btn_answer1, btn_answer2, btn_answer3, btn_answer4, btn_answer5, btn_answer6, btn_answer7;
    TextView tv_score,tv_questions, tv_timer,tv_bottommessage;
    ProgressBar prog_timer;
    long startTime;
    ArrayList<Long> middleTime;
    //Bundle extras = getIntent().getExtras();
    //if (extras != null) {
        //String value = extras.getString("key");
        //The key argument here must match that used in the other activity
    //}
    int secondsRemaining, totalSeconds;
    CountDownTimer timer;
    int seconds;
    Game g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        String type = extras.getString("type");
        String difficulty = extras.getString("difficulty");
        if (difficulty.equals("Easy"))
            setContentView(R.layout.activity_easy);
        else if (difficulty.equals("Medium"))
            setContentView(R.layout.activity_medium);
        else
            setContentView(R.layout.activity_hard);
        g = new Game(type,difficulty);
        btn_start=findViewById(R.id.btn_start);
        btn_answer0=findViewById(R.id.btn_answer01);
        btn_answer1=findViewById(R.id.btn_answer1);
        btn_answer2=findViewById(R.id.btn_answer2);
        btn_answer3=findViewById(R.id.btn_answer3);
        if (difficulty.equals("Medium")){
            btn_answer4=findViewById(R.id.btn_answer4);
            btn_answer5=findViewById(R.id.btn_answer5);
        }
        if (difficulty.equals("Hard")){
            btn_answer4=findViewById(R.id.btn_answer4);
            btn_answer5=findViewById(R.id.btn_answer5);
            btn_answer6=findViewById(R.id.btn_answer6);
            btn_answer7=findViewById(R.id.btn_answer7);
        }

        tv_score=findViewById(R.id.tv_score);
        tv_bottommessage=findViewById(R.id.tv_bottommessage);
        tv_questions=findViewById(R.id.tv_questions);
        tv_timer = findViewById(R.id.tv_timer);

        prog_timer=findViewById(R.id.prog_timer);
        int seconds;
        if (difficulty.equals("Easy"))
            seconds=40;
        else if (difficulty.equals("Medium"))
            seconds=30;
        else
            seconds=20;
        prog_timer.setMax(seconds);
        tv_timer.setText(seconds+" sekundi");
        tv_questions.setText("");
        tv_bottommessage.setText("tačni/pogrešni");
        tv_score.setText("sekundi");
        prog_timer.setProgress(0);
        middleTime=new ArrayList<>();
        View.OnClickListener startButtonClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button start_button=(Button)view;
                start_button.setVisibility(View.INVISIBLE);
                totalSeconds=secondsRemaining=seconds;
                tv_score.setText("");
                g = new Game(type,difficulty);
                startTime=System.currentTimeMillis();
                nextTurn(g,type,difficulty);
                timer.start();
            }
        };
        View.OnClickListener answerButtonClickListener= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button buttonClicked=(Button)view;

                int answerSlected=Integer.parseInt(buttonClicked.getText().toString());
                g.checkAnswer(answerSlected);
                middleTime.add(System.currentTimeMillis());
                if (middleTime.size()!=1)
                    tv_score.setText(Float.toString((float)(middleTime.get(middleTime.size()-1)-middleTime.get(middleTime.size()-2))/1000));
                else
                    tv_score.setText(Float.toString((float)(middleTime.get(middleTime.size()-1)-startTime)/1000));
                nextTurn(g,type,difficulty);
            }
        };

        btn_start.setOnClickListener(startButtonClickListener);
        btn_answer0.setOnClickListener(answerButtonClickListener);
        btn_answer1.setOnClickListener(answerButtonClickListener);
        btn_answer2.setOnClickListener(answerButtonClickListener);
        btn_answer3.setOnClickListener(answerButtonClickListener);
        if (difficulty.equals("Medium")) {
            btn_answer4.setOnClickListener(answerButtonClickListener);
            btn_answer5.setOnClickListener(answerButtonClickListener);
        }
        if (difficulty.equals("Hard")){
            btn_answer4.setOnClickListener(answerButtonClickListener);
            btn_answer5.setOnClickListener(answerButtonClickListener);
            btn_answer6.setOnClickListener(answerButtonClickListener);
            btn_answer7.setOnClickListener(answerButtonClickListener);
        }
        timer=new CountDownTimer(seconds*1000,1000) {
            @Override
            public void onTick(long l) {
                secondsRemaining--;
                tv_timer.setText(secondsRemaining+" sek");
                prog_timer.setProgress(totalSeconds-secondsRemaining);
            }

            @Override
            public void onFinish() {
                btn_answer0.setEnabled(false);
                btn_answer1.setEnabled(false);
                btn_answer2.setEnabled(false);
                btn_answer3.setEnabled(false);
                tv_bottommessage.setText("Vreme je isteklo! "+g.getNumberCorrect()+"/"+(g.getTotalQuestions()-1));

                final Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent=new Intent(getApplicationContext(),MainMenu.class);
                        startActivity(intent);
                        finish();
                    }
                },2000);
            }
        };
    }

    private void nextTurn(Game g,String type, String difficulty){
        g.makeNewQuestion(type,difficulty);
        int[] answer= g.getCurrentQuestion().getAnswerArray();
        btn_answer0.setText(Integer.toString(answer[0]));
        btn_answer1.setText(Integer.toString(answer[1]));
        btn_answer2.setText(Integer.toString(answer[2]));
        btn_answer3.setText(Integer.toString(answer[3]));
        if (difficulty.equals("Medium")){
            btn_answer4.setText(Integer.toString(answer[4]));
            btn_answer5.setText(Integer.toString(answer[5]));
        }
        if (difficulty.equals("Hard")){
            btn_answer4.setText(Integer.toString(answer[4]));
            btn_answer5.setText(Integer.toString(answer[5]));
            btn_answer6.setText(Integer.toString(answer[6]));
            btn_answer7.setText(Integer.toString(answer[7]));
        }

        btn_answer0.setEnabled(true);
        btn_answer1.setEnabled(true);
        btn_answer2.setEnabled(true);
        btn_answer3.setEnabled(true);
        if (difficulty.equals("Medium")){
            btn_answer4.setEnabled(true);
            btn_answer5.setEnabled(true);
        }
        if (difficulty.equals("Hard")){
            btn_answer4.setEnabled(true);
            btn_answer5.setEnabled(true);
            btn_answer6.setEnabled(true);
            btn_answer7.setEnabled(true);
        }

        tv_questions.setText(g.getCurrentQuestion().getQuestionPhrase());

        tv_bottommessage.setText(g.getNumberCorrect() + "/"+(g.getTotalQuestions()-1));
    }

}