package com.etsn05group2.lampcontroller.activity;

import android.app.Activity;
import android.os.Bundle;
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
        sensorSwitch = (Switch) findViewById(R.id.sensorSwitch);
        /*
        sensorSwitch.setChecked(manager.getToggledState(device, new Callback<ToggledStateResponse>() {
            @Override
            public void success(Response<DeviceStatus> response) {

            }

            @Override
            public void failure(RetrofitError error) {
                sensorSwitch.setChecked(false);
            }
        }));
        sensorSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
                manager.toggle(device, isChecked, new Callback<Response>() {
                    @Override
                    public void success(Response<DeviceStatus> response) {

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        sensorSwitch.setChecked(!isChecked);
                    }
                });
            }
        });
        */
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

    protected void getTemperature(View v) {
        /*
        manager.getTemperature(device, new Callback<List<DeviceData>>() {
            @Override
            public void success(List<DeviceData> deviceDatas, Response response) {
                DeviceData deviceData = response.body().get(response.body().size() - 1);
                TextView textView = (TextView) findViewById(R.id.temperatureValueTextView);
                textView.setText(deviceData.value.toString());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
        */
    }

    protected void getPressure(View v) {
        NetworkManager.getPressure(device, new Callback<List<DeviceData>>() {
            @Override
            public void success(List<DeviceData> deviceDatas, Response response) {
                DeviceData deviceData = deviceDatas.get(deviceDatas.size() - 1);
                TextView textView = (TextView) findViewById(R.id.pressureValueTextView);
                textView.setText(deviceData.value.toString());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    protected void getHumidity(View v) {
        NetworkManager.getHumidity(device, new Callback<List<DeviceData>>() {
            @Override
            public void success(List<DeviceData> deviceDatas, Response response) {
                DeviceData deviceData = deviceDatas.get(deviceDatas.size() - 1);
                TextView textView = (TextView) findViewById(R.id.pressureValueTextView);
                textView.setText(deviceData.value.toString());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    protected void getMagnetic(View v) {
        NetworkManager.getMagnetic(device, new Callback<List<DeviceData>>() {
            @Override
            public void success(List<DeviceData> deviceDatas, Response response) {
                DeviceData deviceData = deviceDatas.get(deviceDatas.size() - 1);
                TextView textView = (TextView) findViewById(R.id.pressureValueTextView);
                textView.setText(deviceData.value.toString());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    protected void getGyroscopic(View v) {
        NetworkManager.getGyroscopic(device, new Callback<List<DeviceData>>() {
            @Override
            public void success(List<DeviceData> deviceDatas, Response response) {
                DeviceData deviceData = deviceDatas.get(deviceDatas.size() - 1);
                TextView textView = (TextView) findViewById(R.id.pressureValueTextView);
                textView.setText(deviceData.value.toString());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    protected void getAccelerometer(View v) {
        NetworkManager.getAccelerometer(device, new Callback<List<DeviceData>>() {
            @Override
            public void success(List<DeviceData> deviceDatas, Response response) {
                DeviceData deviceData = deviceDatas.get(deviceDatas.size() - 1);
                TextView textView = (TextView) findViewById(R.id.pressureValueTextView);
                textView.setText(deviceData.value.toString());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    protected void getAll(View v) {

    }

    protected void clearAll(View v) {
        ((TextView) findViewById(R.id.temperatureValueTextView)).setText("");
        ((TextView) findViewById(R.id.pressureValueTextView)).setText("");
        ((TextView) findViewById(R.id.humidityValueTextView)).setText("");
        ((TextView) findViewById(R.id.magneticValueTextView)).setText("");
        ((TextView) findViewById(R.id.gyroscopicValueTextView)).setText("");
        ((TextView) findViewById(R.id.accelerometerValueTextView)).setText("");
    }

}
