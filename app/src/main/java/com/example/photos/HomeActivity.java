package com.example.photos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity {

    ImageButton search, createAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        search = findViewById(R.id.search);
        createAlbum = findViewById(R.id.createAlbum);

        //loads all albums;


    }

    public void searchOnClick(View view){
        startActivity(new Intent(this, SearchActivity.class));
    }

    public void createOnClick(View view){

    }



}
