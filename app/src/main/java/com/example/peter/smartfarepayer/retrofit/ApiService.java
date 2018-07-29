package com.example.peter.smartfarepayer.retrofit;

import com.example.peter.smartfarepayer.retrofit.model.FareResponseModel;
import com.example.peter.smartfarepayer.retrofit.model.MpesaResponseModel;
import com.example.peter.smartfarepayer.retrofit.model.PaymentResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("Retrofit/getFare.php")
    @FormUrlEncoded
    Call<FareResponseModel> getFareRate(@Field("number_plate") String numberPlate);

    @POST("Retrofit/mpesa_Api.php")
    @FormUrlEncoded
    Call<MpesaResponseModel> getMpesaApi(@Field("phone_no") String phoneNumber, @Field("paybill")
            String paybill,@Field("amount")int amount);

    @POST("Retrofit/getPaymentResponse.php")
    @FormUrlEncoded
    Call<PaymentResponse> getPaymentResponse(@Field("merchant_id") String merchant_id, @Field
            ("numberplate") String numberPlate, @Field("seatNo") String seatNo);
  /*  @GET("Retrofit/mpesa_Api.php")
    Call<ResponseBody> getMpesaApi();*/

    /*@GET("Retrofit/getFare.php")
    Call<FareResponseModel>getFareRate(@Query());*/
}
