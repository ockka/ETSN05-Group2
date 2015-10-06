package com.etsn05group2.lampcontroller.network;

import android.util.Log;

import com.etsn05group2.lampcontroller.model.Device;
import com.etsn05group2.lampcontroller.network.data.DataAboutDevice;
import com.etsn05group2.lampcontroller.network.data.DeviceData;
import com.etsn05group2.lampcontroller.network.data.DeviceStatus;
import com.etsn05group2.lampcontroller.network.data.ToggledStateResponse;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

/**
 * Created by Niklas on 2015-09-22.
 */
public class NetworkManager {

    private static final String PATH = "http://vm39.cs.lth.se:9000/";
    private static OkHttpClient client;
    private static NetworkManagerApi api;

    static {
        long timeout = 15L;
        client = new OkHttpClient();
        client.setReadTimeout(timeout, TimeUnit.SECONDS);
        client.setWriteTimeout(timeout, TimeUnit.SECONDS);
        client.setConnectTimeout(timeout, TimeUnit.SECONDS);
        api = new RestAdapter.Builder().setEndpoint(PATH).setClient(new OkClient(client)).build().create(NetworkManagerApi.class);
    }

    public NetworkManager() {
    }

    public static void toggle(Device device, boolean value, Callback<DeviceStatus> callback) {
        api.putDeviceStatus(new DeviceStatus(device.getMacAddress(), (value ? "1" : "0")), callback);
    }


    public static void getToggledState(Device device, Callback<DataAboutDevice> callback) {
        api.getDataAboutDevice(device.getId(), callback);
    }

    public static void detectDevices(Callback<List<DataAboutDevice>> callback) {
        api.getDataAboutAllDevices(callback);
    }

    public static void getTemperature(Device device, Callback<List<DeviceData>> callback) {
        api.getDeviceData(device.getId(), "temperature", callback);
    }

    public static void getPressure(Device device, Callback<List<DeviceData>> callback) {
        api.getDeviceData(device.getId(), "pressure", callback);
    }

    public static void getHumidity(Device device, Callback<List<DeviceData>> callback) {
        api.getDeviceData(device.getId(), "humidity", callback);
    }

    public static void getMagnetic(Device device, Callback<List<DeviceData>> callback) {
        api.getDeviceData(device.getId(), "magnometer", callback);
    }

    public static void getAccelerometer(Device device, Callback<List<DeviceData>> callback) {
        api.getDeviceData(device.getId(), "accelerometer", callback);
    }

    public static void getGyroscopic(Device device, Callback<List<DeviceData>> callback) {
        api.getDeviceData(device.getId(), "gyroscope", callback);   // is this right/
    }

    public static void getAllSensorValues(Device device, Callback<List<DeviceData>> callback) {
        api.getDeviceData(device.getId(), callback);
    }

    public static void getColor(Device device, Callback<List<DeviceData>> callback) {
        api.getDeviceData(device.getId(), callback);
    }

    public static void setColor(Device device, String color, Callback<DeviceStatus> callback) {
        api.putDeviceValue(new DeviceStatus(device.getMacAddress(), color), callback);
    }
}
