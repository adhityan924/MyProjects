package com.example.myapplication.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "crops")
public class Crop {
    @PrimaryKey(autoGenerate = true)
    public int id;
    
    public String name;
    public String variety;
    public String plantingDate;
    public double area;
    public String status;
    public String notes;

    public Crop(String name, String variety, String plantingDate, double area, String status, String notes) {
        this.name = name;
        this.variety = variety;
        this.plantingDate = plantingDate;
        this.area = area;
        this.status = status;
        this.notes = notes;
    }
} 