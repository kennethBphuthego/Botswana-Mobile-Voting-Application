package com.example.bwsmobilevoting;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Candidate_ListingActivity1 extends AppCompatActivity {

    ImageView Home,Profile,Listing,Count,Results,Exit;
    CheckBox checkBox1,checkBox2,checkBox3,checkBox4,checkBox5,checkBox6;
    Button Cast;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_listing);

        Home=findViewById(R.id.Home_symbol);
        Home.setOnClickListener(view -> {
            Intent Option8=new Intent(Candidate_ListingActivity1.this,Home_PageActivity1.class);
            startActivity(Option8);
        });

        Profile=findViewById(R.id.PROFILE_symbol);
        Profile.setOnClickListener(view -> {
            Intent Option8=new Intent(Candidate_ListingActivity1.this,V_AccountActivity1.class);
            startActivity(Option8);
        });

        Listing=findViewById(R.id.Listing_symbol);
        Listing.setOnClickListener(view -> {
            Intent Option8=new Intent(Candidate_ListingActivity1.this,Candidate_ListingActivity1.class);
            startActivity(Option8);
        });

        Count=findViewById(R.id.Count_symbol);
        Count.setOnClickListener(view -> {
            Intent Option8=new Intent(Candidate_ListingActivity1.this,Vote_CountingActivity1.class);
            startActivity(Option8);
        });

        Results=findViewById(R.id.Results_symbol);
        Results.setOnClickListener(view -> {
            Intent Option8=new Intent(Candidate_ListingActivity1.this,Results_Activity1.class);
            startActivity(Option8);
        });

        Exit=findViewById(R.id.Exit_symbol);
        Exit.setOnClickListener(view -> {
            Intent Option8=new Intent(Candidate_ListingActivity1.this,Candidate_ListingActivity1.class);
            startActivity(Option8);
        });

        checkBox1=findViewById(R.id.checkBox1);
        checkBox2=findViewById(R.id.checkBox2);
        checkBox3=findViewById(R.id.checkBox3);
        checkBox4=findViewById(R.id.checkBox4);
        checkBox5=findViewById(R.id.checkBox5);
        checkBox6=findViewById(R.id.checkBox6);

        Cast=findViewById(R.id.Cast_btn);
        Cast.setOnClickListener(view -> {
             int Count=0;
             if(checkBox1.isChecked()){

             }
        });

    }
}
