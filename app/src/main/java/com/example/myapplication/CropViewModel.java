package com.example.myapplication;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.myapplication.data.AppDatabase;
import com.example.myapplication.data.Crop;
import java.util.List;

public class CropViewModel extends AndroidViewModel {
    private AppDatabase database;
    private LiveData<List<Crop>> allCrops;

    public CropViewModel(Application application) {
        super(application);
        database = AppDatabase.getDatabase(application);
        allCrops = database.cropDao().getAllCrops();
    }

    public LiveData<List<Crop>> getAllCrops() {
        return allCrops;
    }

    public void insert(Crop crop) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            database.cropDao().insert(crop);
        });
    }

    public void update(Crop crop) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            database.cropDao().update(crop);
        });
    }

    public void delete(Crop crop) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            database.cropDao().delete(crop);
        });
    }
} 