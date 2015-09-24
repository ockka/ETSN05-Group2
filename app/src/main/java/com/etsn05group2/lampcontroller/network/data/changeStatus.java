package com.etsn05group2.lampcontroller.network.data;

/**
 * Created by carl on 2015-09-24.
 */
public class changeStatus {
    public final String deviceAddress;
    public final String value;

    public changeStatus(String deviceAddress, String value){
        this.deviceAddress = deviceAddress;
        this.value = value;
    }
}
