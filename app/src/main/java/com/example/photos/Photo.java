package com.example.photos;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Photo {

    private String name;
    private File file;
    private Map<String, List<String>> tags;

    public Photo(File file) {
        this.file = file;
        name = file.getName();
        tags = new HashMap<>();

        tags.put("location",  new ArrayList<String>());
        tags.put("person",  new ArrayList<String>());
    }

    public boolean addTag(String key, String value) {
        key = key.trim();
        value = value.trim();

        for (String existingValue: tags.get(key)) {
            if (value.toLowerCase().equals(existingValue.toLowerCase()))
                return false;
        }

        tags.get(key).add(value);

        return true;
    }

}
