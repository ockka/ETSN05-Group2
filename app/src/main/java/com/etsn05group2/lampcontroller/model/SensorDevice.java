package com.etsn05group2.lampcontroller.model;

/**
 * Created by Madeleine on 28/09/15.
 */
public class SensorDevice extends Device {

    public SensorDevice(String macAddress, long id){
        super(macAddress, id);
    }

    @Override
    public String getName() {
        return "Sensor";
    }
}
