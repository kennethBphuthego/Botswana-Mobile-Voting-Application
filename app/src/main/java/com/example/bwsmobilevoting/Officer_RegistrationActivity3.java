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

public class Officer_RegistrationActivity3 extends AppCompatActivity {

    Button Officer_Registration_btn2;
    BiometricPrompt biometricPrompt3;
    BiometricPrompt.PromptInfo promptInfo3;
    EditText OfficerName;
    EditText OfficerSurname;
    EditText OfficerId;
    EditText OfficerLocation;
    EditText Of_Registration_Date;
    DBHelper2 db2;

    @SuppressLint({"SwitchIntDef", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officer_registration1);

        OfficerName = findViewById(R.id.enter_Officer_Names);
        OfficerSurname = findViewById(R.id.enter_Officer_Surname);
        OfficerId = findViewById(R.id.enter_Officer_Id);
        OfficerLocation = findViewById(R.id.enter_Officer_Location);
        Of_Registration_Date = findViewById(R.id.enter_Officer_Reg_Date);
        db2 = new DBHelper2(this);

        Officer_Registration_btn2 = findViewById(R.id.Officer_Registration_btn2);
        Officer_Registration_btn2.setOnClickListener(view -> {

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

            biometricPrompt3 = new BiometricPrompt(Officer_RegistrationActivity3.this, executor, new BiometricPrompt.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                }

                @Override
                public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    Toast.makeText(getApplicationContext(), "Authentication Successful", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Processing Registration", Toast.LENGTH_SHORT).show();

                    String NamesTxt=OfficerName.getText().toString();
                    String SurnameTxt=OfficerSurname.getText().toString();
                    String ID_NumberTxt=OfficerId.getText().toString();
                    String LocationTxt=OfficerLocation.getText().toString();
                    String RegistrationTxt= Of_Registration_Date.getText().toString();

                    Boolean checkInsertedData2 = db2.insertRegistrarData(NamesTxt,SurnameTxt,ID_NumberTxt,LocationTxt,RegistrationTxt);
                    if(checkInsertedData2){
                        Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent Option7 =new Intent(Officer_RegistrationActivity3.this,Voter_ListActivity1.class);
                        startActivity(Option7);
                    }else{
                        Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    Toast.makeText(getApplicationContext(), "Authentication Failed", Toast.LENGTH_SHORT).show();

                }

            });

            promptInfo3 = new BiometricPrompt.PromptInfo.Builder().setTitle("Bw's Mobile Voting")
                    .setDescription("Use Fingerprint to proceed").setDeviceCredentialAllowed(true).build();

            biometricPrompt3.authenticate(promptInfo3);


        });


    }
}
