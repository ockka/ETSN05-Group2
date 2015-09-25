package com.etsn05group2.lampcontroller.network;

import com.etsn05group2.lampcontroller.network.data.DataAboutDevice;
import com.etsn05group2.lampcontroller.network.data.DeviceData;
import com.etsn05group2.lampcontroller.network.data.DeviceStatus;


import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by Niklas on 2015-09-22.
 */
public interface NetworkManagerApi {

    @GET("/device")
    void getDataAboutAllDevices(Callback<List<DataAboutDevice>> callback);

    @GET("/data/device/{id}")
    void getDeviceData(@Path("id") long deviceId, Callback<List<DeviceData>> callback);

    @GET("/data/device/{id}/{sensorType}")
    void getDeviceData(@Path("id") long deviceId, @Path("sensorType") String sensorType, Callback<List<DeviceData>> callback);

    @PUT("/device/status")
    void putDeviceStatus(@Body DeviceStatus status, Callback<DeviceStatus> response);

    @PUT("/device/value")
    void putDeviceValue(@Body DeviceStatus status, Callback<DeviceStatus> callback);
}
