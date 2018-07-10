package com.example.peter.smartfarepayer.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "sacco_data_table")
public class SaccoData {
    @PrimaryKey
    @ColumnInfo(name = "saco_name")
    @NonNull
    public String mSaccoName;

    @ColumnInfo(name = "fare_amount")
    public String mFareAmount;

    @ColumnInfo(name = "contact_number")
    public String mContactNUmber;

    public SaccoData(String mSaccoName, String mFareAmount, String mContactNUmber) {
        this.mSaccoName = mSaccoName;
        this.mFareAmount = mFareAmount;
        this.mContactNUmber = mContactNUmber;
    }

    public String getmSaccoName() {
        return mSaccoName;
    }

    public void setmSaccoName(String mSaccoName) {
        this.mSaccoName = mSaccoName;
    }

    public String getmFareAmount() {
        return mFareAmount;
    }

    public void setmFareAmount(String mFareAmount) {
        this.mFareAmount = mFareAmount;
    }

    public String getmContactNUmber() {
        return mContactNUmber;
    }

    public void setmContactNUmber(String mContactNUmber) {
        this.mContactNUmber = mContactNUmber;
    }
}
