package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {

    Button profbtn;
    ImageView bckBtn, btn1, btn2, btn3, btn4, btn5, btn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Back Button Intent
        bckBtn = findViewById(R.id.back_pressed);
        bckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        //Profile Button Intent
        profbtn = findViewById(R.id.Profilebtn);
        profbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NotAvailablePage.class);
                startActivity(intent);
            }
        });

        //Settings Button Intent
        btn1 = findViewById(R.id.settings_btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NotAvailablePage.class);
                startActivity(intent);
            }
        });

        btn2 = findViewById(R.id.settings_btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NotAvailablePage.class);
                startActivity(intent);
            }
        });

        btn3 = findViewById(R.id.settings_btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NotAvailablePage.class);
                startActivity(intent);
            }
        });

        btn4 = findViewById(R.id.settings_btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NotAvailablePage.class);
                startActivity(intent);
            }
        });

        btn5 = findViewById(R.id.settings_btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NotAvailablePage.class);
                startActivity(intent);
            }
        });

        btn6 = findViewById(R.id.settings_btn6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NotAvailablePage.class);
                startActivity(intent);
            }
        });

        //Bottom Navigation Intent
        BottomNavigationView bottomNavigationView = findViewById(R.id.BottonNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.nav_set);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_bmi:
                        startActivity(new Intent(getApplicationContext()
                                ,BMIActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_cal:
                        startActivity(new Intent(getApplicationContext()
                                , ExtraActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_vid:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_exe:
                        startActivity(new Intent(getApplicationContext()
                                ,ExerciseActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_set:
                        startActivity(new Intent(getApplicationContext()
                                ,SettingsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}