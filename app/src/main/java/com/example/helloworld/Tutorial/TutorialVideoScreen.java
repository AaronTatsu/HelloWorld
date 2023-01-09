package com.example.helloworld.Tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.helloworld.R;

public class TutorialVideoScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_video_screen);
    }
    public void onBackPressed(){
        TutorialVideoScreen.super.onBackPressed();
    }
}