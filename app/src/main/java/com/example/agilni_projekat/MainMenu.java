package com.example.agilni_projekat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {
    Button btnAdd, btnSub, btnMulti, btnDiv, btnLogout;
    TextView oIgri;
    Intent intent;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        btnAdd=findViewById(R.id.btn_answer0);
        btnSub=findViewById(R.id.btn_answer1);
        btnMulti=findViewById(R.id.btn_answer2);
        btnDiv=findViewById(R.id.btn_answer3);
        btnLogout=findViewById(R.id.button5);
        oIgri=findViewById(R.id.textView5);
        Bundle extras = getIntent().getExtras();
        if (extras!=null)
            id = extras.getString("id");
        intent=new Intent(getApplicationContext(),SelectLevel.class);
        oIgri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent about=new Intent(MainMenu.this, oIgri.class);
                startActivity(about);
            }
        });
        View.OnClickListener type = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button buttonClicked=(Button)view;
                String typeSelected=buttonClicked.getText().toString();
                switch (typeSelected) {
                    case "Add(+)":
                        intent.putExtra("type", "add");
                        break;
                    case "Substract(-)":
                        intent.putExtra("type","subtract");
                        break;
                    case "Multiply(*)":
                        intent.putExtra("type","multiply");
                        break;
                    case "Module(%)":
                        intent.putExtra("type","module");
                        break;
                }
                if (extras!=null)
                    intent.putExtra("id",id);
                startActivity(intent);
                finish();
            }
        };
        btnAdd.setOnClickListener(type);
        btnSub.setOnClickListener(type);
        btnMulti.setOnClickListener(type);
        btnDiv.setOnClickListener(type);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}