package com.etsn05group2.lampcontroller.network.testing;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.etsn05group2.lampcontroller.network.NetworkManager;
import com.etsn05group2.lampcontroller.network.NetworkManagerApi;
import com.etsn05group2.lampcontroller.network.data.DataAboutDevice;


import java.util.List;
import retrofit.RestAdapter;

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
        man.toggle(mac, value);
        List<DataAboutDevice> detectedDevices = man.detectDevices();
        //printList(detectedDevices);


    }
    void printList(List<DataAboutDevice> datas){
        for(DataAboutDevice data : datas){
            Log.d( "id ="," " +data.id);
        }
    }
}
