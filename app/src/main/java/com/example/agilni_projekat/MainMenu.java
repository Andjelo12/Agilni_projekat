package com.example.agilni_projekat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {
    Button btnAdd, btnSub, btnMulti, btnDiv, btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        btnAdd=findViewById(R.id.btn_answer0);
        btnSub=findViewById(R.id.btn_answer1);
        btnMulti=findViewById(R.id.btn_answer2);
        btnDiv=findViewById(R.id.btn_answer3);
        btnLogout=findViewById(R.id.button5);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SelectLevel.class);
                intent.putExtra("mode","add");
                startActivity(intent);
                finish();
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SelectLevel.class);
                intent.putExtra("mode","sub");
                startActivity(intent);
                finish();
            }
        });
        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SelectLevel.class);
                intent.putExtra("mode","multi");
                startActivity(intent);
                finish();
            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SelectLevel.class);
                intent.putExtra("mode","div");
                startActivity(intent);
                finish();
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}