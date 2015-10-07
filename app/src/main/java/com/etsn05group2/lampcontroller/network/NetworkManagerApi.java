package com.etsn05group2.lampcontroller.network;

import com.etsn05group2.lampcontroller.model.Device;
import com.etsn05group2.lampcontroller.network.data.DataAboutDevice;
import com.etsn05group2.lampcontroller.network.data.DeviceData;
import com.etsn05group2.lampcontroller.network.data.DeviceDataList;
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

    @GET("/data/device/{id}/{startDate}/{endDate}")
    void getDeviceDataTimeLimit(@Path("id") long deviceId,@Path("startDate") String startDate, @Path("endDate") String endDate, Callback<List<DeviceData>> callback);

    @GET("/data/device/{id}")
    void getDeviceData(@Path("id") long deviceId, Callback<List<DeviceData>> callback);


    /**
     * Added for receiveing just 10 minutes of database input of sensor values
     */
    @GET("/data/device/{id}/{sensorType}/{startDate}/{endDate}")
    void getDeviceDataTimeLimit(@Path("id") long deviceId, @Path("sensorType") String sensorType, @Path("startDate") String startDate, @Path("endDate") String endDate,Callback<List<DeviceData>> callback);

    @PUT("/device/status")
    void putDeviceStatus(@Body DeviceStatus status, Callback<DeviceStatus> response);

    @PUT("/device/value")
    void putDeviceValue(@Body DeviceStatus status, Callback<DeviceStatus> callback);

    @GET("/device/{id}")
    void getDataAboutDevice(@Path("id") long deviceId, Callback<DataAboutDevice> callback);


}
