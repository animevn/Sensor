package com.haanhgs.app.sensor.view;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.haanhgs.app.sensor.R;
import com.haanhgs.app.sensor.model.Sensors;
import com.haanhgs.app.sensor.viewmodel.ViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvLight)
    TextView tvLight;
    @BindView(R.id.tvProximity)
    TextView tvProximity;

    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.getLiveData().observe(this, sensors -> {
            tvLight.setText(getResources().getString(R.string.label_light, sensors.getLight()));
            tvProximity.setText(getResources()
                    .getString(R.string.label_proximity, sensors.getProximity()));
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        viewModel.stop();
    }
}
