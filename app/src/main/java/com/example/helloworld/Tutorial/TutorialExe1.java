package com.example.helloworld.Tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.helloworld.R;

public class TutorialExe1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_exe1);
    }
    public void onBackPressed(){
        TutorialExe1.super.onBackPressed();
    }
}