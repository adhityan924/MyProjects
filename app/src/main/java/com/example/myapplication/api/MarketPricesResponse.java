package com.example.myapplication.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MarketPricesResponse {
    @SerializedName("prices")
    public List<CropPrice> prices;

    public static class CropPrice {
        @SerializedName("crop")
        public String crop;
        
        @SerializedName("price")
        public double price;
        
        @SerializedName("unit")
        public String unit;
    }
} 