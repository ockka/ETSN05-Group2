package com.etsn05group2.lampcontroller.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.etsn05group2.lampcontroller.R;
import com.etsn05group2.lampcontroller.model.LightBulb;
import com.etsn05group2.lampcontroller.network.NetworkManager;
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
    private Switch status;
    private boolean isOn;
    private Toast toast;
    private Context context;
    private int duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_bulb);
        red = (EditText)findViewById(R.id.Red);
        green = (EditText)findViewById(R.id.Green);
        blue = (EditText)findViewById(R.id.Blue);
        white = (EditText)findViewById(R.id.White);
        status = (Switch) findViewById(R.id.lightBulbSwitch);
        context = getApplicationContext();
        duration = Toast.LENGTH_SHORT;
        TextView name = (TextView)findViewById(R.id.NameId);
        name.setText(device.getName() + " " + device.getId());
        TextView mac = (TextView)findViewById(R.id.Mac);
        mac.setText(device.getMacAddress());
        status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isOn = true;
                    //LightBulb lb = new LightBulb("90:59:AF:2A:BD:19", 24);
                    //NetworkManager.toggle(lb, true, new Callback<DeviceStatus>() {
                    NetworkManager.toggle(device, true, new Callback<DeviceStatus>() {
                        @Override
                        public void success(DeviceStatus deviceStatus, Response response) {

                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });
                } else {
                    isOn = false;
                    //LightBulb lb = new LightBulb("90:59:AF:2A:BD:19",24);
                    //NetworkManager.toggle(lb, false, new Callback<DeviceStatus>() {
                    NetworkManager.toggle(device, false, new Callback<DeviceStatus>() {
                        @Override
                        public void success(DeviceStatus deviceStatus, Response response) {

                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });


                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_light_bulb, menu);
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

    public void setValues(View v){
        if(isOn) {
            String redtext = red.getText().toString();
            String greentext = green.getText().toString();
            String bluetext = blue.getText().toString();
            String whitetext = white.getText().toString();
            String color = (redtext.length() > 1 ? redtext : "00") + (greentext.length() > 1 ? greentext : "00") + (bluetext.length() > 1 ? bluetext : "00") + (whitetext.length() > 1 ? whitetext : "00");
            //Log.w("Testar", color);
            //LightBulb lb = new LightBulb("90:59:AF:2A:BD:19", 24);
            //NetworkManager.setColor(lb, color, new Callback<DeviceStatus>() {
            NetworkManager.setColor(device, color, new Callback<DeviceStatus>() {
                @Override
                public void success(DeviceStatus deviceStatus, Response response) {
                    toast = Toast.makeText(context,"Color successfully changed.",duration);
                    toast.show();
                }

                @Override
                public void failure(RetrofitError error) {
                    toast = Toast.makeText(context,"Error: Could not change color.",duration);
                    toast.show();
                }
            });

        }else{
            toast = Toast.makeText(context,"Lamp is not turned on",duration);
            toast.show();
        }
    }

    public void getValues(View v){
        if(isOn){
            //LightBulb lb = new LightBulb("90:59:AF:2A:BD:19", 24);
            //NetworkManager.getColor(lb, new Callback<List<DeviceData>>() {
            NetworkManager.getColor(device, new Callback<List<DeviceData>>() {
                @Override
                public void success(List<DeviceData> deviceDatas, Response response) {
                    String color = deviceDatas.get(deviceDatas.size() - 1).value;
                    red.setText(color.substring(0,2));
                    green.setText(color.substring(2, 4));
                    blue.setText(color.substring(4, 6));
                    white.setText(color.substring(6, 8));
                }

                @Override
                public void failure(RetrofitError error) {
                    toast = Toast.makeText(context,"Error: Could not get color values.",duration);
                    toast.show();
                }
            });
        }else{
            toast = Toast.makeText(context,"Lamp is not turned on",duration);
            toast.show();
        }
    }
}
