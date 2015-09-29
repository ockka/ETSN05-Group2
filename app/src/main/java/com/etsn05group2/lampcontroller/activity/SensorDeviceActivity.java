package com.etsn05group2.lampcontroller.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.etsn05group2.lampcontroller.R;
import com.etsn05group2.lampcontroller.network.NetworkManager;
import com.etsn05group2.lampcontroller.network.data.DeviceData;
import com.etsn05group2.lampcontroller.network.data.DeviceStatus;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SensorDeviceActivity extends DeviceActivity {

    private Switch sensorSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_device);
        sensorSwitch = (Switch) findViewById(R.id.sensor_switch);
        final TextView deviceName = (TextView) findViewById(R.id.device_name);
        deviceName.setText(device.getName() + "" + device.getId());
        TextView macAddress = (TextView) findViewById(R.id.mac_address);
        macAddress.setText(device.getMacAddress());
        /* TODO: Check if device is on
        sensorSwitch.setChecked(manager.getToggledState(device, new Callback<DeviceStatus>() {
            @Override
            public void success(DeviceStatus deviceStatus, Response response) {
                sensorSwitch.setChecked(deviceStatus.value());
            }

            @Override
            public void failure(RetrofitError error) {
                sensorSwitch.setChecked(false);
                Log.d("failure", error.toString());
            }
        }));*/

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
    public void getAll(View v) {
        NetworkManager.getAllSensorValues(device, new Callback<List<DeviceData>>() {
            @Override
            public void success(List<DeviceData> deviceDatas, Response response) {
                /* TODO: Get all sensor values */
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("failure", error.toString());
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
