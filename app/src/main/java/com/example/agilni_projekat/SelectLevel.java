package com.example.agilni_projekat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectLevel extends AppCompatActivity {
    Button btnEasy, btnMedium, btnHard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);
        btnEasy=findViewById(R.id.btn_answer0);
        btnMedium=findViewById(R.id.btn_answer1);
        btnHard=findViewById(R.id.btn_answer2);
        Bundle extras = getIntent().getExtras();
        //String mode="";
        //if (extras != null) {
            String mode = extras.getString("mode");
        //}
        btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (mode){
                    case "add":
                        Intent i = new Intent(getApplicationContext(),AddEasy.class);
                        i.putExtra("mode",mode);
                        i.putExtra("diff","easy");
                        startActivity(i);
                        finish();
                        break;
                    case "sub":
                        Intent is = new Intent(getApplicationContext(),SubstractEasy.class);
                        is.putExtra("mode",mode);
                        is.putExtra("diff","easy");
                        startActivity(is);
                        finish();
                        break;
                    case "multi":
                        Intent im = new Intent(getApplicationContext(),MultiplyEasy.class);
                        im.putExtra("mode",mode);
                        im.putExtra("diff","easy");
                        startActivity(im);
                        finish();
                        break;
                    case "div":
                        Intent id = new Intent(getApplicationContext(),DivideEasy.class);
                        id.putExtra("mode",mode);
                        id.putExtra("diff","easy");
                        startActivity(id);
                        finish();
                        break;
                }
            }
        });
        btnMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (mode){
                    case "add":
                        Intent i = new Intent(getApplicationContext(),AddMedium.class);
                        i.putExtra("mode",mode);
                        i.putExtra("diff","medium");
                        startActivity(i);
                        finish();
                        break;
                    case "sub":
                        Intent is = new Intent(getApplicationContext(),SubstractMedium.class);
                        is.putExtra("mode",mode);
                        is.putExtra("diff","medium");
                        startActivity(is);
                        finish();
                        break;
                    case "multi":
                        Intent im = new Intent(getApplicationContext(),MultiplyMedium.class);
                        im.putExtra("mode",mode);
                        im.putExtra("diff","medium");
                        startActivity(im);
                        finish();
                        break;
                    case "div":
                        Intent id = new Intent(getApplicationContext(),DivideMedium.class);
                        id.putExtra("mode",mode);
                        id.putExtra("diff","medium");
                        startActivity(id);
                        finish();
                        break;
                }
            }
        });
        btnHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (mode){
                    case "add":
                        Intent i = new Intent(getApplicationContext(), AddHard.class);
                        i.putExtra("mode",mode);
                        i.putExtra("diff","hard");
                        startActivity(i);
                        finish();
                        break;
                    case "sub":
                        Intent is = new Intent(getApplicationContext(), SubstractHard.class);
                        is.putExtra("mode",mode);
                        is.putExtra("diff","hard");
                        startActivity(is);
                        finish();
                        break;
                    case "multi":
                        Intent im = new Intent(getApplicationContext(), MultiplyHard.class);
                        im.putExtra("mode",mode);
                        im.putExtra("diff","hard");
                        startActivity(im);
                        finish();
                        break;
                    case "div":
                        Intent id = new Intent(getApplicationContext(), DivideHard.class);
                        id.putExtra("mode",mode);
                        id.putExtra("diff","hard");
                        startActivity(id);
                        finish();
                        break;
                }
            }
        });
    }
}