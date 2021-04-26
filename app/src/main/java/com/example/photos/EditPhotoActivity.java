package com.example.photos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditPhotoActivity extends AppCompatActivity {

    Photo currentPhoto;

    ImageView photoPicture;
    TextView photoName;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_photo);

        currentPhoto = (Photo) getIntent().getSerializableExtra("CurrentPhoto");

        photoPicture = findViewById(R.id.photoPicture);
        photoPicture.setImageURI(currentPhoto.getUri()); //TODO: Scale image

        photoName = findViewById(R.id.photoName);
        photoName.setText(currentPhoto.getName());

        listView = findViewById(R.id.listView);
        


    }

    public void moveOnClick(View view){

    }

    public void addTagOnClick(View view) {

    }

    public void backOnClick(View view) {
        startActivity(new Intent(this, HomeActivity.class));
    }
}