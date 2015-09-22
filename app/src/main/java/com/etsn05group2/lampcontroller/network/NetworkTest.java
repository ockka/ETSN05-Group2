package com.etsn05group2.lampcontroller.network;

import android.util.Log;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Niklas on 2015-09-21.
 */
public class NetworkTest {

    private DeviceDataApi api;
    DeviceData dd;

    public NetworkTest() {
        // retrofit is used to communicate with the server.
        RestAdapter retrofit = new RestAdapter.Builder().setEndpoint("http://vm39.cs.lth.se:9000/").build();
        api = retrofit.create(DeviceDataApi.class);
    }

    public DeviceData getDeviceData() {
        // Returns data from the first device it finds.
        api.getDeviceData(new Callback<List<DeviceData>>() {
            @Override
            public void success(List<DeviceData> deviceDatas, Response response) {
                DeviceData tmp = deviceDatas.get(0);
                Log.d("success", (tmp == null) ? "" : tmp.name);
                dd = tmp;
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("failure", error.toString());
            }
        });

        return dd;
    }
}
