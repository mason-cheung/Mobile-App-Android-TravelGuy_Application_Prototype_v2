package com.example.prototype_v2.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prototype_v2.R;
import com.example.prototype_v2.entities.Restaurant;

import java.util.List;

public class RestaurantRecyclerViewAdapter extends RecyclerView.Adapter<RestaurantViewHolder> {

    private List<Restaurant> restaurants;
    private Context context;
    private OnRestaurantListener onRestaurantListener;

    public RestaurantRecyclerViewAdapter(List<Restaurant> restaurants, Context context, OnRestaurantListener onRestaurantListener) {
        this.restaurants = restaurants;
        this.context = context;
        this.onRestaurantListener = onRestaurantListener;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View restaurantView = inflater.inflate(R.layout.recycler_item_view, parent, false);

        //Return a new holder instance.
        RestaurantViewHolder restaurantViewHolder = new RestaurantViewHolder(restaurantView, onRestaurantListener);
        return restaurantViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        //get the data from the list, based on position
        Restaurant restaurant = restaurants.get(position);
        // bind the values in the MonsterViewHolder
        holder.updateRestaurant(restaurant);
        holder.bind(restaurant, onRestaurantListener);
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }
}
