package models;

import java.io.Serializable;

public class Tag implements Serializable, Comparable<Tag> {

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

    @Override
    public int compareTo(Tag tag) {
        if (!key.equals(tag.getKey())){
            return key.compareTo(tag.getKey());
        }
        return value.compareTo(tag.getValue());
    }
}
