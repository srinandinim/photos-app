package models;

import java.util.ArrayList;
import java.util.List;

public class User {

    public static List<Album> albumList = new ArrayList<>();
    public static Album currentAlbum = null;
    public static Photo currentPhoto = null;

    public static void setCurrentAlbum(Album album){
        System.out.println("Setting");
        currentAlbum = album;
    }


}
