package com.example.fluentmindss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

public class Homepage extends AppCompatActivity {
    CardView cd1,cd2,cd3,cd4;
    TextView tvUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        tvUsername = findViewById(R.id.tvName);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String name = prefs.getString("name","");

        cd1 = findViewById(R.id.cvlearning);
        cd2 = findViewById(R.id.cvtest);
        cd3 = findViewById(R.id.cvpractice);
        cd4 = findViewById(R.id.cvresources);

        cd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Homepage.this, LearningActivity.class);
                startActivity(i);
            }
        });
        cd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Homepage.this, TestClass.class);
                startActivity(i);
            }
        });
        cd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Homepage.this, practiceclass.class);
                startActivity(i);
            }
        });
        cd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Homepage.this, resourcesclass.class);
                startActivity(i);
            }
        });
    }
}