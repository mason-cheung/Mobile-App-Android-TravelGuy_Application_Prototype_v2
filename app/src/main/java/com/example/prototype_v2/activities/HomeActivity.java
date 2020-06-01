package com.example.prototype_v2.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.example.prototype_v2.service.DataService;

import com.example.prototype_v2.R;
import com.example.prototype_v2.database.DatabaseHelper;
import com.example.prototype_v2.entities.Restaurant;

import static com.example.prototype_v2.entities.Constants.ADD_RESTAURANT_ACTIVITY_CODE;

public class HomeActivity extends AppCompatActivity {

    private DataService restaurantDataService;

    ImageButton restaurantList, uploadList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        restaurantList = findViewById(R.id.restaurantListBtn);
        restaurantList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToList = new Intent(HomeActivity.this, ListingRestaurantActivity.class);
                startActivity(goToList);
            }
        });

        uploadList = findViewById(R.id.uploadRestBtn);
        uploadList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToInsert = new Intent(HomeActivity.this, InsertRestaurantScrollingActivity.class);
                startActivityForResult(goToInsert, ADD_RESTAURANT_ACTIVITY_CODE);
            }
        });
    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_RESTAURANT_ACTIVITY_CODE){
            if(resultCode == RESULT_OK){
                addRestaurant(data);
            }
        }
    }

    private void addRestaurant(Intent data) {
        Restaurant restaurant = (Restaurant) data.getSerializableExtra(Restaurant.RESTAURANT_KEY);
        restaurantDataService.add(restaurant);
    }
*/

}
