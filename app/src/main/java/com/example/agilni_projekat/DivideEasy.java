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

public class DivideEasy extends AppCompatActivity {
    Button btn_start, btn_answer0, btn_answer1, btn_answer2, btn_answer3;
    TextView tv_score,tv_questions, tv_timer,tv_bottommessage;
    ProgressBar prog_timer;

    int secondsRemaining=40;

    CountDownTimer timer=new CountDownTimer(40000,1000) {
        @Override
        public void onTick(long l) {
            secondsRemaining--;
            tv_timer.setText(Integer.toString(secondsRemaining)+"sek");
            prog_timer.setProgress(40-secondsRemaining);
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
            },500);
        }
    };

    GameDivHard g = new GameDivHard();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy);

        btn_start=findViewById(R.id.btn_start);
        btn_answer0=findViewById(R.id.btn_answer01);
        btn_answer1=findViewById(R.id.btn_answer1);
        btn_answer2=findViewById(R.id.btn_answer2);
        btn_answer3=findViewById(R.id.btn_answer3);

        tv_score=findViewById(R.id.tv_score);
        tv_bottommessage=findViewById(R.id.tv_bottommessage);
        tv_questions=findViewById(R.id.tv_questions);
        tv_timer = findViewById(R.id.tv_timer);

        prog_timer=findViewById(R.id.prog_timer);

        prog_timer.setMax(30);

        tv_timer.setText("0Sek");
        tv_questions.setText("");
        tv_bottommessage.setText("Pritisnite kreni");
        tv_score.setText("0poena");
        prog_timer.setProgress(0);

        View.OnClickListener startButtonClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button start_button=(Button)view;

                start_button.setVisibility(View.INVISIBLE);
                secondsRemaining=30;
                g = new GameDivHard();
                nextTurn();
                timer.start();
            }
        };

        View.OnClickListener answerButtonClickListener= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button buttonClicked=(Button)view;

                int answerSlected=Integer.parseInt(buttonClicked.getText().toString());
                g.checkAnswer(answerSlected);
                tv_score.setText(Integer.toString(g.getScore()));
                nextTurn();
            }
        };

        btn_start.setOnClickListener(startButtonClickListener);
        btn_answer0.setOnClickListener(answerButtonClickListener);
        btn_answer1.setOnClickListener(answerButtonClickListener);
        btn_answer2.setOnClickListener(answerButtonClickListener);
        btn_answer3.setOnClickListener(answerButtonClickListener);
    }
    private void nextTurn(){
        g.makeNewQuestion();
        int[] answer= g.getCurrentQuestion().getAnswerArray();
        btn_answer0.setText(Integer.toString(answer[0]));
        btn_answer1.setText(Integer.toString(answer[1]));
        btn_answer2.setText(Integer.toString(answer[2]));
        btn_answer3.setText(Integer.toString(answer[3]));

        btn_answer0.setEnabled(true);
        btn_answer1.setEnabled(true);
        btn_answer2.setEnabled(true);
        btn_answer3.setEnabled(true);

        tv_questions.setText(g.getCurrentQuestion().getQuestionPhrase());

        tv_bottommessage.setText(g.getNumberCorrect() + "/"+(g.getTotalQuestions()-1));
    }
}