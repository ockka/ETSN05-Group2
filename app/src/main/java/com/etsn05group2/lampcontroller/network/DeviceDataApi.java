package com.etsn05group2.lampcontroller.network;

import com.etsn05group2.lampcontroller.example.WeatherObj;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Niklas on 2015-09-21.
 */
public interface DeviceDataApi {

    @GET("/device")
    void getDeviceData(Callback<List<DeviceData>> callback);
}

