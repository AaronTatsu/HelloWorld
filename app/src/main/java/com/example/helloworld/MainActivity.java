package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Main Activity Variables
    private ViewPager2 viewPager2;
    private List<Video> videoList;
    private VideoAdapter adapter;

    // Intent
    TextView augmentedReality, videoScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // View Pager Functions
        videoList = new ArrayList<>();
        viewPager2 = findViewById(R.id.viewPager2);

        videoList.add(new Video("android.resource://" + getPackageName() + "/" + R.raw.a, "Tasty and Easy Healthy Meal ", "    This Video Shows A Healty Meal That You May Enjoy. #Mealprep #weightloss #Healthy"));
        videoList.add(new Video("android.resource://" + getPackageName() + "/" + R.raw.b, "Healthy Granola Bars | Quick and easy Protein Bars", "   If you're looking for the quickest healthy snack to make for a busy week ahead, these granola bars are for you! Loaded with oats and dry fruits, these granola bars are not only easy to make but also taste absolutely delicious!"));
        videoList.add(new Video("android.resource://" + getPackageName() + "/" + R.raw.c, "Easy, Healthy, High Protein Breakfast Sandwich", "   Hey guys, Fithackster back at it again with another recipe video. In today's video, I will be showing you how to make an easy high protein and low calorie breakfast recipe that will keep you full and fuel your body for a great start to your day!"));

        adapter = new VideoAdapter(videoList);
        viewPager2.setAdapter(adapter);

        // Intent

        augmentedReality = findViewById(R.id.augmentedReality);
        augmentedReality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NotAvailablePage.class);
                startActivity(intent);
            }
        });

        videoScreen = findViewById(R.id.videoScreen);
        videoScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        // Bottom Navigation Intent
        BottomNavigationView bottomNavigationView = findViewById(R.id.BottonNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.nav_vid);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_bmi:
                        startActivity(new Intent(getApplicationContext()
                                , BMIActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_cal:
                        startActivity(new Intent(getApplicationContext()
                                , ToDoListActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_vid:
                        startActivity(new Intent(getApplicationContext()
                                , MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_exe:
                        startActivity(new Intent(getApplicationContext()
                                , ExerciseActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_set:
                        startActivity(new Intent(getApplicationContext()
                                , SettingsActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }
    public void onBackPressed() {
        Toast.makeText(MainActivity.this, "Press Home Button to exit", Toast.LENGTH_SHORT).show();
    }
}