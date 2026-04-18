 package com.example.bwsmobilevoting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;


 public class MainActivity extends AppCompatActivity {

    Button Voter_Registration;
    Button Voter_Login;
    Button officer_registration_btn;
    Button officer_login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Voter_Registration=findViewById(R.id.Voter_Registration);
        Voter_Registration.setOnClickListener(view -> {
            Intent Option1 =new Intent(MainActivity.this, V_RegistrationActivity1.class);
            startActivity(Option1);
        });

        Voter_Login=findViewById(R.id.Voter_Login);
        Voter_Login.setOnClickListener(view -> {
            Intent Option2 = new Intent(MainActivity.this,V_LoginActivity2.class);
            startActivity(Option2);
        });

        officer_registration_btn=findViewById(R.id.officer_registration_btn);
        officer_registration_btn.setOnClickListener(view -> {
            Intent Option3 =new Intent(MainActivity.this, Officer_RegistrationActivity3.class);
            startActivity(Option3);
        });

        officer_login_btn=findViewById(R.id.officer_login_btn);
        officer_login_btn.setOnClickListener(view -> {
            Intent Option4 =new Intent(MainActivity.this,Officer_LoginActivity3.class);
            startActivity(Option4);
        });
    }
 }