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

public class V_LoginActivity2 extends AppCompatActivity {

    Button Voter_login_btn2;
    EditText VoterNames2;
    EditText VoterId_Number2;
    BiometricPrompt biometricPrompt2;
    BiometricPrompt.PromptInfo promptInfo2;
    DBHelper db;

    @SuppressLint("SwitchIntDef")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter_login2);

        VoterNames2 = findViewById(R.id.enter_Names2);
        VoterId_Number2 = findViewById(R.id.enter_Id_Number2);
        db = new DBHelper(this);

        Voter_login_btn2 = findViewById(R.id.Voter_login_btn2);
        Voter_login_btn2.setOnClickListener(view -> {

            BiometricManager biometricManager = BiometricManager.from(this);
            switch (biometricManager.canAuthenticate()) {
                case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                    Toast.makeText(getApplicationContext(), "Device does not have Fingerprint", Toast.LENGTH_SHORT).show();
                    break;

                case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                    Toast.makeText(getApplicationContext(), "Fingerprint not available", Toast.LENGTH_SHORT).show();
                    break;

                case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                    Toast.makeText(getApplicationContext(), "No Fingerprint assigned", Toast.LENGTH_SHORT).show();
                    break;
            }

            Executor executor = ContextCompat.getMainExecutor(this);

            biometricPrompt2 = new BiometricPrompt(V_LoginActivity2.this, executor, new BiometricPrompt.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                }

                @Override
                public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    Toast.makeText(getApplicationContext(), "Authentication Successful", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Logging In", Toast.LENGTH_SHORT).show();

                    String NamesTxt2=VoterNames2.getText().toString();
                    String ID_NumberTxt2=VoterId_Number2.getText().toString();

                    Boolean CheckLogInData = db. checkVoterLog(NamesTxt2,ID_NumberTxt2);
                    if(CheckLogInData){
                        Toast.makeText(getApplicationContext(), "Log in Successful", Toast.LENGTH_SHORT).show();
                        Intent Option6 =new Intent(V_LoginActivity2.this,Home_PageActivity1.class);
                        startActivity(Option6);
                    }else{
                        Toast.makeText(getApplicationContext(), "Log in Failed", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    Toast.makeText(getApplicationContext(), "Authentication Failed", Toast.LENGTH_SHORT).show();

                }
            });

            promptInfo2 = new BiometricPrompt.PromptInfo.Builder().setTitle("Bw's Mobile Voting")
                    .setDescription("Use Fingerprint to proceed").setDeviceCredentialAllowed(true).build();

            biometricPrompt2.authenticate(promptInfo2);


        });

    }
}
