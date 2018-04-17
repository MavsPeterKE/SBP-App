package com.example.peter.smartfarepayer.viewModel;

public class FareHistoryModel {
    String busSacco;
    String travelDate;
    String travelTime;
    String fareAmount;

    public FareHistoryModel() {
    }

    public FareHistoryModel(String busSacco, String travelDate, String travelTime, String fareAmount) {
        this.busSacco = busSacco;
        this.travelDate = travelDate;
        this.travelTime = travelTime;
        this.fareAmount = fareAmount;
    }

    public String getBusSacco() {
        return busSacco;
    }

    public void setBusSacco(String busSacco) {
        this.busSacco = busSacco;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(String travelDate) {
        this.travelDate = travelDate;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
    }

    public String getFareAmount() {
        return fareAmount;
    }

    public void setFareAmount(String fareAmount) {
        this.fareAmount = fareAmount;
    }
}
