package com.dmitry.nytimes.db;

import android.arch.persistence.room.*;
import io.reactivex.Flowable;

import java.util.List;

@Dao
public interface TitleDao {

    @Insert
    void insert(TitleEntity titleEntity);

    @Update
    void update(TitleEntity titleEntity);

    @Delete
    void delete(TitleEntity titleEntity);

    @Query("SELECT * FROM TitleEntity")
    Flowable<List<TitleEntity>> getAllData();
}
