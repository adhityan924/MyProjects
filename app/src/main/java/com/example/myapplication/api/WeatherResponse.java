package com.example.myapplication.api;

import com.google.gson.annotations.SerializedName;

public class WeatherResponse {
    @SerializedName("main")
    public Main main;
    
    @SerializedName("wind")
    public Wind wind;
    
    @SerializedName("weather")
    public Weather[] weather;

    public static class Main {
        @SerializedName("temp")
        public double temperature;
        
        @SerializedName("humidity")
        public int humidity;
    }

    public static class Wind {
        @SerializedName("speed")
        public double speed;
    }

    public static class Weather {
        @SerializedName("description")
        public String description;
    }
} 