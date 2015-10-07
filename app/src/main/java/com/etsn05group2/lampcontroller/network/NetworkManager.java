package com.etsn05group2.lampcontroller.network;

import android.util.Log;

import com.etsn05group2.lampcontroller.model.Device;
import com.etsn05group2.lampcontroller.network.data.DataAboutDevice;
import com.etsn05group2.lampcontroller.network.data.DeviceData;
import com.etsn05group2.lampcontroller.network.data.DeviceStatus;
import com.etsn05group2.lampcontroller.network.data.ToggledStateResponse;
import com.squareup.okhttp.OkHttpClient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

import java.util.Calendar;



/**
 * Created by Niklas on 2015-09-22.
 */
public class NetworkManager {

    private static final String PATH = "http://vm39.cs.lth.se:9000/";
    private static OkHttpClient client;
    private static NetworkManagerApi api;

    static {
        long timeout = 15L;
        client = new OkHttpClient();
        client.setReadTimeout(timeout, TimeUnit.SECONDS);
        client.setWriteTimeout(timeout, TimeUnit.SECONDS);
        client.setConnectTimeout(timeout, TimeUnit.SECONDS);
        api = new RestAdapter.Builder().setEndpoint(PATH).setClient(new OkClient(client)).build().create(NetworkManagerApi.class);
    }

    public NetworkManager() {
    }

    public static void toggle(Device device, boolean value, Callback<DeviceStatus> callback) {
        api.putDeviceStatus(new DeviceStatus(device.getMacAddress(), (value ? "1" : "0")), callback);
    }


    public static void getToggledState(Device device, Callback<DataAboutDevice> callback) {
        api.getDataAboutDevice(device.getId(), callback);
    }

    public static void detectDevices(Callback<List<DataAboutDevice>> callback) {
        api.getDataAboutAllDevices(callback);
    }

    public static void getTemperature(Device device, Callback<List<DeviceData>> callback) {
        String startDate = previousTime();
        String endDate = currentTime();
        api.getDeviceDataTimeLimit(device.getId(), "temperature", startDate, endDate, callback);
    }

    public static void getPressure(Device device, Callback<List<DeviceData>> callback) {
        String startDate = previousTime();
        String endDate = currentTime();
        api.getDeviceDataTimeLimit(device.getId(), "pressure", startDate, endDate, callback);
    }

    public static void getHumidity(Device device, Callback<List<DeviceData>> callback) {
        String startDate = previousTime();
        String endDate = currentTime();
        api.getDeviceDataTimeLimit(device.getId(), "humidity", startDate, endDate, callback);
    }

    public static void getMagnetic(Device device, Callback<List<DeviceData>> callback) {
        String startDate = previousTime();
        String endDate = currentTime();
        api.getDeviceDataTimeLimit(device.getId(), "magnometer", startDate, endDate, callback);
    }

    public static void getAccelerometer(Device device, Callback<List<DeviceData>> callback) {
        String startDate = previousTime();
        String endDate = currentTime();
        api.getDeviceDataTimeLimit(device.getId(), "accelerometer", startDate, endDate, callback);
    }

    public static void getGyroscopic(Device device, Callback<List<DeviceData>> callback) {
        String startDate = previousTime();
        String endDate = currentTime();
        api.getDeviceDataTimeLimit(device.getId(), "gyroscope", startDate, endDate, callback);   // is this right/
    }

    public static void getAllSensorValues(Device device, Callback<List<DeviceData>> callback) {
        String startDate = previousTime();
        String endDate = currentTime();
        api.getDeviceDataTimeLimit(device.getId(),startDate, endDate,callback);
    }

    public static void getColor(Device device, Callback<List<DeviceData>> callback) {
        api.getDeviceData(device.getId(), callback);
    }

    public static void setColor(Device device, String color, Callback<DeviceStatus> callback) {
        api.putDeviceValue(new DeviceStatus(device.getMacAddress(), color), callback);
    }

    /**
     * Placera in all Fulkod här under
     *
     *
     *
     *
     */

    static private Callback<DeviceStatus> colorCall(){
        return new Callback<DeviceStatus>() {
            @Override
            public void success(DeviceStatus device, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {

            }
        };
    }







    static private Callback<List<DataAboutDevice>> allDevicesCall(){
        Callback<List<DataAboutDevice>> call = new Callback<List<DataAboutDevice>>(){

            @Override
            public void success(List<DataAboutDevice> dataAboutDevices, Response response) {
                Log.d("Succes", "");
               //detectedDevices = dataAboutDevices;
            }

            @Override
            public void failure(RetrofitError error) { Log.d("failure", error.toString()); }
        };
        return call;
    }

    static private String currentTime(){
        Calendar calendar = Calendar.getInstance();
        TimeZone timezone = TimeZone.getDefault();
        timezone.setID("Europe/Stockholm");
        calendar.setTimeZone(timezone);
        calendar.set(Calendar.HOUR_OF_DAY, Calendar.HOUR_OF_DAY + 4);
        /*
        calendar.set(Calendar.HOUR_OF_DAY, Calendar.HOUR_OF_DAY + 3);
        calendar.set(Calendar.YEAR, 2015);

        DateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String time = (df.format(calendar.getTime()));
        Log.d("Current = ", "" + df.format(calendar.getTime()));
        Log.d("TimeZone = ", "" + timezone.getID());
        */
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        month = month + 1;
        int day = calendar.get(Calendar.DATE);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);
        if (minutes < 0) {
            minutes += 60;
            hours--;
        }
        if (hours < 0) {
            hours += 24;
            hours--;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(year);
        sb.append("-");
        if (month < 10) {
            sb.append("0");
        }
        sb.append(month);
        sb.append("-");
        if (day < 10) {
            sb.append("0");
        }
        sb.append(day);
        sb.append(" ");
        if (hours < 10) {
            sb.append("0");
        }
        sb.append(hours);
        sb.append(":");
        if (minutes < 10) {
            sb.append("0");
        }
        sb.append(minutes);
        sb.append(":");
        if (seconds < 10) {
            sb.append("0");
        }
        sb.append(seconds);
        Log.d("Current time", "" + sb.toString());
        return sb.toString();
        //return time;
    }

    static private String previousTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Calendar.HOUR_OF_DAY + 4);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        month = month + 1;
        int day = calendar.get(Calendar.DATE);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);

        minutes = (minutes - 50) % 60;
        if (minutes < 0) {
            minutes += 60;
            hours--;
        }
        if (hours < 0) {
            hours += 24;
            hours--;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(year);
        sb.append("-");
        if (month < 10) {
            sb.append("0");
        }
        sb.append(month);
        sb.append("-");
        if (day < 10) {
            sb.append("0");
        }
        sb.append(day);
        sb.append(" ");
        if (hours < 10) {
            sb.append("0");
        }
        sb.append(hours);
        sb.append(":");
        if (minutes < 10) {
            sb.append("0");
        }
        sb.append(minutes);
        sb.append(":");
        if (seconds < 10) {
            sb.append("0");
        }

        sb.append(seconds);
        Log.d("TID:", "=" + sb.toString());
        Log.d("Month", "= " + month);
        Log.d("day", "= " + day);
        Log.d("hour", "= " + hours);
        Log.d("minute", "= " + minutes);
        Log.d("second", "= " + seconds);
        return sb.toString();
    }

}
