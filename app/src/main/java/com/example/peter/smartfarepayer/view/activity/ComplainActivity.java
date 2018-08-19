package com.example.peter.smartfarepayer.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.peter.smartfarepayer.R;
import com.example.peter.smartfarepayer.retrofit.ApiService;
import com.example.peter.smartfarepayer.retrofit.model.ComplainResponse;
import com.example.peter.smartfarepayer.utils.ApiUtils;
import com.example.peter.smartfarepayer.utils.PreferenceManager;
import com.example.peter.smartfarepayer.utils.UtilMethods;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComplainActivity extends AppCompatActivity {
    @BindView(R.id.checkboxLoudMusic)CheckBox checkLoudMusic;
    @BindView(R.id.checkboxSpeeding)CheckBox checkSpeeding;
    @BindView(R.id.checkboxExcess)CheckBox checkExcess;
    @BindView(R.id.checkboxRudeDriver)CheckBox checkRudeDriver;
    ArrayList<String>  complains = new ArrayList<>();
    ApiService myApi;
    PreferenceManager prefs;
    UtilMethods mUtilMethods;
    SweetAlertDialog mSweetAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_complain_layout);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myApi = ApiUtils.getApiService();
        prefs = new PreferenceManager(this);
        mUtilMethods = new UtilMethods(this);

        mSweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        mSweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        mSweetAlertDialog.setTitleText("Loading");
        mSweetAlertDialog.setCancelable(false);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
    }

    @OnClick(R.id.cardLoudMusic)void reportMusic(){
        if (!checkLoudMusic.isChecked()){
            checkLoudMusic.setChecked(true);
            complains.add("Loud music. ") ;
        }else {
            checkLoudMusic.setChecked(false);
            complains.remove("Loud music. ");
        }
    }

    @OnClick(R.id.cardSpeeding)void reportSpeeding(){
        if (!checkSpeeding.isChecked()){
            checkSpeeding.setChecked(true);
            complains.add("Driver Speeding");
        }else {
            checkSpeeding.setChecked(false);
           complains.remove("Driver Speeding");
        }
    }

    @OnClick(R.id.cardExcess)void reportExcess(){
        if (!checkExcess.isChecked()){
            checkExcess.setChecked(true);
           complains.add("Carrying Excess Passengers. ");
        }else {
            checkExcess.setChecked(false);
            complains.remove("Carrying Excess Passengers. ");
        }
    }
    @OnClick(R.id.cardRudeDriver)void reportRudeDriver(){
        if (!checkRudeDriver.isChecked()){
            checkRudeDriver.setChecked(true);
           complains.add("Poor customer Service. Rude driver/Conductor. ");
        }else {
            checkRudeDriver.setChecked(false);
            complains.remove("Poor customer Service. Rude driver/Conductor. ");
        }
    }

    @OnClick(R.id.postComplain)void postComplain(){
       mSweetAlertDialog.show();
        if (checkExcess.isChecked() || checkSpeeding.isChecked() || checkLoudMusic.isChecked() ||
                checkRudeDriver.isChecked()) {
                 myApi.postComplain(getComplain(),prefs.getVehicleNumberPlate(),prefs.getSaccoId()).enqueue(new Callback<ComplainResponse>() {
            @Override
            public void onResponse(Call<ComplainResponse> call, Response<ComplainResponse> response) {
               if (response.isSuccessful()){
                   if (mSweetAlertDialog.isShowing()){
                       mSweetAlertDialog.dismiss();
                   }
                   if (response.body().getResultMsg().equals("success")){
                       mUtilMethods.successDialog("Complain to "+prefs.getVehicleNumberPlate(),"Complain Posted");
                   }else {
                       Toast.makeText(ComplainActivity.this, "Problem Posting", Toast.LENGTH_SHORT).show();
                   }
               }
            }

            @Override
            public void onFailure(Call<ComplainResponse> call, Throwable t) {

            }
        });
        }else {
            Toast.makeText(this, "No complain Selected", Toast.LENGTH_SHORT).show();
        }

    }

    private String getComplain() {
        String complain="";
        Log.e("getComplain: ",complains.toString() );
        for (String body:complains){
            complain+=body;
        }
        return complain;
    }

    @OnClick(R.id.complainStatus)void checkCompainStatus(){
        Toast.makeText(this, "Complain has been Staged", Toast.LENGTH_SHORT).show();
    }


}
