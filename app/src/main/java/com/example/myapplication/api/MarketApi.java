package com.example.myapplication.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MarketApi {
    @GET("market-prices")
    Call<MarketPricesResponse> getMarketPrices();
} 