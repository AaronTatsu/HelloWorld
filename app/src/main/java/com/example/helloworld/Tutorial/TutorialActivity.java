package com.example.helloworld.Tutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.MainActivity;
import com.example.helloworld.R;

import me.relex.circleindicator.CircleIndicator;

public class TutorialActivity extends AppCompatActivity {

    TextView skipTutorial;

    CircleIndicator circleIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        // For Skipping Tutorial
        skipTutorial = findViewById(R.id.skipTutorial);
        skipTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TutorialActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // To be able to appear on first install
        SharedPreferences preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        String firstTime = preferences.getString("FirstTimeInstall", "");

        if(firstTime.equals("Yes")){
            Intent intent = new Intent(TutorialActivity.this, MainActivity.class);
            startActivity(intent);
        }else{

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("FirstTimeInstall", "Yes");
            editor.apply();
        }

        // View Pager
        ViewPager viewPager = findViewById(R.id.tutorialPager);
        circleIndicator = findViewById(R.id.circleIndicator);

        TutorialAdapter adapter = new TutorialAdapter(this);
        viewPager.setAdapter(adapter);

        circleIndicator.setViewPager(viewPager);

    }
    public void onBackPressed() {
        Toast.makeText(TutorialActivity.this, "Press Finish to exit tutorial!", Toast.LENGTH_SHORT).show();
    }
}