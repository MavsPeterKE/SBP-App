package com.example.peter.smartfarepayer.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peter.smartfarepayer.R;
import com.example.peter.smartfarepayer.retrofit.ApiService;
import com.example.peter.smartfarepayer.retrofit.model.FareResponseModel;
import com.example.peter.smartfarepayer.retrofit.model.MpesaResponseModel;
import com.example.peter.smartfarepayer.retrofit.model.PaymentResponse;
import com.example.peter.smartfarepayer.utils.ApiUtils;
import com.example.peter.smartfarepayer.utils.Constants;
import com.example.peter.smartfarepayer.utils.PreferenceManager;
import com.example.peter.smartfarepayer.utils.UtilMethods;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmPaymentActivity extends AppCompatActivity {
    @BindView(R.id.plateNo)TextView tvNumberPLate;
    @BindView(R.id.saccoName)TextView tvSaccoName;
    @BindView(R.id.seatNo)TextView tvSeatNo;
    @BindView(R.id.fareAmount)TextView tvFareAmount;
    @BindView(R.id.mpesaloading)ProgressBar mpesaloading;
    private ApiService mApiService;
    int fareAmount;
    private PreferenceManager mPreferenceManager;
    private MpesaResponseModel mMpesaResponseModel;
    private FareResponseModel fareResponseModel;
    private String seatNo;
    UtilMethods mUtilMethods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_confirmation_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        mApiService = ApiUtils.getApiService();
        mPreferenceManager = new PreferenceManager(this);
        mUtilMethods = new UtilMethods();
        setViews();
    }

    private void setViews() {
      fareResponseModel = (FareResponseModel)
                getIntent().getSerializableExtra("response");
        seatNo = getIntent().getStringExtra("seatNo");
        if (seatNo.length()>2 && seatNo.contains(",")){
            String [] seats = seatNo.split(",");
            if (seats.length>1){
                if (Integer.parseInt(fareResponseModel.getFare())!=0) {
                    fareAmount = seats.length * Integer.parseInt(fareResponseModel.getFare());
                }
            }
        }else {
            fareAmount = Integer.parseInt(fareResponseModel.getFare()) ;
        }
        tvSaccoName.setText(fareResponseModel.getSaccoName());
        tvFareAmount.setText(""+fareAmount);
        tvSeatNo.setText(seatNo);
        tvNumberPLate.setText(fareResponseModel.getNumberPlate());
    }

    @OnClick(R.id.makePayment)
            void makePaymentAction (){

        mpesaloading.setVisibility(View.VISIBLE);
        triggerMpesaAPI();
    }

    @OnClick(R.id.cancel_payment)
    void cancelPayment(){startActivity(new Intent(this,PayFareActivity.class));}

    private void triggerMpesaAPI() {
        mApiService.getMpesaApi(Constants.TEST_PHONE,Constants.TEST_PAYBILL,fareAmount).enqueue(new Callback<MpesaResponseModel>() {
            @Override
            public void onResponse(Call<MpesaResponseModel> call, Response<MpesaResponseModel>
                    response) {
                if (response.isSuccessful()){
                    Toast.makeText(ConfirmPaymentActivity.this, "Input Pin to Pay", Toast.LENGTH_SHORT).show();
                    mMpesaResponseModel = response.body();
                    //getPaymentResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<MpesaResponseModel> call, Throwable t) {

            }
        });
    }

    private void getPaymentResponse(MpesaResponseModel mpesaResponseModel){
        mApiService.getPaymentResponse(mpesaResponseModel.getMerchantRequestID(),
                fareResponseModel.getNumberPlate(), seatNo).enqueue(new Callback<PaymentResponse>
                () {


            @Override
            public void onResponse(Call<PaymentResponse> call, Response<PaymentResponse> response) {
                mpesaloading.setVisibility(View.GONE);
                showPaymentFeedBack(response.body());

            }

            @Override
            public void onFailure(Call<PaymentResponse> call, Throwable t) {

            }
        });
    }

    private void showPaymentFeedBack(PaymentResponse response) {
        if (response!=null) {
            if (response.getResultCode().equals("0")) {
                String msg = response.getAmountPaid() +".00"+ "Paid to"+ response.getVehicleNumber()
                +"For Seat"+" "+response.getSeatPaid();
                mUtilMethods.successDialog(this,msg);
            } else {
                mUtilMethods.errorDialog(this,response.getResultDescription());
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mMpesaResponseModel !=null) {
                    getPaymentResponse(mMpesaResponseModel);
                    Toast.makeText(ConfirmPaymentActivity.this, "Response Found", Toast.LENGTH_SHORT).show();
                }
            }
        }, 5000);

    }
}
