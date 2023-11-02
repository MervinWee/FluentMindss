package com.example.fluentmindss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class LearningActivity extends AppCompatActivity {
    ListView lv;
    ArrayAdapter aa;
    ArrayList<String> learninglist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

        lv = findViewById(R.id.lvlearningstuff);

        learninglist = new ArrayList<String>();
        learninglist.add("Grammer");
        learninglist.add("Reading");
        learninglist.add("Vocabulary");
        learninglist.add("Listening");

        Intent i = getIntent();
        int number = i.getIntExtra("number",0);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(LearningActivity.this,
                android.R.layout.simple_list_item_1, android.R.id.text1, learninglist);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedItem = learninglist.get(position);

                if (selectedItem.equals("Grammer")){
                    Intent intent = new Intent(LearningActivity.this, GrammerActivity.class);
                    startActivity(intent);
                }else if (selectedItem.equals("Reading")) {
                    Intent intent = new Intent(LearningActivity.this, ReadingActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Vocabulary")) {
                    Intent intent = new Intent(LearningActivity.this, VocabularyActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Listening")) {
                    Intent intent = new Intent(LearningActivity.this, ListeningActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}