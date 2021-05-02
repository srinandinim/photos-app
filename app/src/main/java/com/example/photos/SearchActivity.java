package com.example.photos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import adapters.SearchPhotosAdapter;
import models.Album;
import models.Photo;
import models.User;

public class SearchActivity extends AppCompatActivity {

    Spinner searchTag1, searchTag2;
    EditText searchVal1, searchVal2;
    RadioGroup radioGroup;
    RadioButton andChoice, orChoice;
    Button search;
    //Button createAlbum;
    GridView searchGrid;

    List<Photo> searchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchTag1 = findViewById(R.id.searchTag1);
        searchTag2 = findViewById(R.id.searchTag2);
        searchVal1 = findViewById(R.id.searchVal1);
        searchVal2 = findViewById(R.id.searchVal2);
        radioGroup = findViewById(R.id.toggleGroup);
        andChoice = findViewById(R.id.andChoice);
        orChoice = findViewById(R.id.orChoice);
        search = findViewById(R.id.search);

        searchGrid = findViewById(R.id.searchGrid);
        //createAlbum = findViewById(R.id.searchCreateAlbum);

        disableSearch();

        searchTag1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                disableSearch();
                if (i != 0) {
                    searchVal1.setText("");
                    searchVal1.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        searchVal1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().isEmpty()) {
                    search.setEnabled(false);
                    searchTag2.setEnabled(false);
                } else {
                    search.setEnabled(true);
                    searchTag2.setEnabled(true);
                }
            }
        });

        searchTag2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                radioGroup.clearCheck();
                for (int j = 0; j < radioGroup.getChildCount(); j++) {
                    (radioGroup.getChildAt(j)).setEnabled(false);
                }

                if (i == 0) {
                    searchVal2.setText("");
                    searchVal2.setEnabled(false);
                    if (searchTag1.getSelectedItemPosition() != 0) {
                        search.setEnabled(true);
                    }
                } else {
                    searchVal2.setText("");
                    searchVal2.setEnabled(true);
                    search.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        searchVal2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                radioGroup.clearCheck();
                if (editable.toString().isEmpty()) {
                    for (int j = 0; j < radioGroup.getChildCount(); j++) {
                        (radioGroup.getChildAt(j)).setEnabled(false);
                    }
                } else {
                    for (int j = 0; j < radioGroup.getChildCount(); j++) {
                        (radioGroup.getChildAt(j)).setEnabled(true);
                    }
                }

                search.setEnabled(false);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                search.setEnabled(true);
            }
        });

    }

    private void disableSearch() {
        searchVal1.setText("");
        searchVal1.setEnabled(false);

        searchTag2.setSelection(0);
        searchTag2.setEnabled(false);

        searchVal2.setText("");
        searchVal2.setEnabled(false);

        radioGroup.clearCheck();
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            (radioGroup.getChildAt(i)).setEnabled(false);
        }

        search.setEnabled(false);
        //createAlbum.setEnabled(false);
    }

    public void searchOnClick(View view) {

        searchResults = new ArrayList<>();

        for (Album currAlbum: User.albumList){
            for (Photo currPhoto: currAlbum.getPhotoList()){
                if (fitsTagsSpecifications(currPhoto))
                    searchResults.add(currPhoto);
            }
        }

        SearchPhotosAdapter searchPhotosAdapter = new SearchPhotosAdapter(this, searchResults);
        searchGrid.setAdapter(searchPhotosAdapter);

        //createAlbum.setEnabled(searchResults.size() != 0);
    }


    private boolean fitsTagsSpecifications(Photo currentPhoto) {

        boolean tag1Include = false;
        boolean tag2Include = false;

        for (String value: currentPhoto.valuesWithKey(searchTag1.getSelectedItem().toString())){
            if (value.startsWith(searchVal1.getText().toString().trim())) {
                tag1Include = true;
                break;
            }
        }

        String toggleValue = null;
        if (searchVal2.isEnabled()) {
            RadioButton chosenTagButton = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
            toggleValue = chosenTagButton.getText().toString();

            for (String value: currentPhoto.valuesWithKey(searchTag2.getSelectedItem().toString())){
                if (value.startsWith(searchVal2.getText().toString().trim())) {
                    tag2Include = true;
                    break;
                }
            }
        }

        if (toggleValue == null)
            return tag1Include;
        else if (toggleValue.equals(andChoice.getText().toString()))
            return tag1Include && tag2Include;
        else if (toggleValue.equals(orChoice.getText().toString()))
            return tag1Include || tag2Include;

        return false;
    }

    /*
    public void createAlbumOnClick(View view) {

        if (searchResults.size() == 0)
            return;

        final EditText input = new EditText(this);

        AlertDialog dialog = (new AlertDialog.Builder(this))
                .setTitle("Create New Album")
                .setMessage("\nAlbum Name:")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!containsAlbum(input.getText().toString().trim())) {
                            User.albumList.add(new Album(input.getText().toString(), searchResults));
                            //serialize();
                        } else {
                            Toast.makeText(SearchActivity.this, "Invalid Album Name", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();

        dialog.setView(input, 40, 0, 40, 0);
        dialog.show();

    }

    private boolean containsAlbum(String name) {
        if (name == null || name.isEmpty())
            return true;

        for (Album currAlbum : User.albumList) {
            if (currAlbum.getName().toLowerCase().equals(name.toLowerCase()))
                return true;
        }

        return false;
    }
    */

    public void backOnClick(View view){ finish(); }
}