package com.example.photos;

import java.io.Serializable;

public class Tag implements Serializable {

    private String key;
    private String value;

    public Tag(String key, String value) {
        this.key = key.trim();
        this.value = value.trim();
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString(){
        return key + " " + value;
    }
}
