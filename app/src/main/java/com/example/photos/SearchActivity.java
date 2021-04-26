package com.example.photos;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {

    Spinner searchTag1, searchTag2;
    TextView searchVal1, searchVal2;
    Button search, createAlbum;
    GridView searchGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchTag1 = findViewById(R.id.searchTag1);
        searchTag2 = findViewById(R.id.searchTag2);
        searchVal1 = findViewById(R.id.searchVal1);
        searchVal2 = findViewById(R.id.searchVal2);
        search = findViewById(R.id.search);

        searchGrid = findViewById(R.id.searchGrid);
        createAlbum = findViewById(R.id.searchCreateAlbum);



    }
}