package com.example.photos;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import adapters.TagAdapter;
import models.Album;
import models.Photo;
import models.User;

public class EditPhotoActivity extends AppCompatActivity {

    Photo currentPhoto;

    ImageView photoPicture;
    TextView photoName;
    ListView listView;

    Button movePhoto;
    Album parentAlbum;

    TagAdapter tagAdapter;

    RadioGroup tagType;
    EditText tagValue;
    Button addNewTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_photo);

        currentPhoto = User.currentPhoto;
        parentAlbum = User.currentAlbum;
        movePhoto = findViewById(R.id.movePhoto);

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
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0 && tagType.getCheckedRadioButtonId() != -1) {
                    addNewTag.setEnabled(true);
                } else {
                    addNewTag.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });


    }

    public void moveOnClick(View view) {

        PopupMenu popupMenu = new PopupMenu(this, view);

        for (Album album: User.albumList)
            popupMenu.getMenu().add(album.getName());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Album pickedAlbum = null;
                for (Album album: User.albumList){
                    if (album.getName().equals(menuItem.getTitle())){
                        pickedAlbum = album;
                        break;
                    }
                }
                parentAlbum.deletePhoto(currentPhoto);
                pickedAlbum.addPhoto(currentPhoto);
                parentAlbum = pickedAlbum;
                Toast.makeText(EditPhotoActivity.this, "Moved photo to: "+ parentAlbum.getName(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        popupMenu.show();

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
            tagAdapter.notifyDataSetChanged();
        }

        tagType.clearCheck();
        tagValue.setText("");
    }

    public void backOnClick(View view) { finish(); }
}