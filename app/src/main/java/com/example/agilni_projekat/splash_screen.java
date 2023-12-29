package com.example.agilni_projekat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

public class splash_screen extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        progressBar = findViewById(R.id.progressBar);

        Thread t = new Thread() {
            public void run() {

                try {
                    //simulate long running operation
                    for (int progress = 0; progress < 100; progress += 10) {
                        Thread.sleep(100);// ovo je obavezno
                        progressBar.setProgress(progress);
                    }

                    //start new activity  obavezno mora
                    Intent i = new Intent(splash_screen.this, SignUp.class);
                    startActivity(i);

                    //destroying Splash activity
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }
}