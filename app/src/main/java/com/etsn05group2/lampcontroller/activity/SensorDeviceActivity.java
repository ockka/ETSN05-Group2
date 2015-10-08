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
import java.util.StringTokenizer;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SensorDeviceActivity extends DeviceActivity {

    private Switch sensorSwitch;
    private TextView temperature;
    private TextView pressure;
    private TextView humidity;
    private TextView magnometer;
    private TextView gyroscope;
    private TextView accelerometer;
    private int duration;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_device);

        final TextView deviceName = (TextView) findViewById(R.id.device_name);
        deviceName.setText(device.getName() + " " + device.getId());
        TextView macAddress = (TextView) findViewById(R.id.mac_address);
        macAddress.setText(device.getMacAddress());

        sensorSwitch = (Switch) findViewById(R.id.sensor_switch);
        temperature = (TextView) findViewById(R.id.temperature_value);
        pressure = (TextView) findViewById(R.id.pressure_value);
        humidity = (TextView) findViewById(R.id.humidity_value);
        magnometer = (TextView) findViewById(R.id.magnetic_value);
        gyroscope = (TextView) findViewById(R.id.gyroscopic_value);
        accelerometer = (TextView) findViewById(R.id.accelerometer_value);
        duration = Toast.LENGTH_SHORT;
        toast = Toast.makeText(getApplicationContext(), "", duration);

        NetworkManager.getToggledState(device, new Callback<DataAboutDevice>() {
            @Override
            public void success(DataAboutDevice dataAboutDevice, Response response) {
                sensorSwitch.setChecked(dataAboutDevice.status == 1 ? true : false);
            }

            @Override
            public void failure(RetrofitError error) {
                sensorSwitch.setChecked(false);
                toast.setText("Error occurred");
                toast.show();
            }
        });

        sensorSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
                NetworkManager.toggle(device, isChecked, new Callback<DeviceStatus>() {
                    @Override
                    public void success(DeviceStatus deviceStatus, Response response) {
                        toast.setText("Success");
                        toast.show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        sensorSwitch.setChecked(false);
                        toast.setText("Error occurred");
                        toast.show();

                    }
                });
            }
        });
    }

    private Callback<List<DeviceData>> getValue(final TextView textView) {
        Callback<List<DeviceData>> call = new Callback<List<DeviceData>>() {
            @Override
            public void success(List<DeviceData> deviceDatas, Response response) {
                if (deviceDatas.size() != 0) {
                    String[] values = deviceDatas.get(deviceDatas.size() - 1).value.split(";");
                    String text;
                    if (values.length == 1) {
                        text = values[0];
                    } else {
                        text = String.format("x: %s\ny: %s\nz: %s", values[0], values[1], values[2]);
                    }
                    textView.setText(text);

                    /*
                    DeviceData deviceData = deviceDatas.get(deviceDatas.size() - 1);
                    textView.setText(deviceData.value.toString());
                    */
                } else {
                    toast.setText("No data available");
                    toast.show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                toast.setText("No data available");
                toast.show();
            }
        };
        return call;
    }

    public void getTemperature(View v) {
        NetworkManager.getTemperature(device, getValue(temperature));
    }

    public void getPressure(View v) {
        NetworkManager.getPressure(device, getValue(pressure));
    }

    public void getHumidity(View v) {
        NetworkManager.getHumidity(device, getValue(humidity));
    }

    public void getMagnetic(View v) {
        NetworkManager.getMagnetic(device, getValue(magnometer));
    }

    public void getGyroscopic(View v) {
        NetworkManager.getGyroscopic(device, getValue(gyroscope));
    }

    public void getAccelerometer(View v) {
        NetworkManager.getAccelerometer(device, getValue(accelerometer));
    }

    public void getAll(View v) {
        NetworkManager.getAllSensorValues(device, new Callback<List<DeviceData>>() {
            @Override
            public void success(List<DeviceData> deviceDatas, Response response) {
                if (deviceDatas.size() != 0) {
                    for (int i = 0; i < deviceDatas.size(); i++) {
                        DeviceData newDevice = deviceDatas.get(i);
                        if (newDevice.sensorType.equals("temperature")) {
                            temperature.setText(newDevice.value.toString() + " \u2103");
                        } else if (newDevice.sensorType.equals("pressure")) {
                            pressure.setText(newDevice.value.toString() + " mBar");
                        } else if (newDevice.sensorType.equals("humidity")) {
                            humidity.setText(newDevice.value.toString() + " %");
                        } else if (newDevice.sensorType.equals("magnometer")) {
                            String[] coords = newDevice.value.split(";");
                            String text = String.format("x: %s\ny: %s\nz: %s", coords[0], coords[1], coords[2]);
                            magnometer.setText(text);
                        } else if (newDevice.sensorType.equals("gyroscope")) {
                            String[] coords = newDevice.value.split(";");
                            String text = String.format("x: %s\ny: %s\nz: %s", coords[0], coords[1], coords[2]);
                            gyroscope.setText(text);
                        } else if (newDevice.sensorType.equals("accelerometer")) {
                            String[] coords = newDevice.value.split(";");
                            String text = String.format("x: %s\ny: %s\nz: %s", coords[0], coords[1], coords[2]);
                            accelerometer.setText(text);
                        }
                    }
                } else {
                    toast.setText("No data available");
                    toast.show();
                }
            }


            @Override
            public void failure(RetrofitError error) {
                toast.setText("No data available");
                toast.show();
            }
        });
    }

    public void clearAll(View v) {
        temperature.setText("");
        pressure.setText("");
        humidity.setText("");
        magnometer.setText("");
        gyroscope.setText("");
        accelerometer.setText("");
    }

}
