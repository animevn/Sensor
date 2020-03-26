package com.haanhgs.app.sensor.view;

import android.os.Bundle;
import android.widget.TextView;
import com.haanhgs.app.sensor.R;
import com.haanhgs.app.sensor.viewmodel.ViewModel;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvLight)
    TextView tvLight;
    @BindView(R.id.tvProximity)
    TextView tvProximity;

    private ViewModel viewModel;

    private void initViewModel(){
        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        viewModel.getLiveData().observe(this, sensors -> {
            Float light = sensors.getLight();
            if (light != null){
                tvLight.setText(getResources().getString(R.string.label_light, light));
            }else {
                tvLight.setText(getResources().getString(R.string.error_no_sensor));
            }

            Float proximity = sensors.getProximity();
            if (proximity != null){
                tvProximity.setText(getResources().getString(R.string.label_proximity, proximity));
            }else {
                tvProximity.setText(getResources().getString(R.string.error_no_sensor));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViewModel();
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
