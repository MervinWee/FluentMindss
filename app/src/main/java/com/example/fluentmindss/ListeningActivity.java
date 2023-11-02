package com.example.fluentmindss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ListeningActivity extends AppCompatActivity {
    ImageView ivback;
    private LinearLayout showItemsContainer;
    Button btnshowbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening);
        showItemsContainer = findViewById(R.id.show_items_container);
        btnshowbutton = findViewById(R.id.show_button);
        ivback = findViewById(R.id.ivback);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListeningActivity.this, LearningActivity.class);
                startActivity(i);
            }
        });
        btnshowbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             showItems();
            }
        });
    }
    public void showItems() {
        showItemsContainer.setVisibility(View.VISIBLE);
    }
}