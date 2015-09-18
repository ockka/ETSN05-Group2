package com.etsn05group2.lampcontroller.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.etsn05group2.lampcontroller.R;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Daniel on 2015-09-18.
 */
public class ExampleActivity extends Activity {

    private LampApi api;
    private TextView textView;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        textView = (TextView) findViewById(R.id.text_wind_speed);
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);


        RestAdapter retrofit = new RestAdapter.Builder()
                .setEndpoint("http://api.openweathermap.org/")
                .build();
        api = retrofit.create(LampApi.class);
    }

    public void onClick(View v) {
        api.getWeather("Lund,Sweden", new Callback<WeatherObj>() {
            @Override
            public void success(WeatherObj weatherObj, Response response) {
                textView.setText(weatherObj.wind.speed + " m/s.");
                toast.cancel();
            }

            @Override
            public void failure(RetrofitError error) {
                toast.setText("Failure: " + error.toString());
                toast.show();
            }
        });
    }
}
