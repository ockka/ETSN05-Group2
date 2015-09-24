package com.etsn05group2.lampcontroller.network.testing;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import com.etsn05group2.lampcontroller.network.NetworkManager;
import com.etsn05group2.lampcontroller.network.NetworkManagerApi;
import com.etsn05group2.lampcontroller.network.data.DataAboutDevice;
import com.etsn05group2.lampcontroller.network.data.DeviceStatus;


import java.util.List;
import java.util.concurrent.Semaphore;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by carl on 2015-09-24.
 */
public class testActivity extends Activity{
    String PATH = "http://vm39.cs.lth.se:9000/";
    List<DataAboutDevice> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetworkManager man = new NetworkManager();


        final Callback<List<DataAboutDevice>> callback = new Callback<List<DataAboutDevice>>() {
            @Override
            public void success(List<DataAboutDevice> dataAboutDevices, Response response) {
                datas = dataAboutDevices;
                printList(datas);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        };


        RestAdapter retrofit= new RestAdapter.Builder().setEndpoint(PATH).build();
        NetworkManagerApi api = retrofit.create(NetworkManagerApi.class);
        //api.getDataAboutAllDevices(callback);
        //DeviceStatus status = new DeviceStatus("90:59:AF:2A:BD:19","0");
        //api.putDeviceStatus(status,respons);
        String mac = "90:59:AF:2A:BD:19";
        String value = "0";
        man.toggle(mac, value);

    }

    void printList(List<DataAboutDevice> datas){

        for(DataAboutDevice data : datas){
            Log.d( "id ="," " +data.id);
        }
    }
}
