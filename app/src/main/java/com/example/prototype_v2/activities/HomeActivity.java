package com.example.prototype_v2.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prototype_v2.MainActivity;
import com.example.prototype_v2.service.DataService;

import com.example.prototype_v2.R;
import com.example.prototype_v2.database.DatabaseHelper;
import com.example.prototype_v2.entities.Restaurant;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import static com.example.prototype_v2.entities.Constants.ADD_RESTAURANT_ACTIVITY_CODE;

public class HomeActivity extends AppCompatActivity {

    private DataService restaurantDataService;

    GoogleSignInClient mGoogleSignInClient;
    ImageButton restaurantList, uploadList;
    Button signOutBtn;
    TextView nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        nameText = findViewById(R.id.nameView);
        signOutBtn = findViewById(R.id.signOutBtn);
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

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(HomeActivity.this);
        if (acct != null) {
            String personName = acct.getDisplayName();

            nameText.setText(personName);

        }

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }

    private void signOut(){
        mGoogleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(HomeActivity.this, "Successfully signed out!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                finish();
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
