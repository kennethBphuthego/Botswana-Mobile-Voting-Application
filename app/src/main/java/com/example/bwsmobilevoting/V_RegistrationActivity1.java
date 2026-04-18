package com.example.bwsmobilevoting;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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


public class    V_RegistrationActivity1 extends AppCompatActivity {

    Button Voter_registration_btn2;
    BiometricPrompt biometricPrompt1;
    BiometricPrompt.PromptInfo promptInfo1;
    EditText VoterName;
    EditText VoterSurname;
    EditText VoterId;
    EditText VoterLocation;
    EditText Registration_Date;
    DBHelper db;




    @SuppressLint({"SwitchIntDef", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter_registration1);

        VoterName = findViewById(R.id.enter_Names);
        VoterSurname = findViewById(R.id.enter_Surname);
        VoterId = findViewById(R.id.enter_Id_Number);
        VoterLocation = findViewById(R.id.enter_V_Location);
        Registration_Date = findViewById(R.id.enter_Reg_Date);
        db = new DBHelper(this);

        Voter_registration_btn2 = findViewById(R.id.Voter_registration_btn2);
        Voter_registration_btn2.setOnClickListener(view -> {



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

            biometricPrompt1 = new BiometricPrompt(V_RegistrationActivity1.this, executor, new BiometricPrompt.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                }

                @Override
                public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    Toast.makeText(getApplicationContext(), "Authentication Successful", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Processing Registration", Toast.LENGTH_SHORT).show();

                    String NamesTxt=VoterName.getText().toString();
                    String SurnameTxt=VoterSurname.getText().toString();
                    String ID_NumberTxt=VoterId.getText().toString();
                    String LocationTxt=VoterLocation.getText().toString();
                    String RegistrationTxt= Registration_Date.getText().toString();

                    SQLiteDatabase db1= openOrCreateDatabase("Immigration_Database.db", Context.MODE_PRIVATE,null);
                    @SuppressLint("Recycle") Cursor cursor7 = db1.rawQuery("Select * from Census where National_ID_Number = ?", new String[]{ID_NumberTxt} );
                    if (cursor7.getCount() > 0) {
                        Toast.makeText(getApplicationContext(), "Match Found", Toast.LENGTH_SHORT).show();

                        Boolean checkInsertedData = db.insertVoterData(NamesTxt,SurnameTxt,ID_NumberTxt,LocationTxt,RegistrationTxt);
                        if (checkInsertedData){
                            Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                            Intent Option5 = new Intent(V_RegistrationActivity1.this, Home_PageActivity1.class);
                            startActivity(Option5);
                        } else {
                            Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(), "No Match Found", Toast.LENGTH_SHORT).show();
                    }

                    }


                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    Toast.makeText(getApplicationContext(), "Authentication Failed", Toast.LENGTH_SHORT).show();

                }
            });

            promptInfo1 = new BiometricPrompt.PromptInfo.Builder().setTitle("Bw's Mobile Voting")
                    .setDescription("Use Fingerprint to proceed").setDeviceCredentialAllowed(true).build();

            biometricPrompt1.authenticate(promptInfo1);

        });

        }


}













