package com.etsn05group2.lampcontroller.example;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Daniel on 2015-09-18.
 */
public interface LampApi {

    @GET("/data/2.5/weather")
    void getWeather(@Query("q") String location, Callback<WeatherObj> callback);
}
