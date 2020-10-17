package com.health29.ustawi.models;

/**
 * Created by derrick.kaffo on 12/10/2020.
 * kaffoderrick@gmail.com
 */
public class Drug {
    private String name;
    private String price;
    private String category;
    private boolean available;

    public Drug() {

    }

    public Drug(String name, String price, String category, boolean available) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
