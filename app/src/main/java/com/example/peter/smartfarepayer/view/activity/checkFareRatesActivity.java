package com.example.peter.smartfarepayer.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.peter.smartfarepayer.R;
import com.example.peter.smartfarepayer.models.FareHistoryModel;
import com.example.peter.smartfarepayer.view.adapter.FareRecyclerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class checkFareRatesActivity extends AppCompatActivity {

    protected @BindView(R.id.history_recycler)
    RecyclerView fareRecycler;

    protected @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farehistory_layout);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*fareRecycler.setHasFixedSize(false);*/
        fareRecycler.setLayoutManager(new LinearLayoutManager(this));
        final FareRecyclerAdapter fareRecyclerAdapter = new FareRecyclerAdapter(getHistory());
        fareRecycler.setAdapter(fareRecyclerAdapter);
        //fareRecyclerAdapter.setItems(getHistory());
    }

    //History ArrayList
    private ArrayList<FareHistoryModel> getHistory() {
        ArrayList<FareHistoryModel> models = new ArrayList<>();
        ArrayList<String>saccos= new ArrayList<>();
        saccos.add("Kenya Mpya");
        saccos.add("Metro Sacco");
        saccos.add("Ummo Sacco");
        saccos.add("KMO Sacco");
        saccos.add("Rongai Sacco");
        saccos.add("Embassava Sacco");
        saccos.add("LINUS Sacco");
        saccos.add("Joy Kenya");
        saccos.add("Chania ");
        saccos.add("NYS Buses");
        ArrayList<String>dates= new ArrayList<>();
        dates.add("01/02/2018");
        dates.add("02/02/2018");
        dates.add("03/02/2018");
        dates.add("24/03/2018");
        dates.add("25/03/2018");
        dates.add("15/03/2018");
        dates.add("10/04/2018");
        dates.add("13/04/2018");
        dates.add("16/04/2018");
        dates.add("18/04/2018");

        for (int i = 0; i < saccos.size(); i++) {
            FareHistoryModel model = new FareHistoryModel();
            model.setBusSacco(saccos.get(i));
            model.setFareAmount("90.00");
            model.setTravelDate(dates.get(i));
            model.setTravelTime("1010am");
            models.add(model);
        }
        return models;
    }
}
