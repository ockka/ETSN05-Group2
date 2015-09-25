package com.etsn05group2.lampcontroller.network.testing;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.etsn05group2.lampcontroller.network.NetworkManager;
import com.etsn05group2.lampcontroller.network.NetworkManagerApi;
import com.etsn05group2.lampcontroller.network.data.DataAboutDevice;
import com.etsn05group2.lampcontroller.network.data.DeviceStatus;


import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by carl on 2015-09-24.
 */
public class testActivity extends Activity{
    String PATH = "http://vm39.cs.lth.se:9000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetworkManager man = new NetworkManager();



        RestAdapter retrofit= new RestAdapter.Builder().setEndpoint(PATH).build();
        NetworkManagerApi api = retrofit.create(NetworkManagerApi.class);
        String mac = "90:59:AF:2A:BD:19";
        String value = "0";
        //man.toggle(mac, value);
        //List<DataAboutDevice> detectedDevices = man.detectDevices();
        //printList(detectedDevices);

        man.setColor(mac, "0000FF00", new Callback<DeviceStatus>() {
            @Override
            public void success(DeviceStatus deviceStatus, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("failure", error.toString());
            }
        });

        /*
        man.detectDevices(new Callback<List<DataAboutDevice>>() {
            @Override
            public void success(List<DataAboutDevice> dataAboutDevices, Response response) {
                printList(dataAboutDevices);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("failure", error.toString());
            }
        });
        */
    }
    void printList(List<DataAboutDevice> data){
        for (DataAboutDevice device : data) {
            Log.d("id", "id: " + device.id);
            Log.d("name", "name: " + device.name);
            Log.d("status", "status: " + device.status);
            Log.d("address", "address: " + device.deviceAddress);
            if (device.description != null)
                Log.d("description", "description: " + device.description);
            for (String sensor : device.sensors) {
                if (sensor != null)
                    Log.d("sensor", sensor);
            }
        }
    }
}
