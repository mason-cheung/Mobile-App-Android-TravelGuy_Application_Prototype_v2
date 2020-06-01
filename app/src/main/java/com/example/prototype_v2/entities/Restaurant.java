package com.example.prototype_v2.entities;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class Restaurant implements Serializable {

    public static final String RESTAURANT_KEY = "restaurant_key";

    public Long id;
    public String name;
    public String address;
    public String phone;
    public String comment;
    public String imageFileName;

    public Restaurant() {

    }

    public Restaurant(Long id, String name, String address, String phone, String comment) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id + System.lineSeparator() +
                ", name='" + name + System.lineSeparator() +
                ", address='" + address + System.lineSeparator() +
                ", phone='" + phone + System.lineSeparator() +
                ", comment='" + comment + System.lineSeparator() +
                '}' + System.lineSeparator();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Restaurant restaurant = (Restaurant) obj;
        return id.equals(restaurant.id);
    }
}
