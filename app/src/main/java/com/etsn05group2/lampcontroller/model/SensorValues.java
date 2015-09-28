package com.etsn05group2.lampcontroller.model;

/**
 * Created by Madeleine on 28/09/15.
 */
public class SensorValues {
    private Temperature temperature;
    private Pressure pressure;
    private Humidity humidity;
    private Magnetic magnetic;
    private Gyroscopic gyroscopic;
    private Accelerometer accelerometer;

    public SensorValues(Temperature temperature, Pressure pressure, Humidity humidity, Magnetic magnetic, Gyroscopic gyroscopic, Accelerometer accelerometer) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.magnetic = magnetic;
        this.gyroscopic = gyroscopic;
        this.accelerometer = accelerometer;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public Pressure getPressure() {
        return pressure;
    }

    public Humidity getHumidity() {
        return humidity;
    }

    public  Magnetic getMagnetic() {
        return magnetic;
    }

    public Gyroscopic getGyroscopic() {
        return gyroscopic;
    }

    public  Accelerometer getAccelerometer() {
        return accelerometer;
    }
}
