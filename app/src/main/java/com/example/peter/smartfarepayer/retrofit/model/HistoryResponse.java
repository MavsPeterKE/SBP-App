package com.example.peter.smartfarepayer.retrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryResponse {

    @SerializedName("sacco_id")
    @Expose
    private String saccoId;
    @SerializedName("sacco_name")
    @Expose
    private String saccoName;
    @SerializedName("vehicle_number")
    @Expose
    private String vehicleNumber;
    @SerializedName("amount_paid")
    @Expose
    private String amountPaid;
    @SerializedName("mpesa_refno")
    @Expose
    private String mpesaRefno;
    @SerializedName("transaction_date")
    @Expose
    private String transactionDate;

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

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getMpesaRefno() {
        return mpesaRefno;
    }

    public void setMpesaRefno(String mpesaRefno) {
        this.mpesaRefno = mpesaRefno;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

}
