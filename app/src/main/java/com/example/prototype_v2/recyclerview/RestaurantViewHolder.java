package com.example.prototype_v2.recyclerview;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prototype_v2.R;
import com.example.prototype_v2.activities.ListingRestaurantActivity;
import com.example.prototype_v2.activities.MenuScrollingActivity;
import com.example.prototype_v2.entities.Restaurant;

import static androidx.core.content.ContextCompat.startActivity;

public class RestaurantViewHolder extends RecyclerView.ViewHolder {

    public final ImageView restaurantImageView;
    public final TextView restaurantNameTextView;
    public final TextView restaurantAddressTextView;
    public Button reserveBtn, menuBtn;

    private OnRestaurantListener onRestaurantListener;

    public RestaurantViewHolder(@NonNull View itemView, OnRestaurantListener onRestaurantListener) {
        super(itemView);

        restaurantImageView = itemView.findViewById(R.id.restaurantImageView);
        restaurantNameTextView = itemView.findViewById(R.id.restaurantNameTextView);
        restaurantAddressTextView = itemView.findViewById(R.id.restaurantAddressTextView);
        reserveBtn = itemView.findViewById(R.id.reserveBtn);
        menuBtn = itemView.findViewById(R.id.menuBtn);

        this.onRestaurantListener = onRestaurantListener;
    }

    public void updateRestaurant(Restaurant restaurant){
//        Difference between res and assets:
//        https://www.concretepage.com/questions/413
//        Put images in drawable folder not assets folder. Assets folder is used to keep other types of file like pdf, js, txt, etc, preferable zipped


//        This is the reason why is better to use xml instead of png files
//        https://medium.com/the-android-guy/stop-using-pngs-use-vector-drawable-why-8ca68bed5335

//        Understanding vector image format: vector drawable
//        https://medium.com/androiddevelopers/understanding-androids-vector-image-format-vectordrawable-ab09e41d5c68
/*
        View rootView = restaurantImageView.getRootView();
        int resID = rootView.getResources().getIdentifier(restaurant.imageFileName , "drawable" , rootView.getContext().getPackageName()) ;
        restaurantImageView.setImageResource(resID);

 */
        this.restaurantNameTextView.setText(restaurant.getName());
        this.restaurantAddressTextView.setText(restaurant.getAddress());
    }



    public void bind(final Restaurant restaurant, final OnRestaurantListener onRestaurantListener){
        this.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("booo", "click using the interface");
                onRestaurantListener.onRestaurantClick(restaurant);
            }
        });

        this.reserveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRestaurantListener.onRestaurantClick(restaurant);
            }
        });

        this.menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRestaurantListener.onMenuClick((restaurant));
            }
        });

    }

}
