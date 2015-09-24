package com.etsn05group2.lampcontroller.network;

import android.graphics.Color;
import android.telecom.Call;

import com.etsn05group2.lampcontroller.network.data.DataAboutDevice;
import com.etsn05group2.lampcontroller.network.data.DeviceData;
import com.etsn05group2.lampcontroller.network.data.changeStatus;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Part;
import retrofit.http.Path;

/**
 * Created by Niklas on 2015-09-22.
 */
public interface NetworkManagerApi {

    @GET("/device")
    void getDataAboutAllDevices(Callback<List<DataAboutDevice>> callback);

    @GET("/data/device/{id}")
    void getDeviceData(@Path("id") int deviceId, Callback<List<DeviceData>> callback);

    @GET("/data/device/{id}/{sensorType}")
    void getDeviceData(@Path("id") int deviceId, @Path("sensorType") String sensorType, Callback<List<DeviceData>> callback);

    @Multipart
    @PUT("/device/status")
    void putDeviceStatus(@Part("deviceAddress") String deviceAddress, @Part("value") String value, Callback<String> callback);

    //@Multipart
    @POST("/device/status")
    void putDeviceStatus(changeStatus status, Callback<String> callback);


}
