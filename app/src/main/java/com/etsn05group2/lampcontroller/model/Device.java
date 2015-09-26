package com.etsn05group2.lampcontroller.model;

/**
 * Created by Filip on 2015-09-24.
 */
public abstract class Device {
    protected String macAddress;
    protected long id;

    public Device(String macAddress, long id){
        this.macAddress = macAddress;
        this.id = id;
    }
    public abstract String getName();

    public String getMacAddress() {
        return macAddress;
    }

    public long getId() {
        return id;
    }
}
