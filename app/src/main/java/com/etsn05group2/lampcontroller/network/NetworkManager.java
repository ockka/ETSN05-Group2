package com.etsn05group2.lampcontroller.network;

import android.provider.ContactsContract;
import android.util.Log;

import com.etsn05group2.lampcontroller.network.data.DataAboutDevice;
import com.etsn05group2.lampcontroller.network.data.DeviceStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

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
    List<DataAboutDevice> detectedDevices = new ArrayList<DataAboutDevice>();

    public NetworkManager() {
        RestAdapter retrofit = new RestAdapter.Builder().setEndpoint(PATH).build();
        api = retrofit.create(NetworkManagerApi.class);

    }

    public List<DataAboutDevice> detectDevices() {
        api.getDataAboutAllDevices(new Callback<List<DataAboutDevice>>() {


            @Override
            public void success(List<DataAboutDevice> dataAboutDevices, Response response) {
                detectedDevices = new ArrayList<DataAboutDevice>(dataAboutDevices);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("failure", error.toString());
            }
        });


        return detectedDevices;
    }

    public static void toggle(String mac, String value){
        deviceStatus = new DeviceStatus(mac, value);
        api.putDeviceStatus(deviceStatus, toggleCallback() );
    }




    static private Callback<DeviceStatus> toggleCallback(){
        Callback<DeviceStatus> toggleCall = new Callback<DeviceStatus>() {
            @Override
            public void success(DeviceStatus deviceStatus, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {

            }
        };
                return toggleCall;
    }

}
