package com.example.peter.smartfarepayer.utils;

import android.content.Context;

import cn.pedant.SweetAlert.SweetAlertDialog;

public  class UtilMethods {

    public  void errorDialog(Context context, String message){
        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Payment Error")
                .setContentText(message)
                .show();
    }

    public  void successDialog(Context context,String message){
        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Payment Successfull")
                .setContentText(message)
                .show();
    }


    public void warningDialog(Context context){
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("Won't be able to recover this file!")
                .setConfirmText("Yes,delete it!")
                .show();
    }


}
