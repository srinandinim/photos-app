package com.example.photos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AlbumActivity extends AppCompatActivity {

    Album currentAlbum;
    List<Photo> photoList;

    GridView gridview;
    PhotosAdapter photosAdapter;

    final int PICK_IMAGE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        currentAlbum = (Album) getIntent().getSerializableExtra("CurrentAlbum");
        photoList = currentAlbum.getPhotoList();

        gridview = findViewById(R.id.photosGrid);
        photosAdapter = new PhotosAdapter(this, currentAlbum, photoList);
        gridview.setAdapter(photosAdapter);

    }

    public void addPhotoOnClick(View view) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
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

    }

    public void backOnClick(View view) {
        startActivity(new Intent(this, HomeActivity.class));
    }

}