package com.etsn05group2.lampcontroller.model;

/**
 * Created by Filip on 2015-09-24.
 */
public abstract class Device {

    protected String deviceName;
    protected String macAdress;
    protected String id;

    public abstract String getName();
    public abstract String getMacAdress();
    public abstract String getId();
}
