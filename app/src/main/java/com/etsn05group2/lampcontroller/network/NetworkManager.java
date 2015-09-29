package com.etsn05group2.lampcontroller.network;

import android.util.Log;

import com.etsn05group2.lampcontroller.model.Device;
import com.etsn05group2.lampcontroller.network.data.DataAboutDevice;
import com.etsn05group2.lampcontroller.network.data.DeviceData;
import com.etsn05group2.lampcontroller.network.data.DeviceStatus;
import com.etsn05group2.lampcontroller.network.data.ToggledStateResponse;

import java.util.ArrayList;
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
    private static NetworkManagerApi api = new RestAdapter.Builder().setEndpoint(PATH).build().create(NetworkManagerApi.class);
    private static DeviceStatus deviceStatus;

    // Holds data about all the detected devices.
    private static List<DataAboutDevice> detectedDevices;
    static String color= "000000ff";

    private List<DeviceData> deviceData = new ArrayList<DeviceData>();

    public NetworkManager() {
    }

    public static List<DataAboutDevice> detectDevices() {
        api.getDataAboutAllDevices(allDevicesCall());
        return detectedDevices;
    }

    public static void toggle(Device device, boolean value, Callback<DeviceStatus> callback) {
        api.putDeviceStatus(new DeviceStatus(device.getMacAddress(), (value ? "1" : "0")), callback);
        setColor(device, color, colorCall());
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
        NetworkManager.color = color;
        api.putDeviceValue(new DeviceStatus(device.getMacAddress(), color), callback);
    }

    /**
     * Placera in all Fulkod här under
     *
     *
     *
     *
     */

    static private Callback<DeviceStatus> colorCall(){
        return new Callback<DeviceStatus>() {
            @Override
            public void success(DeviceStatus device, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {

            }
        };
    }

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
