package com.example.peter.smartfarepayer.utils;

import android.content.Context;

import cn.pedant.SweetAlert.SweetAlertDialog;

public  class UtilMethods {
    Context mContext;

    public UtilMethods(Context context) {
        mContext = context;
    }

    public  void errorDialog(String message){
        new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Payment Error")
                .setContentText(message)
                .show();
    }

    public  void successDialog(String message,String title){
        new SweetAlertDialog(mContext, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText(title)
                .setContentText(message)
                .show();
    }


    public void warningDialog(){
        new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("Won't be able to recover this file!")
                .setConfirmText("Yes,delete it!")
                .show();
    }

}
