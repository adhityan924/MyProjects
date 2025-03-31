package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.api.MarketApi;
import com.example.myapplication.api.MarketPricesResponse;
import com.example.myapplication.api.WeatherApi;
import com.example.myapplication.api.WeatherResponse;
import com.example.myapplication.data.AppDatabase;
import com.example.myapplication.data.Crop;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView weatherText;
    private TextView marketPricesText;
    private MaterialButton addCropButton;
    private MaterialButton contactExpertButton;
    private BottomNavigationView bottomNavigation;
    private AppDatabase database;
    private CropViewModel cropViewModel;

    private static final String WEATHER_API_KEY = "4c34b21f1f9f4f3b9f9f9f9f9f9f9f9f"; // OpenWeatherMap API key
    private static final String WEATHER_BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private static final String MARKET_BASE_URL = "https://api.example.com/"; // Replace with your market API URL

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        weatherText = findViewById(R.id.weatherText);
        marketPricesText = findViewById(R.id.marketPricesText);
        addCropButton = findViewById(R.id.addCropButton);
        contactExpertButton = findViewById(R.id.contactExpertButton);
        bottomNavigation = findViewById(R.id.bottomNavigation);

        // Initialize database
        database = AppDatabase.getDatabase(this);
        cropViewModel = new ViewModelProvider(this).get(CropViewModel.class);

        // Set up toolbar
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);

        // Set up button click listeners
        addCropButton.setOnClickListener(v -> showAddCropDialog());

        contactExpertButton.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Contact Expert clicked", Toast.LENGTH_SHORT).show();
            // TODO: Implement expert contact functionality
        });

        // Set up bottom navigation
        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                return true;
            } else if (itemId == R.id.navigation_crops) {
                Toast.makeText(MainActivity.this, "Crops section clicked", Toast.LENGTH_SHORT).show();
                // TODO: Navigate to crops section
            } else if (itemId == R.id.navigation_market) {
                Toast.makeText(MainActivity.this, "Market section clicked", Toast.LENGTH_SHORT).show();
                // TODO: Navigate to market section
            } else if (itemId == R.id.navigation_profile) {
                Toast.makeText(MainActivity.this, "Profile section clicked", Toast.LENGTH_SHORT).show();
                // TODO: Navigate to profile section
            }
            return true;
        });

        // Load initial data
        loadWeatherData();
        loadMarketPrices();
    }

    private void loadWeatherData() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(WEATHER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        Call<WeatherResponse> call = weatherApi.getWeather("Mumbai", WEATHER_API_KEY, "metric");

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    WeatherResponse weather = response.body();
                    String weatherText = String.format("Temperature: %.1f°C\nHumidity: %d%%\nWind: %.1f km/h\nForecast: %s",
                        weather.main.temperature,
                        weather.main.humidity,
                        weather.wind.speed,
                        weather.weather[0].description);
                    MainActivity.this.weatherText.setText(weatherText);
                } else {
                    // Show sample data if API call fails
                    MainActivity.this.weatherText.setText("Temperature: 25°C\nHumidity: 65%\nWind: 12 km/h\nForecast: Sunny");
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                // Show sample data if API call fails
                weatherText.setText("Temperature: 25°C\nHumidity: 65%\nWind: 12 km/h\nForecast: Sunny");
            }
        });
    }

    private void loadMarketPrices() {
        // Since we don't have a real market API, show sample data
        String samplePrices = "Wheat: ₹2,500/ton\nRice: ₹3,200/ton\nCotton: ₹6,500/ton";
        marketPricesText.setText(samplePrices);
    }

    private void showAddCropDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_crop, null);
        
        TextInputEditText nameInput = dialogView.findViewById(R.id.cropNameInput);
        TextInputEditText varietyInput = dialogView.findViewById(R.id.cropVarietyInput);
        TextInputEditText areaInput = dialogView.findViewById(R.id.cropAreaInput);
        TextInputEditText notesInput = dialogView.findViewById(R.id.cropNotesInput);

        new MaterialAlertDialogBuilder(this)
            .setTitle("Add New Crop")
            .setView(dialogView)
            .setPositiveButton("Add", (dialog, which) -> {
                String name = nameInput.getText().toString();
                String variety = varietyInput.getText().toString();
                String areaStr = areaInput.getText().toString();
                String notes = notesInput.getText().toString();

                if (!name.isEmpty() && !variety.isEmpty() && !areaStr.isEmpty()) {
                    try {
                        double area = Double.parseDouble(areaStr);
                        Crop crop = new Crop(name, variety, "Today", area, "Planted", notes);
                        cropViewModel.insert(crop);
                        Toast.makeText(this, "Crop added successfully", Toast.LENGTH_SHORT).show();
                    } catch (NumberFormatException e) {
                        Toast.makeText(this, "Please enter a valid area", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
                }
            })
            .setNegativeButton("Cancel", null)
            .show();
    }
}
