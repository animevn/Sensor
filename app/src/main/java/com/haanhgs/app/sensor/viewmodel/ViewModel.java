package com.haanhgs.app.sensor.viewmodel;

import android.app.Application;
import com.haanhgs.app.sensor.model.Repo;
import com.haanhgs.app.sensor.model.Sensors;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class ViewModel extends AndroidViewModel {

    private final Repo repo;

    public ViewModel(@NonNull Application application) {
        super(application);
        repo = new Repo(application.getApplicationContext());
    }

    public MutableLiveData<Sensors> getLiveData() {
        return repo.getLiveData();
    }

    public void start(){
        repo.start();
    }

    public void stop(){
        repo.stop();
    }
}
