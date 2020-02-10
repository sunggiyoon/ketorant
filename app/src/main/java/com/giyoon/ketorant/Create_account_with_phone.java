package com.giyoon.ketorant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Create_account_with_phone extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    private EditText mCountryCode;
    private EditText mPhoneNumber;

    private Button mGenerateBtn;
    private TextView mLoginFeedback;
    private ProgressBar mLoginProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_with_phone);

        mCountryCode = findViewById(R.id.countryListSpinner);
        mPhoneNumber = findViewById(R.id.edit_phone_number);

        mGenerateBtn = findViewById(R.id.bt_generate_otp);
        mLoginFeedback = findViewById(R.id.login_form_feedback);
        mLoginProgress = findViewById(R.id.loginProgressBar);



        mGenerateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String countryCode = mCountryCode.getText().toString();
                String phoneNumber = mPhoneNumber.getText().toString();

                if(countryCode == null || phoneNumber == null ){
                    mLoginFeedback.setVisibility(View.VISIBLE);
                    mLoginFeedback.setText("Please fill in the form to continue");
                }else{
                    mLoginProgress.setVisibility(View.VISIBLE);
                    mGenerateBtn.setEnabled(false);

                }
            }
        });
    }
}
