package com.example.agilni_projekat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Login extends AppCompatActivity {
    TextInputEditText textInputEditTextUsername, textInputEditTextPassword;
    Button buttonLogin;
    TextView textViewSignUp,textViewGuest;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputEditTextUsername=findViewById(R.id.textInputLayout2);
        textInputEditTextPassword=findViewById(R.id.textInputLayout4);
        buttonLogin=findViewById(R.id.btnLogin);
        textViewSignUp=findViewById(R.id.signUpText);
        textViewGuest=findViewById(R.id.guest);
        progressBar=findViewById(R.id.progress);
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
                finish();
            }
        });
        textViewGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainMenu.class);
                startActivity(intent);
                finish();
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username, password, email;
                username=String.valueOf(textInputEditTextUsername.getText());
                password=String.valueOf(textInputEditTextPassword.getText());

                if (!username.equals("") && !password.equals("")){
                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[2];
                            field[0]="username";
                            field[1]="password";
                            String[] data = new String[2];
                            data[0]=username;
                            data[1]=password;
                            PutData putData = new PutData("http://192.168.26.138/agilni_projekat/login.php","POST",field,data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    //progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if (result.equals("Login Success")){
                                        //String id=data[0];
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(getApplicationContext(),MainMenu.class);
                                        startActivity(intent);
                                        finish();
                                    }else {
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });}else{
                    Toast.makeText(getApplicationContext(),"Fill all fields",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}