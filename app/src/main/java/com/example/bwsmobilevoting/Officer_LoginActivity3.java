package com.example.bwsmobilevoting;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import java.util.concurrent.Executor;

public class Officer_LoginActivity3 extends AppCompatActivity {

    EditText OfficerName;
    EditText OfficerId;
    Button Officer_Login_btn2;
    BiometricPrompt biometricPrompt4;
    BiometricPrompt.PromptInfo promptInfo4;
    DBHelper2 db2;



    @SuppressLint("SwitchIntDef")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officer_login2);

        OfficerName = findViewById(R.id.enter_Officer_Names2);
        OfficerId = findViewById(R.id.enter_Officer_Id2);

        Officer_Login_btn2=findViewById(R.id.Officer_Login_btn2);
        Officer_Login_btn2.setOnClickListener(view -> {

            BiometricManager biometricManager=BiometricManager.from(this);
            switch (biometricManager.canAuthenticate())
            {
                case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                    Toast.makeText(getApplicationContext(),"Device does not have Fingerprint", Toast.LENGTH_SHORT).show();
                    break;

                case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                    Toast.makeText(getApplicationContext(),"Fingerprint not available", Toast.LENGTH_SHORT).show();
                    break;

                case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                    Toast.makeText(getApplicationContext(),"No Fingerprint assigned", Toast.LENGTH_SHORT).show();
                    break;
            }

            Executor executor= ContextCompat.getMainExecutor(this);

            biometricPrompt4=new BiometricPrompt(Officer_LoginActivity3.this, executor, new BiometricPrompt.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                }

                @Override
                public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    Toast.makeText(getApplicationContext(),"Authentication Successful", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(),"Logging In", Toast.LENGTH_SHORT).show();

                    String NamesTxt3=OfficerName.getText().toString();
                    String ID_NumberTxt3=OfficerId.getText().toString();

                    Boolean CheckLogInData = db2. checkOfficerLog(NamesTxt3,ID_NumberTxt3);
                    if(CheckLogInData){
                        Toast.makeText(getApplicationContext(), "Log in Successful", Toast.LENGTH_SHORT).show();
                        Intent Option6 =new Intent(Officer_LoginActivity3.this,Voter_ListActivity1.class);
                        startActivity(Option6);
                    }else{
                        Toast.makeText(getApplicationContext(), "Log in Failed", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    Toast.makeText(getApplicationContext(),"Authentication Failed", Toast.LENGTH_SHORT).show();

                }
            });

            promptInfo4=new BiometricPrompt.PromptInfo.Builder().setTitle("Bw's Mobile Voting")
                    .setDescription("Use Fingerprint to proceed").setDeviceCredentialAllowed(true).build();

            biometricPrompt4.authenticate(promptInfo4);
        });


    }

}
