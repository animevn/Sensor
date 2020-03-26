package com.haanhgs.app.sensor.model;

import android.hardware.Sensor;

public class Sensors {

    private Sensor proximity;
    private Sensor light;

    public void setProximity(Sensor proximity) {
        this.proximity = proximity;
    }

    public void setLight(Sensor light) {
        this.light = light;
    }

    public Sensor getProximity() {
        return proximity;
    }

    public Sensor getLight() {
        return light;
    }
}
