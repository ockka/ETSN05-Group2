package com.etsn05group2.lampcontroller.network.testing;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.etsn05group2.lampcontroller.network.NetworkManager;
import com.etsn05group2.lampcontroller.network.NetworkManagerApi;
import com.etsn05group2.lampcontroller.network.data.DataAboutDevice;
import com.etsn05group2.lampcontroller.network.data.DeviceData;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by carl on 2015-09-24.
 */
public class testActivity extends Activity{
    String PATH = "http://vm39.cs.lth.se:9000/";

    private static Map<String, String> sensorValues = new HashMap<String, String>();

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

        man.getAllSensorValues(34L, new Callback<List<DeviceData>>() {
            @Override
            public void success(List<DeviceData> deviceDataList, Response response) {
                int count = 0; // Not necessary.
                for (int i = deviceDataList.size() - 1; i >= 0; i--) {
                    DeviceData data = deviceDataList.get(i);
                    String sensor = data.sensorType;    // The name of the sensor, e.g. "temperature"
                    if (!sensorValues.containsKey(sensor)) {
                        sensorValues.put(sensor, data.value);   // Will associate e.g. "temperature" with "25.7"
                        count++;
                    }
                    if (count == 6) break;  // To avoid unnecessary loop iterations, not requred for the logic of the program.
                }
                Log.d("temperature", "temperature: " + sensorValues.get("temperature"));
                Log.d("pressure", "pressure: " + sensorValues.get("pressure"));
                Log.d("humidity", "humidity: " + sensorValues.get("humidity"));
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    void printList(List<DataAboutDevice> datas){
        for(DataAboutDevice data : datas){
            Log.d( "id ="," " +data.id);
        }
    }
}
