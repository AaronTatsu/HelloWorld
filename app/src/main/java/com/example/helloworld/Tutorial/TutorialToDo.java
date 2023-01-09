package com.example.helloworld.Tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.helloworld.NotAvailablePage;
import com.example.helloworld.R;

public class TutorialToDo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_to_do);
    }
    public void onBackPressed(){
        TutorialToDo.super.onBackPressed();
    }
}