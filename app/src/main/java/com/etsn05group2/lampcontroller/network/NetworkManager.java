package com.etsn05group2.lampcontroller.network;

import android.provider.ContactsContract;
import android.util.Log;

import com.etsn05group2.lampcontroller.network.data.DataAboutDevice;

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

    private NetworkManagerApi api;

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
}
