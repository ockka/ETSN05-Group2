package com.etsn05group2.lampcontroller.adapter;

import com.etsn05group2.lampcontroller.model.Device;

import java.util.List;

/**
 * Created by David on 2015-09-25.
 */
public class DeviceListAdapter {
    private List<Device> devices;
    public void addDevices(List<Device> devices){
        this.devices = devices;
    }
}
