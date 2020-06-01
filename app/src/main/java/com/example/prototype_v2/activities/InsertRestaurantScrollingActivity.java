package com.example.prototype_v2.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.prototype_v2.database.DatabaseHelper;
import com.example.prototype_v2.entities.Restaurant;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prototype_v2.R;

public class InsertRestaurantScrollingActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    private EditText editRestName, editAddress, editPhone, editComment;
    private Button submitBtn, cancelBtn;
    private Restaurant restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_restaurant_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDb = new DatabaseHelper(this);

        editRestName = (EditText)findViewById(R.id.restNameText);
        editAddress = (EditText)findViewById(R.id.phoneText);
        editPhone = (EditText)findViewById(R.id.addressText);
        editComment = (EditText)findViewById(R.id.commentText);
        submitBtn = (Button)findViewById(R.id.submitBtn);
        cancelBtn = (Button)findViewById(R.id.cancelBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewRestaurant(v);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });


    }

    private void addNewRestaurant(View v) {
        String name = editRestName.getText().toString();
        String phone = editPhone.getText().toString();
        String address = editAddress.getText().toString();
        String comment = editComment.getText().toString();
        if(name.trim().isEmpty()){
            Snackbar.make(v, "Name is required", Snackbar.LENGTH_SHORT).show();
            editRestName.getText().clear();
            editRestName.requestFocus();
            return;
        }
        else if(phone.trim().isEmpty()){
            Snackbar.make(v, "Phone number is required", Snackbar.LENGTH_SHORT).show();
            editPhone.getText().clear();
            editPhone.requestFocus();
            return;
        }
        else if(address.trim().isEmpty()){
            Snackbar.make(v, "Restaurant address is required", Snackbar.LENGTH_SHORT).show();
            editAddress.getText().clear();
            editAddress.requestFocus();
            return;
        }
        else if(comment.trim().isEmpty()){
            Snackbar.make(v, "Restaurant address is required", Snackbar.LENGTH_SHORT).show();
            editComment.getText().clear();
            editComment.requestFocus();
            return;
        }
        else{
            boolean isInserted = myDb.insertRestaurant(editRestName.getText().toString(),
                    editPhone.getText().toString(),
                    editAddress.getText().toString(),
                    editComment.getText().toString());
            if(isInserted = true){
                Toast.makeText(InsertRestaurantScrollingActivity.this, "Restaurant inserted!", Toast.LENGTH_LONG).show();
                Intent goingBack = new Intent();
                goingBack.putExtra(Restaurant.RESTAURANT_KEY, restaurant);
                setResult(RESULT_OK);
                finish();
            }
            else{
                Toast.makeText(InsertRestaurantScrollingActivity.this, "Insert restaurant error!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void cancel(){
        setResult(RESULT_CANCELED);
        finish();
    }
}
