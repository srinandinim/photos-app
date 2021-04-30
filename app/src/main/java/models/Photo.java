package models;

import android.net.Uri;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Photo implements Serializable {

    private String name;
    private File file;
    private List<Tag> tags;
    private String uri;

    public Photo(Uri uri) {
        this.uri = uri.toString();
        //System.out.println(getUriString() +" asdf "+ uri.getPath() +" asdf "+ uri.getLastPathSegment());
        file = new File(uri.getPath());
        name = file.getName();
        tags = new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Photo))
            return false;

        return file.equals(((Photo) obj).getFile()); //TODO: check might be different cause URI
    }


    public boolean addTag(String key, String value) {
        key = key.trim();
        value = value.trim();

        for (Tag existingTag : tags) {
            if (key.toLowerCase().equals(existingTag.getKey().toLowerCase())) {
                if (value.toLowerCase().equals(existingTag.getValue().toLowerCase())) {
                    return false;
                }
            }
        }

        tags.add(new Tag(key, value));

        return true;
    }

    public boolean deleteTag(String key, String value) {
        key = key.trim();
        value = value.trim();

        for (int i = 0; i < tags.size(); i++) {
            Tag existingTag = tags.get(i);
            if (key.toLowerCase().equals(existingTag.getKey().toLowerCase())) {
                if (value.toLowerCase().equals(existingTag.getValue().toLowerCase())) {
                    tags.remove(i);
                    return true;
                }
            }
        }

        return false;
    }

    public String getName() {
        return name;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public File getFile() {
        return file;
    }

    public String getUriString() {
        return uri;
    }

    //public Uri getUri(){ return uri; }

}