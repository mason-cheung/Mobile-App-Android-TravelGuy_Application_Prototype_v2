package com.example.prototype_v2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.prototype_v2.entities.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = DatabaseHelper.class.getName();

    private static DatabaseHelper mInstance = null;
    private Context context;

    //create database constants
    private static final String DATABASE_NAME = "restaurantList.db";
    private static final Integer DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "restaurant_table";

    //create constants for the table's column name
    private static final String COL_ID = "ID";
    private static final String COL_NAME = "NAME";
    private static final String COL_PHONE = "PHONE";
    private static final String COL_ADDRESS = "ADDRESS";
    private static final String COL_COMMENT = "COMMENT";

    private static final String CREATE_TABLE_ST = "CREATE TABLE " + TABLE_NAME + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NAME + " TEXT, " +
            COL_PHONE + " INTEGER, " +
            COL_ADDRESS + " TEXT, " +
            COL_COMMENT + " TEXT )";

    private static final String DROP_TABLE_ST = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String GET_ALL_ST = "SELECT * FROM " + TABLE_NAME;
    private static final String GET_RESTAURANT_BY_ID = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID + "= ?";

    public static synchronized DatabaseHelper getInstance(Context ctx) {
        if (mInstance == null) {
            mInstance = new DatabaseHelper(ctx.getApplicationContext());
        }
        return mInstance;
    }

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_ST);
        onCreate(db);
    }

    public boolean insertRestaurant(String name, String phone, String address, String comment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_PHONE, phone);
        contentValues.put(COL_ADDRESS, address);
        contentValues.put(COL_COMMENT, comment);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;


    }

    private Cursor getAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery(GET_ALL_ST, null);
    }

    public List<Restaurant> getAllRestaurant() {
        List<Restaurant> restaurants = new ArrayList<>();
        Cursor cursor = getAll();

        if(cursor.getCount() >= 0) {
            Restaurant restaurant;
            while (cursor.moveToNext()) {
                Long id = cursor.getLong(0);
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                String address = cursor.getString(3);
                String comment = cursor.getString(4);

                restaurant = new Restaurant(id, name, phone, address, comment);
                restaurants.add(restaurant);
            }
        }
        cursor.close();
        return restaurants;
    }


    public Restaurant getRestaurant(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Restaurant restaurant = null;
        Cursor cursor = db.rawQuery(GET_RESTAURANT_BY_ID, new String[]{id.toString()});

        if(cursor.getCount() > 0 ){
            while (cursor.moveToNext()){
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                String address = cursor.getString(3);
                String comment = cursor.getString(4);

                restaurant = new Restaurant(id, name, phone,  address, comment);
            }
        }
        cursor.close();
        return restaurant;
    }
}
