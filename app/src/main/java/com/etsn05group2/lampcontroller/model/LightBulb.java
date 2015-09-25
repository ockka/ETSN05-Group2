package com.etsn05group2.lampcontroller.model;

/**
 * Created by Filip on 2015-09-24.
 */
public class LightBulb extends Device {

    public LightBulb(String deviceName, String macAddress, Long id){
        super(deviceName, macAddress, id);
    }

    @Override
    public String getName() {
        return deviceName;
    }

}
