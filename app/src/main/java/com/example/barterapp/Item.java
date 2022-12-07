package com.example.barterapp;

import android.media.Image;

public class Item {

    private String id;
    public String name;
    public int value;
    public String owner;
    public String image;

    public Item(String id, String name, int value, String owner, String image) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.owner = owner;
        this.image = image;
    }

    public Item() {

    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public String getOwner() {
        return owner;
    }
}
