package com.example.photos;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    Spinner searchTag1, searchTag2;
    TextView searchVal1, searchVal2;
    RadioGroup radioGroup;
    Button search, createAlbum;
    GridView searchGrid;

    ArrayList<Album> albumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchTag1 = findViewById(R.id.searchTag1);
        searchTag2 = findViewById(R.id.searchTag2);
        searchVal1 = findViewById(R.id.searchVal1);
        searchVal2 = findViewById(R.id.searchVal2);
        radioGroup = findViewById(R.id.toggleGroup);
        search = findViewById(R.id.search);

        searchGrid = findViewById(R.id.searchGrid);
        createAlbum = findViewById(R.id.searchCreateAlbum);

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
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        searchVal1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

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
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        searchVal2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

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

//        Bundle bundle = getIntent().getExtras();
//        albumList = bundle.getParcelable("AlbumList");
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
        createAlbum.setEnabled(false);
    }

    public void searchOnClick(View view) {
        //find photos
        List<Photo> searchResults = new ArrayList<>();

        SearchPhotosAdapter searchPhotosAdapter = new SearchPhotosAdapter(this, searchResults);
        searchGrid.setAdapter(searchPhotosAdapter);

        if (searchResults.size() == 0)
            createAlbum.setEnabled(false);
        else
            createAlbum.setEnabled(true);
    }

    public void createAlbumOnClick(View view) {

    }
}