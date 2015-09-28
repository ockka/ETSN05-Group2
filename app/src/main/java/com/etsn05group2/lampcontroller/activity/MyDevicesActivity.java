package com.etsn05group2.lampcontroller.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import com.etsn05group2.lampcontroller.R;
import com.etsn05group2.lampcontroller.adapter.DeviceListAdapter;
import com.etsn05group2.lampcontroller.model.Device;
import com.etsn05group2.lampcontroller.network.data.DataAboutDevice;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MyDevicesActivity extends BaseActivity {
    private DeviceListAdapter listAdapter;

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
        manager.detectDevices(createCallback());

    }

    private void controlDevice(Device device){

    }

    private Callback<List<DataAboutDevice>> createCallback(){
        Callback<List<DataAboutDevice>> call = new Callback<List<DataAboutDevice>>() {
            @Override
            public void success(List<DataAboutDevice> dataAboutDevices, Response response) {
                Log.d("hämtar lista","");
                //fixa listor
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("NÅGOT GICK VÄLDIGT FEL","");
                // popup med att det inte funkade att hitta listor

            }
        };
        return call;
    }
}
