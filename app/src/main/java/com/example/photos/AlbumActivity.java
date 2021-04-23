package com.example.photos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.GridView;

import java.util.List;

public class AlbumActivity extends AppCompatActivity {

    Album currentAlbum;
    List<Photo> photoList;

    GridView gridview;
    PhotosAdapter photosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        currentAlbum = (Album) getIntent().getSerializableExtra("CurrentAlbum");
        photoList = currentAlbum.getPhotoList();

        gridview = findViewById(R.id.gridview);
        photosAdapter = new PhotosAdapter(this, photoList);
        gridview.setAdapter(photosAdapter);

    }

    public void addPhotoOnClick(View view){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, 3);

    }

    public void slideshowOnClick(View view){


    }

}