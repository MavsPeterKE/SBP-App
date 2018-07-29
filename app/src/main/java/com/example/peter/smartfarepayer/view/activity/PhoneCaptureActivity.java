package com.example.peter.smartfarepayer.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.peter.smartfarepayer.R;
import com.example.peter.smartfarepayer.utils.PreferenceManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhoneCaptureActivity extends AppCompatActivity {
    @BindView(R.id.phoneNo_entry)
    EditText edPhoneInput;
    private PreferenceManager prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_slide5);
        ButterKnife.bind(this);
        prefs = new PreferenceManager(this);
        if(!prefs.getpaymentPhone().isEmpty()|| !prefs.getpaymentPhone().equals("")){
            startActivity(new Intent(this,PayFareActivity.class));
        }
    }

    @OnClick(R.id.setPhone)
    void setPhoneNumber(){
        String phone = edPhoneInput.getText().toString().trim();
        if (phone.isEmpty() || phone.equals("")){
            edPhoneInput.setError("Required");
        }
        if (phone.length()<10){
            edPhoneInput.setError("Invalid PhoneNumber");
        }

        if (!phone.isEmpty() && !phone.equals("") && phone.length() == 10){
            prefs.setPhoneNo(phone);
            Toast.makeText(this, prefs.getpaymentPhone(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,PayFareActivity.class));
        }
    }
}
