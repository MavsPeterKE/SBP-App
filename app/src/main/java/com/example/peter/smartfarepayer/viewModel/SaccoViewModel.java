package com.example.peter.smartfarepayer.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.peter.smartfarepayer.Database.SaccoDataRepository;
import com.example.peter.smartfarepayer.models.SaccoData;

import java.util.List;

public class SaccoViewModel extends AndroidViewModel {
    private SaccoDataRepository saccoDataRepository;
    private LiveData<List<SaccoData>>listLiveData;

    public SaccoViewModel(@NonNull Application application) {
        super(application);
        saccoDataRepository = new SaccoDataRepository(application);
        listLiveData = saccoDataRepository.getAllSaccoData();
    }

    public void insert(SaccoData saccoData) {
        saccoDataRepository.insertSaccoData(saccoData);
    }
}
