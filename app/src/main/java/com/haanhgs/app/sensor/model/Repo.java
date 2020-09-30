package com.haanhgs.app.sensor.model;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import androidx.lifecycle.MutableLiveData;

public class Repo implements SensorEventListener {

    private MutableLiveData<Sensors> liveData = new MutableLiveData<>();
    private Sensors sensors = new Sensors();
    private SensorManager manager;
    private Sensor proximity;
    private Sensor light;
    private Context context;

    public Repo(Context context) {
        this.context = context;
        initSensors();
        liveData.setValue(sensors);
    }

    private void initSensors(){
        manager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        if (manager != null){
            light = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
            proximity = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            if (light != null) sensors.setLight(0f);
            if (proximity != null) sensors.setProximity(0f);
        }
    }

    public MutableLiveData<Sensors> getLiveData(){
        return liveData;
    }

    public void start(){
        if (light != null){
            manager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (proximity != null){
            manager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    public void stop(){
        manager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int type = event.sensor.getType();
        float current = event.values[0];
        if (type == Sensor.TYPE_LIGHT){
            sensors.setLight(current);
            liveData.setValue(sensors);
        }else if (type == Sensor.TYPE_PROXIMITY){
            sensors.setProximity(current);
            liveData.setValue(sensors);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
