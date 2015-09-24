package com.etsn05group2.lampcontroller.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.etsn05group2.lampcontroller.R;
import com.etsn05group2.lampcontroller.network.NetworkManager;

public class SensorDeviceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_device);
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
        //String result = NetworkManager.getTemperature().getValue();
        String result = "24,7";
        TextView textView = (TextView)findViewById(R.id.temperatureValueTextView);
        textView.setText(result);
    }

    protected void getPressure(View v) {
        //String result = NetworkManager.getPressure().getValue();
        String result = "24,7";
        TextView textView = (TextView)findViewById(R.id.pressureValueTextView);
        textView.setText(result);
    }

    protected void getHumidity(View v) {
        //String result = NetworkManager.getHumidity().getValue();
        String result = "24,7";
        TextView textView = (TextView)findViewById(R.id.humidityValueTextView);
        textView.setText(result);
    }
    protected void getMagnetic(View v) {
        //String result = NetworkManager.getMagnetic().getValue();
        String result = "24,7";
        TextView textView = (TextView)findViewById(R.id.magneticValueTextView);
        textView.setText(result);
    }
    protected void getGyroscopic(View v) {
        //String result = NetworkManager.getGyroscopic().getValue();
        String result = "24,7";
        TextView textView = (TextView)findViewById(R.id.gyroscopicValueTextView);
        textView.setText(result);
    }
    protected void getAccelerometer(View v) {
        //String result = NetworkManager.getAccelerometer().getValue();
        String result = "24,7";
        TextView textView = (TextView)findViewById(R.id.accelerometerValueTextView);
        textView.setText(result);
    }
    protected void getAll(View v) {
        //SensorValues sensorValues = NetworkManager.getAllSensorValues();
        String result = "24,7";
        TextView textView = (TextView)findViewById(R.id.temperatureValueTextView);
        textView.setText(result);
    }

    protected void clearAll(View v) {
        TextView textViewT = (TextView)findViewById(R.id.temperatureValueTextView);
        TextView textViewP = (TextView)findViewById(R.id.pressureValueTextView);
        TextView textViewH = (TextView)findViewById(R.id.humidityValueTextView);
        TextView textViewM = (TextView)findViewById(R.id.magneticValueTextView);
        TextView textViewG = (TextView)findViewById(R.id.gyroscopicValueTextView);
        TextView textViewA = (TextView)findViewById(R.id.accelerometerValueTextView);

        textViewT.setText("");
        textViewP.setText("");
        textViewH.setText("");
        textViewM.setText("");
        textViewG.setText("");
        textViewA.setText("");
    }

}
