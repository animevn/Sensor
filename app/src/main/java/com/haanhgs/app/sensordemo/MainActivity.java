package com.haanhgs.app.sensordemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    @BindView(R.id.tvLabel)
    TextView tvLabel;
    @BindView(R.id.tvLight)
    TextView tvLight;
    @BindView(R.id.tvProximity)
    TextView tvProximity;

    private SensorManager sensorManager;
    private Sensor lightSensor;
    private Sensor proximitySensor;

    private void initSensors(){
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        if (sensorManager != null){
            lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        }
        if (lightSensor == null) {
            tvLight.setText(getResources().getString(R.string.error_no_sensor));
        }
        if (proximitySensor == null){
            tvProximity.setText(getResources().getString(R.string.error_no_sensor));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initSensors();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (lightSensor != null){
            sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (proximitySensor != null){
            sensorManager.registerListener(
                    this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int type = event.sensor.getType();
        float current = event.values[0];
        if (type == Sensor.TYPE_PROXIMITY){
            tvProximity.setText(getResources().getString(R.string.label_proximity, current));
        }
        if (type == Sensor.TYPE_LIGHT){
            tvLight.setText(getResources().getString(R.string.label_light, current));
        }
    }

    @Override public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
