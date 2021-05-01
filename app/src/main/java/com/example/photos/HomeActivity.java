package com.example.photos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import adapters.AlbumsAdapter;
import models.Album;
import models.User;

public class HomeActivity extends AppCompatActivity {

    private static final String storeDir = "../../res" + File.separator + "raw";
    private static final String storeFile = "SerializedData.dat";

    // private final String pathToAppFolder = getExternalFilesDir(null).getAbsolutePath();
    // private final String filePath = pathToAppFolder + File.separator  + "list.ser";

    GridView albumGrid;

    AlbumsAdapter albumsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // deserialize();

        albumGrid = findViewById(R.id.albumGrid);
        albumsAdapter = new AlbumsAdapter(this);
        albumGrid.setAdapter(albumsAdapter);
    }

    public void searchOnClick(View view) {
        startActivity(new Intent(this, SearchActivity.class));
    }

    public void createOnClick(View view) {
        final EditText input = new EditText(this);

        AlertDialog dialog = (new AlertDialog.Builder(this))
                .setTitle("Create New Album")
                .setMessage("\nAlbum Name:")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!containsAlbum(input.getText().toString().trim())) {
                            User.albumList.add(new Album(input.getText().toString()));
                            albumsAdapter.notifyDataSetChanged();
                            //serialize();
                        } else {
                            Toast.makeText(HomeActivity.this, "Invalid Album Name", Toast.LENGTH_SHORT).show();
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

    /*

    public void serialize() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storeDir + File.separator + storeFile));
            // System.out.println(filePath);
            oos.writeObject(albumList);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserialize() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(storeDir + File.separator + storeFile));
            albumList = (ArrayList<Album>) ois.readObject();
            ois.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            albumList = new ArrayList<>();
        }

    }
    */

}
