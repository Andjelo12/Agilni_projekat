package com.example.agilni_projekat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SelectLevel extends AppCompatActivity {
    Button btnEasy, btnMedium, btnHard;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);
        i = new Intent(SelectLevel.this, StartGame.class);
        btnEasy = findViewById(R.id.btn_answer0);
        btnMedium = findViewById(R.id.btn_answer1);
        btnHard = findViewById(R.id.btn_answer2);
        Bundle extras = getIntent().getExtras();
        String type = extras.getString("type");
        String id = extras.getString("id");
        View.OnClickListener difficulty = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button buttonClicked=(Button)view;
                String difficultySelected=buttonClicked.getText().toString();
                i.putExtra("type", type);
                i.putExtra("difficulty", difficultySelected);
                i.putExtra("id",id);
                startActivity(i);
                finish();
            }
        };
        btnEasy.setOnClickListener(difficulty);
        btnMedium.setOnClickListener(difficulty);
        btnHard.setOnClickListener(difficulty);
        /*btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectLevel.this, MainMenu.class);
                intent.putExtra("type", type);
                intent.putExtra("id",id);
                startActivity(intent);
                finish();
            }
        });*/
    }
}