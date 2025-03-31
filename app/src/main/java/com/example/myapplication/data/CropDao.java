package com.example.myapplication.data;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import java.util.List;

@Dao
public interface CropDao {
    @Query("SELECT * FROM crops")
    LiveData<List<Crop>> getAllCrops();

    @Insert
    void insert(Crop crop);

    @Update
    void update(Crop crop);

    @Delete
    void delete(Crop crop);

    @Query("DELETE FROM crops")
    void deleteAll();
} 