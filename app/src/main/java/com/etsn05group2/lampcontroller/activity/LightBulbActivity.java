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
import android.widget.Toast;

import com.etsn05group2.lampcontroller.R;


public class LightBulbActivity extends Activity {

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
        status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isOn = true;
                } else {
                    isOn = false;
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

        }else{
            toast = Toast.makeText(context,"Lamp is not turned on",duration);
            toast.show();
        }
    }

    public void getValues(View v){
        if(isOn){

        }else{
            toast = Toast.makeText(context,"Lamp is not turned on",duration);
            toast.show();
        }
    }
}
