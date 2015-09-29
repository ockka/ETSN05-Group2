package com.etsn05group2.lampcontroller.network.data;

import java.util.List;

/**
 * Created by Niklas on 2015-09-29.
 */
public abstract class DeviceDataList {

    public final List<DeviceData> list;

    public DeviceDataList(List<DeviceData> list) {
        this.list = list;
    }

    public String getLatestValue() {
        if (list.isEmpty()) {
            return "";
        }
        return list.get(list.size() - 1).value;
    }

    public String getLatestTime() {
        if (list.isEmpty()) {
            return "";
        }
        return list.get(list.size() - 1).time;
    }

    public String getLatestSensorType() {
        if (list.isEmpty()) {
            return "";
        }
        return list.get(list.size() - 1).sensorType;
    }

    public String getLatestDevice() {
        if (list.isEmpty()) {
            return "";
        }
        return list.get(list.size() - 1).device;
    }
}
