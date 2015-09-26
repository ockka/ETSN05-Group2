package com.etsn05group2.lampcontroller.model;

/**
 * Created by Filip on 2015-09-24.
 */
public class LightBulb extends Device {

    public LightBulb(String macAddress, long id){
        super(macAddress, id);
    }

    @Override
    public String getName() {
        return "LightBulb";
    }

}
