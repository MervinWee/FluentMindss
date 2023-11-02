package com.example.fluentmindss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class GrammerActivity extends AppCompatActivity {
ImageView ivback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammer);

        ivback = findViewById(R.id.ivback);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GrammerActivity.this, LearningActivity.class);
                startActivity(i);
            }
        });
    }
}