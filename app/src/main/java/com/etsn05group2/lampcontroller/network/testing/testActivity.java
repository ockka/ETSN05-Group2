package com.etsn05group2.lampcontroller.network.testing;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import com.etsn05group2.lampcontroller.network.NetworkManager;
import com.etsn05group2.lampcontroller.network.NetworkManagerApi;
import com.etsn05group2.lampcontroller.network.data.DataAboutDevice;
import com.etsn05group2.lampcontroller.network.data.changeStatus;

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

        final Callback<String> callme = new Callback<String>() {
            @Override
            public void success(String string, Response response) {
                Log.d("callme = ", "" + string);
                Log.d("SHIT HAPPEND", "");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Failure", error.toString());

            }
        };



        RestAdapter retrofit= new RestAdapter.Builder().setEndpoint(PATH).build();
        NetworkManagerApi api = retrofit.create(NetworkManagerApi.class);
        //api.getDataAboutAllDevices(callback);
        //changeStatus status = new changeStatus("90:59:AF:2A:BD:19","1");
        api.putDeviceStatus("90:59:AF:2A:BD:19", "1",callme);
        //api.putDeviceStatus(status,callme);
        Log.d("----------------", "");

    }

    void printList(List<DataAboutDevice> datas){

        for(DataAboutDevice data : datas){
            Log.d( "id ="," " +data.id);
        }
    }
}
