package com.example.prototype_v2.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.prototype_v2.entities.Restaurant;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prototype_v2.R;

public class RestaurantDetailsScrollingActivity extends AppCompatActivity {

    Restaurant restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        ImageView restaurantImageView = findViewById(R.id.restaurantImageViewDetail);
        TextView restaurantNameTextView = findViewById(R.id.restaurantNameTextView);
        TextView restaurantAddressTextView = findViewById(R.id.restaurantAddressTextView);
        TextView restaurantPhoneTextView = findViewById(R.id.restaurantPhoneTextView);

        Intent intentThatCalled = getIntent();
        if(intentThatCalled.hasExtra(restaurant.RESTAURANT_KEY)){
            restaurant = (Restaurant) intentThatCalled.getSerializableExtra(Restaurant.RESTAURANT_KEY);
        }

        restaurantNameTextView.setText(restaurant.getName());
        restaurantAddressTextView.setText(restaurant.getAddress());
        restaurantPhoneTextView.setText(restaurant.getPhone());

/*
        View rootView = restaurantImageView.getRootView();
        int resID = rootView.getResources().getIdentifier(restaurant.imageFileName , "drawable" , rootView.getContext().getPackageName()) ;
        restaurantImageView.setImageResource(resID);

 */


    }

    @Override
    public void onBackPressed() {
        setIntentWithData();
        super.onBackPressed();
    }

    private void setIntentWithData() {
        Intent goingBack = new Intent();

        setResult(RESULT_OK, goingBack);
        finish();
    }
}
