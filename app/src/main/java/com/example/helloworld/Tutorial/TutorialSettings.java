package com.example.helloworld.Tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.helloworld.R;

public class TutorialSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_settings);
    }
    public void onBackPressed(){
        TutorialSettings.super.onBackPressed();
    }
}