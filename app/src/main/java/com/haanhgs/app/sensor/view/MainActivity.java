package com.haanhgs.app.sensor.view;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.haanhgs.app.sensor.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvLight)
    TextView tvLight;
    @BindView(R.id.tvProximity)
    TextView tvProximity;

    private SensorManager manager;
    private Sensor proximity;
    private Sensor light;

    private void initSensor(){
        manager = (SensorManager)getSystemService(SENSOR_SERVICE);
        if (manager != null){
            light = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
            proximity = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


}
