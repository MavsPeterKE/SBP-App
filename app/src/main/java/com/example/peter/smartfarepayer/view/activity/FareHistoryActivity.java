package com.example.peter.smartfarepayer.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.peter.smartfarepayer.R;
import com.example.peter.smartfarepayer.view.adapter.FareRecyclerAdapter;
import com.example.peter.smartfarepayer.models.FareHistoryModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FareHistoryActivity extends AppCompatActivity {

    protected @BindView(R.id.history_recycler)
    RecyclerView fareRecycler;

    protected @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fare_report);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*fareRecycler.setHasFixedSize(false);*/
        fareRecycler.setLayoutManager(new LinearLayoutManager(this));
        final FareRecyclerAdapter fareRecyclerAdapter = new FareRecyclerAdapter();
        fareRecycler.setAdapter(fareRecyclerAdapter);
        fareRecyclerAdapter.setItems(getHistory());
    }

    //History ArrayList
    private ArrayList<FareHistoryModel> getHistory() {
        ArrayList<FareHistoryModel> models = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            FareHistoryModel model = new FareHistoryModel();
            model.setBusSacco("BusKenya");
            model.setFareAmount("90.00");
            model.setTravelDate("24/05/1999");
            model.setTravelTime("1010am");
            models.add(model);
        }
        return models;
    }
}
