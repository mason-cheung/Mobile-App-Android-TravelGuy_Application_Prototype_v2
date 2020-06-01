package com.example.prototype_v2.service;

import android.content.Context;

import com.example.prototype_v2.database.DatabaseHelper;
import com.example.prototype_v2.entities.Restaurant;

import java.util.List;

public class DataService {
    private DatabaseHelper sqlite;

    public void connect(){

    }

    public void disconnect(){

    }

    public void init(Context context){
        sqlite = sqlite.getInstance(context);
    }

    public boolean add(Restaurant restaurant){
        return sqlite.insertRestaurant(restaurant.getName(), restaurant.getPhone(), restaurant.getAddress(), restaurant.getComment());
    }

    public List<Restaurant> getRestaurants(){
        List<Restaurant> restaurants = sqlite.getAllRestaurant();
        return restaurants;
    }

    public Restaurant getRestaurant(Long id){
        return sqlite.getRestaurant(id);
    }
}
