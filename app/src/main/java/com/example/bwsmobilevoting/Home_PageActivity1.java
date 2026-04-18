package com.example.bwsmobilevoting;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Home_PageActivity1 extends AppCompatActivity {

    ImageView Home,Profile,Listing,Count,Results,Exit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage1);

        Home=findViewById(R.id.Home_symbol);
        Home.setOnClickListener(view -> {
            Intent Option8=new Intent(Home_PageActivity1.this,Home_PageActivity1.class);
            startActivity(Option8);
        });

        Profile=findViewById(R.id.PROFILE_symbol);
        Profile.setOnClickListener(view -> {
            Intent Option8=new Intent(Home_PageActivity1.this,V_AccountActivity1.class);
            startActivity(Option8);
        });

        Listing=findViewById(R.id.Listing_symbol);
        Listing.setOnClickListener(view -> {
            Intent Option8=new Intent(Home_PageActivity1.this,Candidate_ListingActivity1.class);
            startActivity(Option8);
        });

        Count=findViewById(R.id.Count_symbol);
        Count.setOnClickListener(view -> {
            Intent Option8=new Intent(Home_PageActivity1.this,Vote_CountingActivity1.class);
            startActivity(Option8);
        });

        Results=findViewById(R.id.Results_symbol);
        Results.setOnClickListener(view -> {
            Intent Option8=new Intent(Home_PageActivity1.this,Results_Activity1.class);
            startActivity(Option8);
        });

        Exit=findViewById(R.id.Exit_symbol);
        Exit.setOnClickListener(view -> {
            Intent Option8=new Intent(Home_PageActivity1.this,Home_PageActivity1.class);
            startActivity(Option8);
        });
    }


}
