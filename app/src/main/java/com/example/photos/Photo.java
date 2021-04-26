package com.example.photos;

import android.net.Uri;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Photo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private File file;
    private Map<String, List<String>> tags;
    private Uri uri;

    public Photo(Uri uri) {
        this.uri = uri;
        file = new File(uri.getPath());
        name = file.getName();
        tags = new HashMap<>();

        tags.put("location", new ArrayList<String>());
        tags.put("person", new ArrayList<String>());
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

        for (String existingValue : tags.get(key)) {
            if (value.toLowerCase().equals(existingValue.toLowerCase()))
                return false;
        }

        tags.get(key).add(value);

        return true;
    }

    public boolean deleteTag(String key, String value) {
        key = key.trim();
        value = value.trim();

        for (int i = 0; i < tags.get(key).size(); i++) {
            String existingValue = tags.get(key).get(i);
            if (value.toLowerCase().equals(existingValue.toLowerCase())) {
                tags.get(key).remove(i);
                return true;
            }
        }

        return false;
    }

    public String getName() {
        return name;
    }

    public Map<String, List<String>> getTags() {
        return tags;
    }

    public File getFile() {
        return file;
    }

    public Uri getUri() {
        return uri;
    }

}