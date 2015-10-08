package com.etsn05group2.lampcontroller.network;

import android.util.Log;

import com.etsn05group2.lampcontroller.model.Device;
import com.etsn05group2.lampcontroller.network.data.DataAboutDevice;
import com.etsn05group2.lampcontroller.network.data.DeviceData;
import com.etsn05group2.lampcontroller.network.data.DeviceStatus;
import com.squareup.okhttp.OkHttpClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

import java.util.Calendar;

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

    /* All methods are static, we dont need to create instances of this class. */
    private NetworkManager() {
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
        String startDate = previousTime();
        String endDate = currentTime();
        api.getDeviceDataSensor(device.getId(), "temperature", startDate, endDate, callback);
    }

    public static void getPressure(Device device, Callback<List<DeviceData>> callback) {
        String startDate = previousTime();
        String endDate = currentTime();
        api.getDeviceDataSensor(device.getId(), "pressure", startDate, endDate, callback);
    }

    public static void getHumidity(Device device, Callback<List<DeviceData>> callback) {
        String startDate = previousTime();
        String endDate = currentTime();
        api.getDeviceDataSensor(device.getId(), "humidity", startDate, endDate, callback);
    }

    public static void getMagnetic(Device device, Callback<List<DeviceData>> callback) {
        String startDate = previousTime();
        String endDate = currentTime();
        api.getDeviceDataSensor(device.getId(), "magnometer", startDate, endDate, callback);
    }

    public static void getAccelerometer(Device device, Callback<List<DeviceData>> callback) {
        String startDate = previousTime();
        String endDate = currentTime();
        api.getDeviceDataSensor(device.getId(), "accelerometer", startDate, endDate, callback);
    }

    public static void getGyroscopic(Device device, Callback<List<DeviceData>> callback) {
        String startDate = previousTime();
        String endDate = currentTime();
        api.getDeviceDataSensor(device.getId(), "gyroscope", startDate, endDate, callback);   // is this right/
    }

    public static void getAllSensorValues(Device device, Callback<List<DeviceData>> callback) {
        String startDate = previousTime();
        String endDate = currentTime();
        api.getDeviceData(device.getId(), startDate, endDate, callback);
    }

    public static void getColor(Device device, Callback<List<DeviceData>> callback) {
        api.getDeviceDataColor(device.getId(), callback);
    }

    public static void setColor(Device device, String color, Callback<DeviceStatus> callback) {
        api.putDeviceValue(new DeviceStatus(device.getMacAddress(), color), callback);
    }

    static private String currentTime() {
        Calendar cal = Calendar.getInstance();
        Date current = cal.getTime();
        String newString = "";
        try {
            newString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(current);
        } catch (Exception e) {
            Log.d("Exception", e.getMessage());
        }
        return newString;
    }

    static private String previousTime() {
        int minutesBack = 10;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, -minutesBack);
        Date tenMinutesBack = cal.getTime();
        String newString = "";
        try {
            newString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tenMinutesBack);
        } catch (Exception e) {
            Log.d("Exception", e.getMessage());
        }
        return newString;
    }
}
