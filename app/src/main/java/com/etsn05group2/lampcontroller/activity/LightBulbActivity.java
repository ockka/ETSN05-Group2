package com.etsn05group2.lampcontroller.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
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


public class LightBulbActivity extends DeviceActivity {
    private EditText red;
    private EditText green;
    private EditText blue;
    private EditText white;
    private Switch lightBulbSwitch;
    private Toast toast;
    private int duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setContentView(R.layout.activity_light_bulb);
        red = (EditText) findViewById(R.id.Red);
        green = (EditText) findViewById(R.id.Green);
        blue = (EditText) findViewById(R.id.Blue);
        white = (EditText) findViewById(R.id.White);
        lightBulbSwitch = (Switch) findViewById(R.id.lightBulbSwitch);
        duration = Toast.LENGTH_SHORT;
        toast = Toast.makeText(getApplicationContext(),"",duration);
        TextView name = (TextView) findViewById(R.id.NameId);
        name.setText(device.getName() + " " + device.getId());
        TextView mac = (TextView) findViewById(R.id.Mac);
        mac.setText(device.getMacAddress());
        NetworkManager.getToggledState(device, new Callback<DataAboutDevice>() {
            @Override
            public void success(DataAboutDevice dataAboutDevice, Response response) {
                lightBulbSwitch.setChecked(dataAboutDevice.status == 1);
            }

            @Override
            public void failure(RetrofitError error) {
                lightBulbSwitch.setChecked(false);
                toast.setText("Error occurred");
                toast.show();
            }
        });

        lightBulbSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                NetworkManager.toggle(device, isChecked, new Callback<DeviceStatus>() {
                    @Override
                    public void success(DeviceStatus deviceStatus, Response response) {
                        toast.setText("Success");
                        toast.show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        lightBulbSwitch.setChecked(false);
                        toast.setText("Error occurred");
                        toast.show();

                    }
                });
            }
        });
    }

    public void setValues(View v) {
        if (lightBulbSwitch.isChecked()) {
            String redtext = red.getText().toString();
            if(redtext.length() == 1)
                    redtext = "0" + redtext;
            String greentext = green.getText().toString();
            if(greentext.length() == 1)
                greentext = "0" + greentext;
            String bluetext = blue.getText().toString();
            if(bluetext.length() == 1)
                bluetext = "0" + bluetext;
            String whitetext = white.getText().toString();
            if(whitetext.length() == 1)
                whitetext = "0" + whitetext;
            String color = (redtext.length() > 1 ? redtext : "00") + (greentext.length() > 1 ? greentext : "00") + (bluetext.length() > 1 ? bluetext : "00") + (whitetext.length() > 1 ? whitetext : "00");
            NetworkManager.setColor(device, color.toLowerCase(), new Callback<DeviceStatus>() {
                @Override
                public void success(DeviceStatus deviceStatus, Response response) {
                    toast.setText("Color successfully changed.");
                    toast.show();
                }

                @Override
                public void failure(RetrofitError error) {
                    toast.setText("Error occurred");
                    toast.show();
                }
            });

        } else {
            toast.setText("Lamp is not turned on");
            toast.show();
        }
    }

    public void getValues(View v) {
        NetworkManager.getColor(device, new Callback<List<DeviceData>>() {
            @Override
            public void success(List<DeviceData> deviceDatas, Response response) {
                String color = deviceDatas.get(deviceDatas.size() - 1).value;
                red.setText(color.substring(0, 2).toUpperCase());
                green.setText(color.substring(2, 4).toUpperCase());
                blue.setText(color.substring(4, 6).toUpperCase());
                white.setText(color.substring(6, 8).toUpperCase());
            }

            @Override
            public void failure(RetrofitError error) {
                toast.setText("Error occurred");
                toast.show();
            }
        });
    }
}
