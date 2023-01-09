package com.example.helloworld.Tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.helloworld.NotAvailablePage;
import com.example.helloworld.R;

public class TutorialBMI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_bmi);
    }
    public void onBackPressed(){
        TutorialBMI.super.onBackPressed();
    }
}