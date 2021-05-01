package models;

import androidx.annotation.Nullable;

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
        if (!key.toLowerCase().equals(tag.getKey().toLowerCase())){
            return key.compareToIgnoreCase(tag.getKey());
        }
        return value.compareToIgnoreCase(tag.getValue());
    }

    @Override
    public boolean equals(@Nullable Object obj) {

        if (obj instanceof Tag){
            if ( this.compareTo((Tag)obj) == 0)
                return true;
        }

        return false;

    }
}
