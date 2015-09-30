package com.etsn05group2.lampcontroller.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.etsn05group2.lampcontroller.R;
import com.etsn05group2.lampcontroller.network.NetworkManager;
import com.etsn05group2.lampcontroller.network.data.DataAboutDevice;
import com.etsn05group2.lampcontroller.network.data.DeviceData;
import com.etsn05group2.lampcontroller.network.data.DeviceStatus;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SensorDeviceActivity extends DeviceActivity {

    private Switch sensorSwitch;
    Context context;
    int duration;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_device);
        sensorSwitch = (Switch) findViewById(R.id.sensor_switch);
        final TextView deviceName = (TextView) findViewById(R.id.device_name);
        deviceName.setText(device.getName() + " " + device.getId());
        TextView macAddress = (TextView) findViewById(R.id.mac_address);
        macAddress.setText(device.getMacAddress());

        context = getApplicationContext();
        duration = Toast.LENGTH_SHORT;

    //Fixxade till lite/Carl
        NetworkManager.getToggledState(device, new Callback<DataAboutDevice>() {
            @Override
            public void success(DataAboutDevice dataAboutDevice, Response response) {
                //sensorSwitch.setChecked(deviceStatus.value());
                if(dataAboutDevice.status == 1){
                    sensorSwitch.setChecked(true);
                } else { sensorSwitch.setChecked(false);}

                toast = Toast.makeText(context, "Response received", duration);
                toast.show();
            }

            @Override
            public void failure(RetrofitError error) {
                sensorSwitch.setChecked(false);
                Log.d("failure", error.toString());
            }
        });

        sensorSwitch.setChecked(false);
        sensorSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
                NetworkManager.toggle(device, isChecked, new Callback<DeviceStatus>() {
                    @Override
                    public void success(DeviceStatus deviceStatus, Response response) {

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        sensorSwitch.setChecked(!isChecked);
                        Log.d("failure", error.toString());
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sensor_device, menu);
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

    public void getTemperature(View v) {
        NetworkManager.getTemperature(device, new Callback<List<DeviceData>>() {
            @Override
            public void success(List<DeviceData> deviceDatas, Response response) {
                DeviceData deviceData = deviceDatas.get(deviceDatas.size() - 1);
                TextView textView = (TextView) findViewById(R.id.temperature_value);
                textView.setText(deviceData.value.toString());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("failure", error.toString());
            }
        });

    }

    public void getPressure(View v) {
        NetworkManager.getPressure(device, new Callback<List<DeviceData>>() {
            @Override
            public void success(List<DeviceData> deviceDatas, Response response) {
                DeviceData deviceData = deviceDatas.get(deviceDatas.size() - 1);
                TextView textView = (TextView) findViewById(R.id.pressure_value);
                textView.setText(deviceData.value.toString());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("failure", error.toString());
            }
        });
    }

    public void getHumidity(View v) {
        NetworkManager.getHumidity(device, new Callback<List<DeviceData>>() {
            @Override
            public void success(List<DeviceData> deviceDatas, Response response) {
                DeviceData deviceData = deviceDatas.get(deviceDatas.size() - 1);
                TextView textView = (TextView) findViewById(R.id.humidity_value);
                textView.setText(deviceData.value.toString());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("failure", error.toString());
            }
        });
    }
    public void getMagnetic(View v) {
        NetworkManager.getMagnetic(device, new Callback<List<DeviceData>>() {
            @Override
            public void success(List<DeviceData> deviceDatas, Response response) {
                DeviceData deviceData = deviceDatas.get(deviceDatas.size() - 1);
                TextView textView = (TextView) findViewById(R.id.magnetic_value);
                textView.setText(deviceData.value.toString());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("failure", error.toString());
            }
        });
    }
    public void getGyroscopic(View v) {
        NetworkManager.getGyroscopic(device, new Callback<List<DeviceData>>() {
            @Override
            public void success(List<DeviceData> deviceDatas, Response response) {
                DeviceData deviceData = deviceDatas.get(deviceDatas.size() - 1);
                TextView textView = (TextView) findViewById(R.id.gyroscopic_value);
                textView.setText(deviceData.value.toString());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("failure", error.toString());
            }
        });
    }
    public void getAccelerometer(View v) {
        NetworkManager.getAccelerometer(device, new Callback<List<DeviceData>>() {
            @Override
            public void success(List<DeviceData> deviceDatas, Response response) {
                DeviceData deviceData = deviceDatas.get(deviceDatas.size() - 1);
                TextView textView = (TextView) findViewById(R.id.accelerometer_value);
                textView.setText(deviceData.value.toString());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("failure", error.toString());
            }
        });
    }
    //Fixxade till lite
    public void getAll(View v) {
        NetworkManager.getAllSensorValues(device, new Callback<List<DeviceData>>() {
            @Override
            public void success(List<DeviceData> deviceDatas, Response response) {

                toast = Toast.makeText(context, "Success", duration);
                toast.show();

                /* TODO: Get all sensor values */
                for(int i = 0;i<deviceDatas.size();i++){
                    DeviceData newDevice = deviceDatas.get(i);
                    if(newDevice.sensorType.equals("temperature")){ //Stor bokstav på sensor typerna?
                        TextView textView = (TextView) findViewById(R.id.temperature_value);
                        textView.setText(newDevice.value.toString());
                    }else if(newDevice.sensorType.equals("pressure")){
                        TextView textView = (TextView) findViewById(R.id.pressure_value);
                        textView.setText(newDevice.value.toString());
                    }else if(newDevice.sensorType.equals("humidity")){
                        TextView textView = (TextView) findViewById(R.id.humidity_value);
                        textView.setText(newDevice.value.toString());
                    }else if(newDevice.sensorType.equals("magnometer")){
                        TextView textView = (TextView) findViewById(R.id.magnetic_value);
                        textView.setText(newDevice.value.toString());
                    }else if(newDevice.sensorType.equals("gyroscope")){
                        TextView textView = (TextView) findViewById(R.id.gyroscopic_value);
                        textView.setText(newDevice.value.toString());
                    }else if(newDevice.sensorType.equals("accelerometer")){
                        TextView textView = (TextView) findViewById(R.id.accelerometer_value);
                        textView.setText(newDevice.value.toString());
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("failure", error.toString());

                toast = Toast.makeText(context, "Could not get Status", duration);
                toast.show();
            }
        });
    }

    public void clearAll(View v) {
        ((TextView) findViewById(R.id.temperature_value)).setText("");
        ((TextView) findViewById(R.id.pressure_value)).setText("");
        ((TextView) findViewById(R.id.humidity_value)).setText("");
        ((TextView) findViewById(R.id.magnetic_value)).setText("");
        ((TextView) findViewById(R.id.gyroscopic_value)).setText("");
        ((TextView) findViewById(R.id.accelerometer_value)).setText("");
    }

}
