package com.example.peter.smartfarepayer.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.example.peter.smartfarepayer.R;
import com.example.peter.smartfarepayer.retrofit.ApiService;
import com.example.peter.smartfarepayer.retrofit.RetrofitClient;
import com.example.peter.smartfarepayer.retrofit.model.HistoryResponse;
import com.example.peter.smartfarepayer.retrofit.model.PaymentResponse;
import com.example.peter.smartfarepayer.utils.ApiUtils;
import com.example.peter.smartfarepayer.utils.PreferenceManager;
import com.example.peter.smartfarepayer.view.adapter.FareRecyclerAdapter;
import com.example.peter.smartfarepayer.models.FareHistoryModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FareHistoryActivity extends AppCompatActivity {

    protected @BindView(R.id.history_recycler)
    RecyclerView fareRecycler;
    protected @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ApiService mApiService;
    private PreferenceManager prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farehistory_layout);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mApiService = ApiUtils.getApiService();
        prefs = new PreferenceManager(this);
        /*fareRecycler.setHasFixedSize(false);*/
        fareRecycler.setLayoutManager(new LinearLayoutManager(this));
        syncHistory();
    }

    private void setHistory(List list) {
        final FareRecyclerAdapter fareRecyclerAdapter = new FareRecyclerAdapter(list);
        fareRecycler.setAdapter(fareRecyclerAdapter);
    }

    //History ArrayList
    private void syncHistory() {
        List<HistoryResponse> historyResponses = new ArrayList<>();
        Call<List<HistoryResponse>> call = mApiService.getHistoryResponse(prefs.getpaymentPhone());
        call.enqueue(new Callback<List<HistoryResponse>>() {
            @Override
            public void onResponse(Call<List<HistoryResponse>> call, Response<List<HistoryResponse>> response) {
                setHistory(response.body());
            }

            @Override
            public void onFailure(Call<List<HistoryResponse>> call, Throwable t) {

            }
        });
    }
}
