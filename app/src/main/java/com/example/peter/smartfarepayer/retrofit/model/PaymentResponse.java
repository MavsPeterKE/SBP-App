package com.example.peter.smartfarepayer.retrofit.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentResponse {

    @SerializedName("merchant_id")
    @Expose
    private String merchantId;
    @SerializedName("phone_no")
    @Expose
    private String phoneNo;
    @SerializedName("vehicle_number")
    @Expose
    private String vehicleNumber;
    @SerializedName("amount_paid")
    @Expose
    private String amountPaid;
    @SerializedName("seat_paid")
    @Expose
    private String seatPaid;
    @SerializedName("result_code")
    @Expose
    private String resultCode;
    @SerializedName("result_description")
    @Expose
    private String resultDescription;
    @SerializedName("mpesa_refno")
    @Expose
    private String mpesaRefno;
    @SerializedName("transaction_date")
    @Expose
    private String transactionDate;
    @SerializedName("checkout_id")
    @Expose
    private String checkoutId;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
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

    public String getSeatPaid() {
        return seatPaid;
    }

    public void setSeatPaid(String seatPaid) {
        this.seatPaid = seatPaid;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDescription() {
        return resultDescription;
    }

    public void setResultDescription(String resultDescription) {
        this.resultDescription = resultDescription;
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

    public String getCheckoutId() {
        return checkoutId;
    }

    public void setCheckoutId(String checkoutId) {
        this.checkoutId = checkoutId;
    }

}