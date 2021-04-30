package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Album implements Serializable {

    private String name;
    private List<Photo> photoList;

    public Album(String name) {
        this.name = name;
        photoList = new ArrayList<>();
    }

    public boolean addPhoto(Photo newPhoto) {
        photoList.add(newPhoto); //TODO: only add non-duplicate, make a set maybe
        return true;
    }

    public boolean deletePhoto(Photo photo) {
        for (int i = 0; i < photoList.size(); i++) {
            if (photo.equals(photoList.get(i))) {
                photoList.remove(i);
                return true;
            }
        }

        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return photoList.size();
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public String toString() {
        return name +" "+ photoList.size();
    }

}
