package com.example.helloworld.AccountEntry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.helloworld.R;

public class PrivacyPolicy extends AppCompatActivity {

    ImageView bckBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        bckBtn = findViewById(R.id.back_pressed);
        bckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrivacyPolicy.super.onBackPressed();
            }
        });
    }
    public void onBackPressed(){
        PrivacyPolicy.super.onBackPressed();
    }
}