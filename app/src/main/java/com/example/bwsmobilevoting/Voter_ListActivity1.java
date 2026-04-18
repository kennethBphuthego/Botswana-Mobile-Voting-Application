package com.example.bwsmobilevoting;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Voter_ListActivity1 extends AppCompatActivity {

    ImageView Listing,Count,Results,Exit;

    DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voterlist1);

        Listing=findViewById(R.id.Listing_symbol);
        Listing.setOnClickListener(view -> {
            Intent Option8=new Intent(Voter_ListActivity1.this,Candidate_ListingActivity1.class);
            startActivity(Option8);
        });

        Count=findViewById(R.id.Count_symbol);
        Count.setOnClickListener(view -> {
            Intent Option8=new Intent(Voter_ListActivity1.this,Vote_CountingActivity1.class);
            startActivity(Option8);
        });

        Results=findViewById(R.id.Results_symbol);
        Results.setOnClickListener(view -> {
            Intent Option8=new Intent(Voter_ListActivity1.this,Results_Activity1.class);
            startActivity(Option8);
        });

        Exit=findViewById(R.id.Exit_symbol);
        Exit.setOnClickListener(view -> {
            Intent Option8=new Intent(Voter_ListActivity1.this,Voter_ListActivity1.class);
            startActivity(Option8);
        });

        ListView lst1 = findViewById(R.id.lst1);
        db = new DBHelper(this);

        ArrayList<String>VoterList = new ArrayList<>();
        Cursor Info = db.get_data();

        if(Info.getCount()==0){
            Toast.makeText(getApplicationContext(), "No Voter registered", Toast.LENGTH_SHORT).show();
        }else{
            while(Info.moveToNext()){
                VoterList.add(Info.getString(0));
                VoterList.add(Info.getString(1));
                VoterList.add(Info.getString(2));
                VoterList.add(Info.getString(3));
                VoterList.add(Info.getString(4));

                ListAdapter ListAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,VoterList);
                lst1.setAdapter(ListAdapter1);
            }
        }


    }

}
