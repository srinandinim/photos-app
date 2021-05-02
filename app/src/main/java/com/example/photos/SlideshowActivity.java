package com.example.photos;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import models.Album;
import models.User;

public class SlideshowActivity extends AppCompatActivity {

    Album currentAlbum;
    ImageView imageView;
    Button prev;
    Button next;

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);

        imageView = findViewById(R.id.slideImageView);
        next = findViewById(R.id.next);
        prev = findViewById(R.id.prev);

        currentAlbum = User.currentAlbum;

        if (currentAlbum.getSize() > 0){
            imageView.setImageURI(Uri.parse(currentAlbum.getPhotoList().get(counter).getUriString()));
        }
        else{
            next.setClickable(false);
            prev.setClickable(false);
        }
    }


    public void nextOnclick(View view){

        counter++;
        if (counter >= currentAlbum.getSize())
            counter = 0;

        imageView.setImageURI(Uri.parse(currentAlbum.getPhotoList().get(counter).getUriString()));

    }

    public void prevOnClick(View view){

        counter--;
        if (counter < 0)
            counter = currentAlbum.getSize()-1;

        imageView.setImageURI(Uri.parse(currentAlbum.getPhotoList().get(counter).getUriString()));

    }

    public void backOnClick(View view){
        finish();
    }


}