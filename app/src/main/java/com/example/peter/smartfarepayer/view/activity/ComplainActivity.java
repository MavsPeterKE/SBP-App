package com.example.peter.smartfarepayer.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.peter.smartfarepayer.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ComplainActivity extends AppCompatActivity {
    @BindView(R.id.checkboxLoudMusic)CheckBox checkLoudMusic;
    @BindView(R.id.checkboxSpeeding)CheckBox checkSpeeding;
    @BindView(R.id.checkboxExcess)CheckBox checkExcess;
    @BindView(R.id.checkboxRudeDriver)CheckBox checkRudeDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_complain_layout);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
    }

    @OnClick(R.id.cardLoudMusic)void reportMusic(){
        if (!checkLoudMusic.isChecked()){
            checkLoudMusic.setChecked(true);
        }else {
            checkLoudMusic.setChecked(false);
        }
    }

    @OnClick(R.id.cardSpeeding)void reportSpeeding(){
        if (!checkSpeeding.isChecked()){
            checkSpeeding.setChecked(true);
        }else {
            checkSpeeding.setChecked(false);
        }
    }

    @OnClick(R.id.cardExcess)void reportExcess(){
        if (!checkExcess.isChecked()){
            checkExcess.setChecked(true);
        }else {
            checkExcess.setChecked(false);
        }
    }
    @OnClick(R.id.cardRudeDriver)void reportRudeDriver(){
        if (!checkRudeDriver.isChecked()){
            checkRudeDriver.setChecked(true);
        }else {
            checkRudeDriver.setChecked(false);
        }
    }

    @OnClick(R.id.postComplain)void postComplain(){
        Toast.makeText(this, "Complain Posted Successfully", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.complainStatus)void checkCompainStatus(){
        Toast.makeText(this, "Complain has been Staged", Toast.LENGTH_SHORT).show();
    }


}
