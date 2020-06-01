package com.example.prototype_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.prototype_v2.activities.HomeActivity;
import com.example.prototype_v2.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity{
    DatabaseHelper myDb;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHome = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(goHome);
            }
        });

    }
}
