package com.example.peter.smartfarepayer.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peter.smartfarepayer.R;
import com.example.peter.smartfarepayer.models.SaccoData;
import com.example.peter.smartfarepayer.retrofit.ApiService;
import com.example.peter.smartfarepayer.retrofit.model.FareResponseModel;
import com.example.peter.smartfarepayer.utils.ApiUtils;
import com.example.peter.smartfarepayer.utils.PreferenceManager;
import com.example.peter.smartfarepayer.viewModel.SaccoViewModel;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.style.ThreeBounce;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayFareActivity extends AppCompatActivity implements NavigationView
        .OnNavigationItemSelectedListener {
    SaccoViewModel saccoViewModel;
    @BindView(R.id.numberPLate)
    EditText mEditTextNumberPLate;
    @BindView(R.id.seatNo)
    EditText mEditTextSeatNo;
    @BindView(R.id.spin_kit)
    SpinKitView loading;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    private ApiService mApiService;
    private PreferenceManager prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payfare);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        mApiService = ApiUtils.getApiService();
        prefs = new PreferenceManager(this);
        setViews();
        saccoViewModel = ViewModelProviders.of(this).get(SaccoViewModel.class);
        SaccoData data = new SaccoData("Peter", "Name", "Gty");
        saccoViewModel.insert(data);
        Log.e("onCreate: ", "done");

    }

    private void setViews() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string
                .navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        TextView tvPayPhone = (TextView) header.findViewById(R.id.payphone);
        tvPayPhone.setText("Payment Phone : " + prefs.getpaymentPhone());
    }

    @OnClick(R.id.btPayFare)
    void startPayment() {
        String numberPLate = mEditTextNumberPLate.getText().toString().trim();
        String seatNO = mEditTextSeatNo.getText().toString().trim();
        if (seatNO.length()>2 && seatNO.contains(",") || seatNO.length()<=2) {
            if (numberPLate.isEmpty() || numberPLate.equals("")) {
                mEditTextNumberPLate.setError("Required");
            }
            if (numberPLate.isEmpty() || numberPLate.equals("")) {
                mEditTextSeatNo.setError("Required");
            }
            if (!numberPLate.isEmpty() && !seatNO.isEmpty()) {
                mEditTextSeatNo.setError(null);
                mEditTextNumberPLate.setError(null);
                getFareAmount(numberPLate, seatNO);
            }
        }else {
            Toast.makeText(this, "Multiple Seats. Separate With Comma", Toast.LENGTH_SHORT).show();
        }


    }

    private void getFareAmount(String numberPLate, final String seatNo) {
        loading.setVisibility(View.VISIBLE);
        ThreeBounce threeBounce = new ThreeBounce();
        loading.setIndeterminateDrawable(threeBounce);
        mApiService.getFareRate(numberPLate).enqueue(new Callback<FareResponseModel>() {
            @Override
            public void onResponse(Call<FareResponseModel> call, Response<FareResponseModel>
                    response) {
                if (response.isSuccessful()) {
                    loading.setVisibility(View.GONE);
                    if (response.body()!=null) {
                        ArrayList<String> invalidSeats =  checkSeatsValidity(response,seatNo);
                        if (invalidSeats.size() == 0){
                            sendPaymentData(response,seatNo);
                        }else {
                            Toast.makeText(PayFareActivity.this, "Invalid Seats "+invalidSeats.toString(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(PayFareActivity.this, "Vehicle Not Registered", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(PayFareActivity.this, "Server Problem", Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<FareResponseModel> call, Throwable t) {
                Toast.makeText(PayFareActivity.this, t.toString(), Toast
                        .LENGTH_SHORT).show();
                t.printStackTrace();

                Log.e("fare",t.toString());
            }
        });
    }

    private ArrayList<String> checkSeatsValidity(Response<FareResponseModel> response, String seatNo) {
        FareResponseModel fareResponseModel = response.body();
        ArrayList<String> wrongSeats = new ArrayList<>();
        if (seatNo.length() > 2 && seatNo.contains(",")) {
            String[] seats = seatNo.split(",");
            if (seats.length > 1) {
                for (String seat : seats) {
                    if (Integer.parseInt(seat) > Integer.parseInt(fareResponseModel.getCapacity
                            ())) {
                        wrongSeats.add(seat);
                    }
                }
            }
        } else {
            if (Integer.parseInt(seatNo) > Integer.parseInt(fareResponseModel.getCapacity())) {
                wrongSeats.add(seatNo);
            }
        }
        return wrongSeats;
    }

    private void sendPaymentData(Response<FareResponseModel> response, String seatNo) {
        Intent dataBit = new Intent(PayFareActivity.this, ConfirmPaymentActivity.class);
        dataBit.putExtra("response", response.body());
        dataBit.putExtra("seatNo", seatNo);
        startActivity(dataBit);
    }

    @OnClick(R.id.cardTrackFare)
    void startFareHistory() {
        startActivity(new Intent(this, FareHistoryActivity.class));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.payfare, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this,SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       /* if (id == R.id.nav_camera) {
            // Handle the camera action
            startActivity(new Intent(this, ConfirmPaymentActivity.class));
        }*/ if (id == R.id.nav_gallery) {
            startActivity(new Intent(this, FareHistoryActivity.class));

        /*} else if (id == R.id.nav_slideshow) {

        } */}else if (id == R.id.nav_manage) {
            startActivity(new Intent(this, ComplainActivity.class));
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
