package com.etsn05group2.lampcontroller.model;

/**
 * Created by Filip on 2015-09-24.
 */
public abstract class Device {
    protected String deviceName;
    protected String macAddress;
    protected Long id;

    public Device(String deviceName, String macAddress, Long id){
        this.deviceName = deviceName;
        this.macAddress = macAddress;
        this.id = id;
    }
    public abstract String getName();

    public String getMacAddress() {
        return macAddress;
    }

    public Long getId() {
        return id;
    }
}
