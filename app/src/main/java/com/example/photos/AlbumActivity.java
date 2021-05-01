package com.example.photos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import adapters.PhotosAdapter;
import models.Album;
import models.Photo;
import models.User;

public class AlbumActivity extends AppCompatActivity {

    Album currentAlbum;

    GridView gridview;
    PhotosAdapter photosAdapter;

    final int PICK_IMAGE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        currentAlbum = User.currentAlbum; //(Album) getIntent().getSerializableExtra("currentAlbum");

        gridview = findViewById(R.id.photosGrid);
        photosAdapter = new PhotosAdapter(this);
        gridview.setAdapter(photosAdapter);
        System.out.println("album create " + currentAlbum.getSize());
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("album resume " + currentAlbum.getSize());

    }

    public void addPhotoOnClick(View view) {
        Intent galleryIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI); //TODO: used to be ACTION_PICK, https://stackoverflow.com/questions/40438537/permission-denied-opening-provider-com-google-android-apps-photos-contentprovi
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE) {
            currentAlbum.addPhoto(new Photo(data.getData()));
            photosAdapter.notifyDataSetChanged();
        }

    }

    public void slideshowOnClick(View view) {

        startActivity(new Intent(this, SlideshowActivity.class));

    }

    public void backOnClick(View view) {
        finish();
        //startActivity(new Intent(this, HomeActivity.class));
    }

}