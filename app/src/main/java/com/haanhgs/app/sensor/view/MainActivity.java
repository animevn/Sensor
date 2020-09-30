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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private void initViewModel(){
        viewModel = new ViewModelProvider(this).get(ViewModel.class);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
