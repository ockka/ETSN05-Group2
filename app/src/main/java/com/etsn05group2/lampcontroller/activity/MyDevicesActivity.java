package com.etsn05group2.lampcontroller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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


public class MyDevicesActivity extends BaseActivity {
    private DeviceListAdapter listAdapter;
    List<Device> devices;
    Device chosen;

    public MyDevicesActivity(){
        devices =new ArrayList<Device>();
        Device d = new LightBulb("90:59:AF:2A:BD:19",24); //tror MACen stämmer
        devices.add(d);
        Device dd = new SensorDevice("2A:3B:4C:5D:6E:7F",3); //Påhittad MAC
        devices.add(dd);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_devices);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_devices, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void detectDevices(){
        //NetworkManager.detectDevices(createCallback());

        final ListView listView = (ListView) findViewById(R.id.listView);

        DeviceListAdapter customAdapter = new DeviceListAdapter(this, R.layout.itemlistrow, devices);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                chosen = (Device) listView.getItemAtPosition(position);
            }
        });

    }

    public void controlDevice(View view){
        controlDevice(chosen);
    }

    private void controlDevice(Device deviceToBeControlled){
        if(deviceToBeControlled != null){
            if(deviceToBeControlled.getName().equals("LightBulb")){
                //skicka med devicen
                Intent intent = new Intent(this, LightBulbActivity.class);
                String[] s = {deviceToBeControlled.getName(), deviceToBeControlled.getMacAddress(),String.valueOf(deviceToBeControlled.getId())};
                intent.putExtra("deviceInfo",s);
                startActivity(intent);
            }else if(deviceToBeControlled.getName().equals("SensorDevice")){
                //skicka med devicen
                Intent intent = new Intent(this, SensorDeviceActivity.class);
                String[] s = {deviceToBeControlled.getName(), deviceToBeControlled.getMacAddress(),String.valueOf(deviceToBeControlled.getId())};
                intent.putExtra("deviceInfo",s);
                startActivity(intent);
            }
        }

    }

    private Callback<List<DataAboutDevice>> createCallback(){
        Callback<List<DataAboutDevice>> call = new Callback<List<DataAboutDevice>>() {
            @Override
            public void success(List<DataAboutDevice> dataAboutDevices, Response response) {
                Log.d("hämtar lista","");
                saveList(dataAboutDevices);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("NÅGOT GICK VÄLDIGT FEL","");
                // popup med att det inte funkade att hitta listor

            }
        };
        return call;
    }
    private void saveList(List<DataAboutDevice> list){
        devices = new ArrayList<Device>();
        for(DataAboutDevice d: list){
            if(d.name == "Nexturn"){
                devices.add(new LightBulb(d.deviceAddress, d.id));
            }
            if(d.description == "WICED sense kit"){
                devices.add(new SensorDevice(d.deviceAddress, d.id));
            }
        }
        return;
    }

    public void getDevices(View v){
        detectDevices();
    }
}
