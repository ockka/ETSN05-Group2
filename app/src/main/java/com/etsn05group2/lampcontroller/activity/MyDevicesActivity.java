package com.etsn05group2.lampcontroller.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.etsn05group2.lampcontroller.R;
import com.etsn05group2.lampcontroller.adapter.DeviceListAdapter;
import com.etsn05group2.lampcontroller.model.Device;
import com.etsn05group2.lampcontroller.model.LightBulb;
import com.etsn05group2.lampcontroller.model.SensorDevice;
import com.etsn05group2.lampcontroller.network.NetworkManager;
import com.etsn05group2.lampcontroller.network.data.DataAboutDevice;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MyDevicesActivity extends Activity {
    private DeviceListAdapter listAdapter;
    private List<Device> devices;
    private Device chosen;
    private Toast toast;
    private Context context;
    private int duration;

    public MyDevicesActivity(){
        devices =new ArrayList<Device>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_devices);

        context = getApplicationContext();
        duration = Toast.LENGTH_SHORT;
        toast = Toast.makeText(context, "", duration);
    }

    private void detectDevices() {
        chosen=null;
        NetworkManager.detectDevices(createCallback());
    }

    public void controlDevice(View view){
        controlDevice(chosen);
    }

    private void controlDevice(Device deviceToBeControlled){
        if(deviceToBeControlled != null){
            if(deviceToBeControlled.getName().equals("Light Bulb")){
                //skicka med devicen
                Intent intent = new Intent(this, LightBulbActivity.class);
                String[] s = {deviceToBeControlled.getName(), deviceToBeControlled.getMacAddress(),String.valueOf(deviceToBeControlled.getId())};
                intent.putExtra("deviceInfo",s);
                startActivity(intent);
            }else if(deviceToBeControlled.getName().equals("Sensor")){
                //skicka med devicen
                Intent intent = new Intent(this, SensorDeviceActivity.class);
                String[] s = {deviceToBeControlled.getName(), deviceToBeControlled.getMacAddress(),String.valueOf(deviceToBeControlled.getId())};
                intent.putExtra("deviceInfo",s);
                startActivity(intent);
            }
        }else{
            toast.setText("Please select a device");
            toast.show();
        }

    }

    private Callback<List<DataAboutDevice>> createCallback(){
        Callback<List<DataAboutDevice>> call = new Callback<List<DataAboutDevice>>() {
            @Override
            public void success(List<DataAboutDevice> dataAboutDevices, Response response) {
                saveList(dataAboutDevices);

                final ListView listView = (ListView) findViewById(R.id.listView);

                DeviceListAdapter customAdapter = new DeviceListAdapter(context, R.layout.itemlistrow, devices);
                listView.setAdapter(customAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        chosen = (Device) listView.getItemAtPosition(position);
                    }
                });

            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("NÅGOT GICK VÄLDIGT FEL","");
                toast.setText("Could not find any devices");
                toast.show();
                // popup med att det inte funkade att hitta listor

            }
        };
        return call;
    }
    private void saveList(List<DataAboutDevice> list){

        for(DataAboutDevice d: list){
            if(d.name.equals("Nexturn") && d.deviceAddress.equals("90:59:AF:2A:BD:19") && devices.size() < 2){
                devices.add(new LightBulb(d.deviceAddress, d.id));
            }
            if(d.name.equals("WICED Sense Kit") && d.deviceAddress.equals("00:10:18:01:23:3A") && devices.size() < 2){
                devices.add(new SensorDevice(d.deviceAddress, d.id));
            }
        }
    }

    public void getDevices(View v){
        detectDevices();
    }
}
