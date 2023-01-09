package com.example.helloworld.AccountEntry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.helloworld.MainActivity;
import com.example.helloworld.NotAvailablePage;
import com.example.helloworld.R;

public class TermsAndAgreement extends AppCompatActivity {

    ImageView bckBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_agreement);

        bckBtn = findViewById(R.id.back_pressed);
        bckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TermsAndAgreement.super.onBackPressed();
            }
        });

    }
    public void onBackPressed(){
        TermsAndAgreement.super.onBackPressed();
    }
}