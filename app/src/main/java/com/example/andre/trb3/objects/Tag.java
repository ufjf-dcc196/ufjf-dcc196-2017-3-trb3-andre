package com.example.andre.trb3.objects;

import java.io.Serializable;

public class Tag implements Serializable {
    private Long id;
    private String tag;

    public Tag() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return getTag();
    }
}
