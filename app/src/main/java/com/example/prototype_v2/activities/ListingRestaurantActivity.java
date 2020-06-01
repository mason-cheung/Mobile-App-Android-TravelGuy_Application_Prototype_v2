package com.example.prototype_v2.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.prototype_v2.R;
import com.example.prototype_v2.entities.Restaurant;
import com.example.prototype_v2.recyclerview.OnRestaurantListener;
import com.example.prototype_v2.recyclerview.RestaurantRecyclerViewAdapter;
import com.example.prototype_v2.service.DataService;

import java.util.List;

import static android.app.PendingIntent.getActivity;
import static com.example.prototype_v2.entities.Constants.VIEW_DETAILS_ACTIVITY_CODE;
import static com.example.prototype_v2.entities.Constants.VIEW_MENU_ACTIVITY_CODE;

public class ListingRestaurantActivity extends AppCompatActivity implements OnRestaurantListener {

    public List<Restaurant> restaurants;
    private RestaurantRecyclerViewAdapter adapter;
    private DataService restaurantDataService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_restaurant);

        RecyclerView restaurantRecyclerView = findViewById(R.id.restaurantRecyclerView);
//      set the layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);


        restaurantRecyclerView.setLayoutManager(linearLayoutManager);

        restaurantDataService = new DataService();
        restaurantDataService.init(this);


        //Load Data from the database
        restaurants = restaurantDataService.getRestaurants();
        //create adapter passing the data, and the context

        adapter = new RestaurantRecyclerViewAdapter(restaurants, this,  this);
        //attach the adapter to the Recyclerview

        restaurantRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onRestaurantClick(Restaurant restaurant) {
        showRestaurantDetails(restaurant);
    }

    @Override
    public void onMenuClick(Restaurant restaurant) {
        showMenuDetails(restaurant);
    }

    private void showRestaurantDetails(Restaurant restaurant){
        Intent restaurantDetails = new Intent(this, RestaurantDetailsScrollingActivity.class);
        restaurantDetails.putExtra(Restaurant.RESTAURANT_KEY, restaurant);

        startActivityForResult(restaurantDetails, VIEW_DETAILS_ACTIVITY_CODE);
    }

    private void showMenuDetails(Restaurant restaurant){
        Intent goToMenu = new Intent(this, MenuScrollingActivity.class);
        goToMenu.putExtra(Restaurant.RESTAURANT_KEY, restaurant);

        startActivityForResult(goToMenu, VIEW_MENU_ACTIVITY_CODE);
    }



}
