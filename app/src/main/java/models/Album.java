package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Album implements Serializable {

    private String name;
    private List<Photo> photoList;

    public Album(String name) {
        this.name = name.trim();
        photoList = new ArrayList<>();
    }

    /*
    public Album(String name, List<Photo> photoList) {
        this.name = name.trim();
        this.photoList = photoList;
    }
     */

    public boolean addPhoto(Photo newPhoto) {
        /*
        if (photoList.contains(newPhoto))
            return false;

        for (Album iterAlbum: User.albumList){
            for (Photo iterPhoto: iterAlbum.getPhotoList()){
                if (iterPhoto.equals(newPhoto)){ //if the photo has already been added to another album add same reference
                    photoList.add(iterPhoto);
                    return true;
                }
            }
        }
        */

        photoList.add(newPhoto);
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
