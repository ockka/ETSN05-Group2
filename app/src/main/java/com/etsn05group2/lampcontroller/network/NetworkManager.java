package com.etsn05group2.lampcontroller.network;

import android.util.Log;

import com.etsn05group2.lampcontroller.network.data.DataAboutDevice;
import com.etsn05group2.lampcontroller.network.data.DeviceData;
import com.etsn05group2.lampcontroller.network.data.DeviceStatus;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Niklas on 2015-09-22.
 */
public class NetworkManager {

    private static final String PATH = "http://vm39.cs.lth.se:9000/";
    static private NetworkManagerApi api;
    static DeviceStatus deviceStatus;


    // Holds data about all the detected devices.
    static private List<DataAboutDevice> detectedDevices;

    public NetworkManager() {
        RestAdapter retrofit = new RestAdapter.Builder().setEndpoint(PATH).build();
        api = retrofit.create(NetworkManagerApi.class);

    }

    public static List<DataAboutDevice> detectDevices() {
        api.getDataAboutAllDevices(allDevicesCall());
        return detectedDevices;
    }

    public static void toggle(String mac, String value){
        deviceStatus = new DeviceStatus(mac, value);
        api.putDeviceStatus(deviceStatus, toggleCallback());
    }

    public void detectDevices(Callback<List<DataAboutDevice>> callback) {
        api.getDataAboutAllDevices(callback);
    }

    public void getTemperature(long id, Callback<List<DeviceData>> callback) {
        api.getDeviceData(id, "temperature", callback);
    }

    public void getPressure(long id, Callback<List<DeviceData>> callback) {
        api.getDeviceData(id, "pressure", callback);
    }

    public void getHumidity(long id, Callback<List<DeviceData>> callback) {
        api.getDeviceData(id, "humidity", callback);
    }

    public void getMagnetic(long id, Callback<List<DeviceData>> callback) {
        api.getDeviceData(id, "magnometer", callback);
    }

    public void getAccelerometer(long id, Callback<List<DeviceData>> callback) {
        api.getDeviceData(id, "accelerometer", callback);
    }

    public void getGyroscopic(long id, Callback<List<DeviceData>> callback) {
        api.getDeviceData(id, "gyroscope", callback);   // is this right/
    }

    public void getAllSensorValues(long id, Callback<List<DeviceData>> callback) {
        api.getDeviceData(id, callback);
    }

    public void getColor(long id, Callback<List<DeviceData>> callback) {
        api.getDeviceData(id, callback);
    }

    public void setColor(String address, String color) {
        api.putDeviceValue(new DeviceStatus(address, color), new Callback<DeviceStatus>() {
            @Override
            public void success(DeviceStatus deviceStatus, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    /**
     * Placera in all Fulkod här under
     *
     *
     *
     *
     */


    static private Callback<List<DataAboutDevice>> allDevicesCall(){
        Callback<List<DataAboutDevice>> call = new Callback<List<DataAboutDevice>>(){

            @Override
            public void success(List<DataAboutDevice> dataAboutDevices, Response response) {
                Log.d("DEN HÄMTAR SKIT", "");
                detectedDevices = dataAboutDevices;
            }

            @Override
            public void failure(RetrofitError error) { Log.d("failure", error.toString()); }
        };
        return call;
    }


    static private Callback<DeviceStatus> toggleCallback(){
        Callback<DeviceStatus> toggleCall = new Callback<DeviceStatus>() {
            @Override
            public void success(DeviceStatus deviceStatus, Response response) {}

            @Override
            public void failure(RetrofitError error) { Log.d("failure", error.toString()); }
        };
        return toggleCall;
    }

}
