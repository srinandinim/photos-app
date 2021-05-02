package models;

import com.example.photos.HomeActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    public static List<Album> albumList = new ArrayList<>();
    public static Album currentAlbum = null;
    public static Photo currentPhoto = null;

    public static void serialize() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(new File(HomeActivity.filesDir, "SerializedData.dat")));
            oos.writeObject(albumList);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deserialize() {
        try {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(HomeActivity.filesDir+"/SerializedData.dat"));
            albumList = (ArrayList<Album>) ois.readObject();
            ois.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

}
