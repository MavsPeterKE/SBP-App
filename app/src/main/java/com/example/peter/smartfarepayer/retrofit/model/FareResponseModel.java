package com.example.peter.smartfarepayer.retrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class FareResponseModel implements Serializable {

    @SerializedName("sacco_id")
    @Expose
    private String saccoId;
    @SerializedName("sacco_name")
    @Expose
    private String saccoName;
    @SerializedName("number_plate")
    @Expose
    private String numberPlate;
    @SerializedName("fare")
    @Expose
    private String fare;
    @SerializedName("capacity")
    @Expose
    private String capacity;
    @SerializedName("paybill")
    @Expose
    private String paybill;

    public String getSaccoId() {
        return saccoId;
    }

    public void setSaccoId(String saccoId) {
        this.saccoId = saccoId;
    }

    public String getSaccoName() {
        return saccoName;
    }

    public void setSaccoName(String saccoName) {
        this.saccoName = saccoName;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getPaybill() {
        return paybill;
    }

    public void setPaybill(String paybill) {
        this.paybill = paybill;
    }

}