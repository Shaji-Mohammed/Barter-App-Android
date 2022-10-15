package com.example.barterapp;

public class Product {

    public String name;
    public int value;
    public String seller;

    public Product(String name, int value, String seller) {
        this.name = name;
        this.value = value;
        this.seller = seller;
    }

    public Product() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public String getSeller() {
        return seller;
    }
}
