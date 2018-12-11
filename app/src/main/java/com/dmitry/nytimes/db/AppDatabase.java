package com.dmitry.nytimes.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = { TitleEntity.class }, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TitleDao titleDao();
}