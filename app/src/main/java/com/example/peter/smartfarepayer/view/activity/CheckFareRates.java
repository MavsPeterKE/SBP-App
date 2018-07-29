package com.example.peter.smartfarepayer.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.peter.smartfarepayer.R;
import com.example.peter.smartfarepayer.models.SaccoData;
import com.example.peter.smartfarepayer.retrofit.ApiService;
import com.example.peter.smartfarepayer.retrofit.model.FareResponseModel;
import com.example.peter.smartfarepayer.utils.ApiUtils;
import com.example.peter.smartfarepayer.viewModel.SaccoViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckFareRates extends AppCompatActivity implements NavigationView
        .OnNavigationItemSelectedListener {
    SaccoViewModel saccoViewModel;
    @BindView(R.id.numberPLate)
    EditText mEditTextNumberPLate;
    @BindView(R.id.seatNo)
    EditText mEditTextSeatNo;
    private ApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payfare);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        mApiService = ApiUtils.getApiService();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string
                .navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        saccoViewModel = ViewModelProviders.of(this).get(SaccoViewModel.class);
        SaccoData data = new SaccoData("Peter", "Name", "Gty");
        saccoViewModel.insert(data);
        Log.e("onCreate: ", "done");

    }

    @OnClick(R.id.btPayFare)
    void startPayment() {
        //startActivity(new Intent(this,ConfirmPaymentActivity.class));
        String numberPLate = mEditTextNumberPLate.getText().toString().trim();
        String seatNO = mEditTextNumberPLate.getText().toString().trim();
        if (!numberPLate.isEmpty() && !seatNO.isEmpty()) {
            getFareAmount(numberPLate);
        }


    }


    private void getFareAmount(String numberPLate) {
        Toast.makeText(this, "Loading Request", Toast.LENGTH_SHORT).show();
        mApiService.getFareRate(numberPLate).enqueue(new Callback<FareResponseModel>() {
            @Override
            public void onResponse(Call<FareResponseModel> call, Response<FareResponseModel>
                    response) {
                if (response.isSuccessful()) {
                    Log.e("onResponse: ",response.body().getSaccoId());
                 /* //  response.body().
                    Toast.makeText(CheckFareRates.this, "Server Comm Successful" + response.body
                            ().getSaccoName(), Toast.LENGTH_SHORT).show();
                    Log.e("onResponse__", response.body().toString());*/
                } else {
                    Toast.makeText(CheckFareRates.this, "Server Problem", Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<FareResponseModel> call, Throwable t) {
                Toast.makeText(CheckFareRates.this, t.toString(), Toast
                        .LENGTH_SHORT).show();
                t.printStackTrace();

                Log.d("fare",t.toString());
            }
        });
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
            startActivity(new Intent(this, ConfirmPaymentActivity.class));
        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent(this, FareHistoryActivity.class));

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(this, ComplainActivity.class));
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
