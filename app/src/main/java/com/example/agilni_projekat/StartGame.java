package com.example.agilni_projekat;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class StartGame extends AppCompatActivity {
    Button btn_start, btn_answer0, btn_answer1, btn_answer2, btn_answer3, btn_answer4, btn_answer5, btn_answer6, btn_answer7;
    TextView tv_score, tv_questions, tv_timer, tv_bottommessage;
    ProgressBar prog_timer;
    ImageView firstNumImg, secondNumImg;
    long startTime, pauseTime = 0, tempTime;
    int seconds;
    ArrayList<Long> middleTime;
    int secondsRemaining, totalSeconds;
    float shortest_time, avg_time;
    boolean started;
    CountDownTimer timer;
    Game g;
    String id, type, difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        type = extras.getString("type");
        id = extras.getString("id");
        difficulty = extras.getString("difficulty");
        if (difficulty.equals("Easy"))
            setContentView(R.layout.activity_easy);
        else if (difficulty.equals("Medium"))
            setContentView(R.layout.activity_medium);
        else
            setContentView(R.layout.activity_hard);
        g = new Game(type, difficulty);
        btn_start = findViewById(R.id.btn_start);
        btn_answer0 = findViewById(R.id.btn_answer01);
        btn_answer0.setEnabled(false);
        btn_answer1 = findViewById(R.id.btn_answer1);
        btn_answer1.setEnabled(false);
        btn_answer2 = findViewById(R.id.btn_answer2);
        btn_answer2.setEnabled(false);
        btn_answer3 = findViewById(R.id.btn_answer3);
        btn_answer3.setEnabled(false);
        firstNumImg = findViewById(R.id.imageView);
        secondNumImg = findViewById(R.id.imageView2);
        if (difficulty.equals("Medium")) {
            btn_answer4 = findViewById(R.id.btn_answer4);
            btn_answer4.setEnabled(false);
            btn_answer5 = findViewById(R.id.btn_answer5);
            btn_answer5.setEnabled(false);
        }
        if (difficulty.equals("Hard")) {
            btn_answer4 = findViewById(R.id.btn_answer4);
            btn_answer4.setEnabled(false);
            btn_answer5 = findViewById(R.id.btn_answer5);
            btn_answer5.setEnabled(false);
            btn_answer6 = findViewById(R.id.btn_answer6);
            btn_answer6.setEnabled(false);
            btn_answer7 = findViewById(R.id.btn_answer7);
            btn_answer7.setEnabled(false);
        }
        tv_score = findViewById(R.id.tv_score);
        tv_bottommessage = findViewById(R.id.tv_bottommessage);
        tv_questions = findViewById(R.id.tv_questions);
        tv_timer = findViewById(R.id.tv_timer);

        prog_timer = findViewById(R.id.prog_timer);
        if (difficulty.equals("Easy"))
            seconds = 40;
        else if (difficulty.equals("Medium"))
            seconds = 30;
        else
            seconds = 20;
        prog_timer.setMax(seconds);
        tv_timer.setText(seconds + " sekundi");
        tv_questions.setText("");
        tv_bottommessage.setText("tačni/pogrešni poeni");
        tv_score.setText("sekundi");
        prog_timer.setProgress(0);
        middleTime = new ArrayList<>();
        View.OnClickListener startButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button start_button = (Button) view;
                start_button.setVisibility(View.INVISIBLE);
                btn_answer0.setEnabled(true);
                btn_answer1.setEnabled(true);
                btn_answer2.setEnabled(true);
                btn_answer3.setEnabled(true);
                if (difficulty.equals("Medium")){
                    btn_answer4.setEnabled(true);
                    btn_answer5.setEnabled(true);
                }
                if (difficulty.equals("Hard")) {
                    btn_answer4.setEnabled(true);
                    btn_answer5.setEnabled(true);
                    btn_answer6.setEnabled(true);
                    btn_answer7.setEnabled(true);
                }
                totalSeconds = secondsRemaining = seconds;
                g = new Game(type, difficulty);
                startTime = System.currentTimeMillis();
                nextTurn(g, type, difficulty);
                startTimer();
            }
        };
        View.OnClickListener answerButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button buttonClicked = (Button) view;
                int answerSlected = Integer.parseInt(buttonClicked.getText().toString());
                g.checkAnswer(answerSlected);
                middleTime.add(System.currentTimeMillis()-pauseTime);
                if (middleTime.size() != 1)
                    tv_score.setText(Float.toString((float) (middleTime.get(middleTime.size() - 1) - middleTime.get(middleTime.size() - 2)) / 1000));
                else
                    tv_score.setText(Float.toString((float) (middleTime.get(middleTime.size() - 1) - startTime) / 1000));
                nextTurn(g, type, difficulty);
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
        if (difficulty.equals("Hard")) {
            btn_answer4.setOnClickListener(answerButtonClickListener);
            btn_answer5.setOnClickListener(answerButtonClickListener);
            btn_answer6.setOnClickListener(answerButtonClickListener);
            btn_answer7.setOnClickListener(answerButtonClickListener);
        }
    }
    public void startTimer(){
        timer = new CountDownTimer(seconds * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                started=true;
                seconds--;
                tv_timer.setText(seconds + " sek");
                prog_timer.setProgress(totalSeconds - seconds);
            }

            @SuppressLint("DefaultLocale")
            @Override
            public void onFinish() {
                if (g.getTotalQuestions() - 1 != 0) {
                    avg_time = (float) totalSeconds / (g.getTotalQuestions() - 1);
                    shortest_time = (float) (middleTime.get(0) - startTime);
                    for (int i = 1; i < middleTime.size(); i++)
                        if (middleTime.get(i) - middleTime.get(i - 1) < shortest_time) {
                            shortest_time = (float) (middleTime.get(i) - middleTime.get(i - 1));
                        }
                }
                btn_answer0.setEnabled(false);
                btn_answer1.setEnabled(false);
                btn_answer2.setEnabled(false);
                btn_answer3.setEnabled(false);
                tv_bottommessage.setText("Vreme je isteklo! " + "\nTačni | Netačni: " + g.getNumberCorrect() + " | " + (g.getTotalQuestions() - 1)+"\nPts: "+g.getPoints());
                String[] field = new String[8];
                field[0] = "id_user";
                field[1] = "correct_answers";
                field[2] = "incorrect_answers";
                field[3] = "avg_time";
                field[4] = "fastest_answer";
                field[5] = "game_type";
                field[6] = "game_level";
                field[7] = "points";
                String[] data = new String[8];
                if (id != null)
                    data[0] = id;
                else
                    data[0] = "anonymous";
                data[1] = Integer.toString(g.getNumberCorrect());
                data[2] = Integer.toString((g.getTotalQuestions() - 1) - g.getNumberCorrect());
                data[3] = Float.toString(avg_time);
                data[4] = Float.toString(shortest_time / 1000);
                data[5] = type;
                data[6] = difficulty;
                data[7] = Integer.toString(g.getPoints());
                PutData putData = new PutData("http://first.stud.vts.su.ac.rs/agilni_projekat/set_result.php", "POST", field, data);
                putData.startPut();
                /*if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        Toast.makeText(StartGame.this, result, Toast.LENGTH_SHORT).show();
                    }
                }*/
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                        intent.putExtra("id",id);
                        startActivity(intent);
                        finish();
                    }
                }, 4000);
            }
        }.start();
    }
    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        if (started) {
            tempTime = System.currentTimeMillis();
            timer.cancel();
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.layout_custom_dialog);
            Button btnClose = dialog.findViewById(R.id.btn_no);
            TextView txtResult = dialog.findViewById(R.id.txtDesc);
            txtResult.setText("Tačni/netačni: " + g.getNumberCorrect() + " | " + (g.getTotalQuestions()-1) + "\nPts: " + g.getPoints()+"\nPress back to continue");
            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
                    finish();
                }
            });

            dialog.show();
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    pauseTime += (System.currentTimeMillis()-tempTime);
                    startTimer();
                }
            });
            dialog.setCanceledOnTouchOutside(false);
        }
    }
    private void nextTurn(Game g, String type, String difficulty) {
        g.makeNewQuestion(type, difficulty);
        switch (g.getCurrentQuestion().getFirstNumber()){
            case 1:
                firstNumImg.setImageResource(R.drawable.jedan);
                break;
            case 2:
                firstNumImg.setImageResource(R.drawable.dva);
                break;
            case 3:
                firstNumImg.setImageResource(R.drawable.tri);
                break;
            case 4:
                firstNumImg.setImageResource(R.drawable.cetiri);
                break;
            case 5:
                firstNumImg.setImageResource(R.drawable.pet);
                break;
            case 6:
                firstNumImg.setImageResource(R.drawable.sest);
        }
        switch (g.getCurrentQuestion().getSecondNumber()){
            case 1:
                secondNumImg.setImageResource(R.drawable.jedan);
                break;
            case 2:
                secondNumImg.setImageResource(R.drawable.dva);
                break;
            case 3:
                secondNumImg.setImageResource(R.drawable.tri);
                break;
            case 4:
                secondNumImg.setImageResource(R.drawable.cetiri);
                break;
            case 5:
                secondNumImg.setImageResource(R.drawable.pet);
                break;
            case 6:
                secondNumImg.setImageResource(R.drawable.sest);
        }
        int[] answer = g.getCurrentQuestion().getAnswerArray();
        btn_answer0.setText(Integer.toString(answer[0]));
        btn_answer1.setText(Integer.toString(answer[1]));
        btn_answer2.setText(Integer.toString(answer[2]));
        btn_answer3.setText(Integer.toString(answer[3]));
        if (difficulty.equals("Medium")) {
            btn_answer4.setText(Integer.toString(answer[4]));
            btn_answer5.setText(Integer.toString(answer[5]));
        }
        if (difficulty.equals("Hard")) {
            btn_answer4.setText(Integer.toString(answer[4]));
            btn_answer5.setText(Integer.toString(answer[5]));
            btn_answer6.setText(Integer.toString(answer[6]));
            btn_answer7.setText(Integer.toString(answer[7]));
        }

        btn_answer0.setEnabled(true);
        btn_answer1.setEnabled(true);
        btn_answer2.setEnabled(true);
        btn_answer3.setEnabled(true);
        if (difficulty.equals("Medium")) {
            btn_answer4.setEnabled(true);
            btn_answer5.setEnabled(true);
        }
        if (difficulty.equals("Hard")) {
            btn_answer4.setEnabled(true);
            btn_answer5.setEnabled(true);
            btn_answer6.setEnabled(true);
            btn_answer7.setEnabled(true);
        }

        tv_questions.setText(g.getCurrentQuestion().getQuestionPhrase());

        tv_bottommessage.setText(g.getNumberCorrect() + "/" + (g.getTotalQuestions() - 1) + " "+g.getPoints());
    }

}