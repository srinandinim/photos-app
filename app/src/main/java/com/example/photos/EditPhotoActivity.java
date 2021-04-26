package com.example.photos;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

import adapters.TagAdapter;
import models.Album;
import models.Photo;

public class EditPhotoActivity extends AppCompatActivity {

    Photo currentPhoto;
    Album currentAlbum;

    ImageView photoPicture;
    TextView photoName;
    ListView listView;

    TagAdapter tagAdapter;

    RadioGroup tagType;
    EditText tagValue;
    Button addNewTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_photo);

        currentPhoto = (Photo) getIntent().getSerializableExtra("CurrentPhoto");
        currentAlbum = (Album) getIntent().getSerializableExtra("CurrentAlbum");

        photoPicture = findViewById(R.id.photoPicture);
        photoPicture.setImageURI(Uri.parse(currentPhoto.getUriString())); //TODO: Scale image

        photoName = findViewById(R.id.photoName);
        photoName.setText(currentPhoto.getName());

        listView = findViewById(R.id.tagList);
        tagAdapter = new TagAdapter(this, currentPhoto.getTags());
        listView.setAdapter(tagAdapter);

        tagType = findViewById(R.id.tagType);
        tagValue = findViewById(R.id.tagValue);
        addNewTag = findViewById(R.id.addNewTag);

        addNewTag.setEnabled(false);
        tagType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (tagValue.getText().length() > 0) {
                    addNewTag.setEnabled(true);
                } else {
                    addNewTag.setEnabled(false);
                }
            }
        });

        tagValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0 && tagType.getCheckedRadioButtonId() != -1) {
                    addNewTag.setEnabled(true);
                } else {
                    addNewTag.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


    }

    public void moveOnClick(View view) {

    }

    public void addTagOnClick(View view) {
        int id = tagType.getCheckedRadioButtonId();
        RadioButton chosenTagButton = tagType.findViewById(id);

        String chosenTag = chosenTagButton.getText().toString();
        String chosenValue = tagValue.getText().toString();

        boolean success = currentPhoto.addTag(chosenTag, chosenValue);
        if (!success) {
            AlertDialog dialog = (new AlertDialog.Builder(this))
                    .setTitle("Add New Tag Error")
                    .setMessage("\nThat tag is not available.")
                    .setPositiveButton("OK", null)
                    .create();
            dialog.show();
        } else {
//            System.out.println (currentPhoto.getTags());
            tagAdapter.notifyDataSetChanged();
        }

        tagType.clearCheck();
        tagValue.setText("");
    }

    public void backOnClick(View view) {
        Intent intent = new Intent(this, AlbumActivity.class);
        intent.putExtra("CurrentAlbum", (Serializable) currentAlbum);
        startActivity(intent);
    }
}