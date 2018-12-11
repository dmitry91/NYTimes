package com.dmitry.nytimes.db;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.persistence.room.Room;

@SuppressLint("Registered")
public class App extends Application {

    public static App instance;

    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "NYTDatabase")
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}