package com.example.bwsmobilevoting;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class V_AccountActivity1 extends AppCompatActivity {

    ImageView Home,Profile,Listing,Count,Results,Exit;

    Button ViewDAta_btn;
    Button Voter_Update_btn2;
    Button DeleteDAta_btn;
    EditText Name_Account;
    EditText Surname_Account;
    EditText Id_Account;
    EditText Location_Account;
    EditText Registration_DateAccount;

    DBHelper db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v_account1);

        Home=findViewById(R.id.Home_symbol);
        Home.setOnClickListener(view -> {
            Intent Option8=new Intent(V_AccountActivity1.this,Home_PageActivity1.class);
            startActivity(Option8);
        });

        Profile=findViewById(R.id.PROFILE_symbol);
        Profile.setOnClickListener(view -> {
            Intent Option8=new Intent(V_AccountActivity1.this,V_AccountActivity1.class);
            startActivity(Option8);
        });

        Listing=findViewById(R.id.Listing_symbol);
        Listing.setOnClickListener(view -> {
            Intent Option8=new Intent(V_AccountActivity1.this,Candidate_ListingActivity1.class);
            startActivity(Option8);
        });

        Count=findViewById(R.id.Count_symbol);
        Count.setOnClickListener(view -> {
            Intent Option8=new Intent(V_AccountActivity1.this,Vote_CountingActivity1.class);
            startActivity(Option8);
        });

        Results=findViewById(R.id.Results_symbol);
        Results.setOnClickListener(view -> {
            Intent Option8=new Intent(V_AccountActivity1.this,Results_Activity1.class);
            startActivity(Option8);
        });

        Exit=findViewById(R.id.Exit_symbol);
        Exit.setOnClickListener(view -> {
            Intent Option8=new Intent(V_AccountActivity1.this,V_AccountActivity1.class);
            startActivity(Option8);
        });

        Name_Account = findViewById(R.id.V_NamesAccount);
        Surname_Account = findViewById(R.id.V_SurnameAccount);
        Id_Account = findViewById(R.id.V_Id_NumberAccount);
        Location_Account = findViewById(R.id.V_LocationAccount);
        Registration_DateAccount = findViewById(R.id.enter_Reg_Date);
        db = new DBHelper(this);

        ViewDAta_btn = findViewById(R.id.ViewDAta_btn);
        ViewDAta_btn.setOnClickListener(view -> {

            Cursor res = db.get_data();
            if(res.getCount()==0){
                Toast.makeText(getApplicationContext(), "No Entry Exist", Toast.LENGTH_SHORT).show();
                return;
            }
            StringBuilder buffer= new StringBuilder();
            while (res.moveToNext()){
                buffer.append("Name :").append(res.getString(0)).append("\n");
                buffer.append("Surname :").append(res.getString(1)).append("\n");
                buffer.append("ID_Number :").append(res.getString(2)).append("\n");
                buffer.append("Location :").append(res.getString(3)).append("\n");
                buffer.append("Registration_Date :").append(res.getString(4)).append("\n\n");
            }
            AlertDialog.Builder builder =  new AlertDialog.Builder(V_AccountActivity1.this);
            builder.setCancelable(true);
            builder.setTitle("Voter Details");
            builder.setMessage(buffer.toString());
            builder.show();

        });

        Voter_Update_btn2 = findViewById(R.id.Voter_Update_btn2);
        Voter_Update_btn2.setOnClickListener(view -> {

            String NamesTxt=Name_Account.getText().toString();
            String SurnameTxt=Surname_Account.getText().toString();
            String ID_NumberTxt=Id_Account.getText().toString();
            String LocationTxt=Location_Account.getText().toString();
            String RegistrationTxt= Registration_DateAccount.getText().toString();

            Boolean checkUpdatedData = db.updateVoterData(NamesTxt,SurnameTxt,ID_NumberTxt,LocationTxt,RegistrationTxt);
            if(checkUpdatedData){
                Toast.makeText(getApplicationContext(), "Update Successful", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "Update Failed", Toast.LENGTH_SHORT).show();
            }

        });

        DeleteDAta_btn = findViewById(R.id.DeleteDAta_btn);
        DeleteDAta_btn.setOnClickListener(view -> {

            String ID_NumberTxt=Id_Account.getText().toString();
            Boolean checkDeleteData = db.deleteVoterData(ID_NumberTxt);
            if(checkDeleteData){
                Toast.makeText(getApplicationContext(), "Delete Successful", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "Delete Failed", Toast.LENGTH_SHORT).show();
            }

        });



    }
}
