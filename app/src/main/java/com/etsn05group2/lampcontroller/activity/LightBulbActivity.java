package com.etsn05group2.lampcontroller.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.etsn05group2.lampcontroller.R;


public class LightBulbActivity extends Activity {

    private EditText red = (EditText)findViewById(R.id.Red);
    private EditText green = (EditText)findViewById(R.id.Green);
    private EditText blue = (EditText)findViewById(R.id.Blue);
    private EditText white = (EditText)findViewById(R.id.White);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_bulb);
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

    public void setColor(View v){
        String redtext = red.getText().toString();
        String greentext = green.getText().toString();
        String bluetext = blue.getText().toString();
        String whitetext = white.getText().toString();
        String color = (redtext.length() > 1 ? redtext : "00") + (greentext.length() > 1 ? greentext : "00") + (bluetext.length() > 1 ? bluetext : "00") + (whitetext.length() > 1 ? whitetext : "00");
        Log.w("Testar", color);
    }

    public void getColor(View v){

    }
}
