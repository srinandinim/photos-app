package com.example.photos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class HomeActivity extends AppCompatActivity {

    private static final String storeDir = "../../res"+ File.separator +"raw";
    private static final String storeFile = "SerializedData.dat";

    ImageButton search, createAlbum;

    List<Album> albumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        search = findViewById(R.id.search);
        createAlbum = findViewById(R.id.createAlbum);

        deserialize();
        //albumList = new ArrayList<>();
        //loads all albums;


    }



    public void searchOnClick(View view){
        startActivity(new Intent(this, SearchActivity.class));
    }

    public void createOnClick(View view){
        final EditText input = new EditText( this );

        AlertDialog dialog = (new AlertDialog.Builder(this))
                .setTitle("Create New Album")
                .setMessage("\nAlbum Name:")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!containsAlbum(input.getText().toString().trim())){
                            albumList.add(new Album(input.getText().toString()));
                            System.out.println("asdf");
                            serialize();
                            //TODO: update grid pane
                        } else{
                            Toast.makeText(HomeActivity.this, "Invalid Album Name", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();

        dialog.setView(input, 40,0, 40,0 );
        dialog.show();
    }

    private boolean containsAlbum(String name){
        if (name == null || name.isEmpty())
            return true;

        for (Album currAlbum: albumList){
            if (currAlbum.getName().toLowerCase().equals(name.toLowerCase()))
                return true;
        }

        return false;
    }

    public void serialize() {
        //System.out.println("OUT");
        String pathToAppFolder = getExternalFilesDir(null).getAbsolutePath();
        String filePath = pathToAppFolder + File.separator + "list.ser";

        try {
            //System.out.println(storeDir + File.separator + storeFile);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
            System.out.println(filePath);

            oos.writeObject(albumList);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void deserialize() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(storeDir + File.separator + storeFile));
            albumList = (List<Album>) ois.readObject();
            ois.close();
        } catch (ClassNotFoundException | IOException e) {
            albumList = new ArrayList<>();
        }

    }



}
