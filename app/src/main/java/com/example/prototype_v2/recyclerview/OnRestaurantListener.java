package com.example.prototype_v2.recyclerview;

import com.example.prototype_v2.entities.Restaurant;

public interface OnRestaurantListener {
    void onRestaurantClick(Restaurant restaurant);
    void onMenuClick(Restaurant restaurant);
}
