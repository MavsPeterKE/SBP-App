package com.example.peter.smartfarepayer.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    Context context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_FIRST_TIME = "sbp_welcome";
    private static final String PREF_PHONE = "phone";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String PAYMENT_PHONE_NO = "default_phone";

    public PreferenceManager(Context context) {
        this.context = context;
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {

        SharedPreferences mySharedPreferences = context.getSharedPreferences(PREF_FIRST_TIME, Activity
                .MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.apply();
    }

    public boolean isFirstTimeLaunch() {
        SharedPreferences mySharedPreferences = context.getSharedPreferences(PREF_FIRST_TIME, Activity
                .MODE_PRIVATE);
        return mySharedPreferences.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setPhoneNo(String phoneNo) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences(PREF_PHONE, Activity
                .MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(PREF_PHONE, phoneNo);
        editor.apply();
    }

    public String getpaymentPhone() {
        SharedPreferences mySharedPreferences = context.getSharedPreferences(PREF_PHONE, Activity
                .MODE_PRIVATE);
        return mySharedPreferences.getString(PREF_PHONE, "");
    }

    public void setVehicleNumberPlate(String numberPlate) {

        SharedPreferences mySharedPreferences = context.getSharedPreferences("PaidVehicle", Activity
                .MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString("NumberPlate", numberPlate);
        editor.apply();
    }

    public void clearVehicleNumberPlate() {
        SharedPreferences mySharedPreferences = context.getSharedPreferences("PaidVehicle", Activity
                .MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public String getVehicleNumberPlate() {
        SharedPreferences mySharedPreferences = context.getSharedPreferences("PaidVehicle", Activity
                .MODE_PRIVATE);
        return mySharedPreferences.getString("NumberPlate","");
    }

    public void setSaccodId(String saccoId) {

        SharedPreferences mySharedPreferences = context.getSharedPreferences("SaccoId", Activity
                .MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString("id", saccoId);
        editor.apply();
    }

    public void clearVSaccoId() {
        SharedPreferences mySharedPreferences = context.getSharedPreferences("SaccoId", Activity
                .MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public String getSaccoId() {
        SharedPreferences mySharedPreferences = context.getSharedPreferences("SaccoId", Activity
                .MODE_PRIVATE);
        return mySharedPreferences.getString("id","");
    }

}
