package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.Tutorial.TutorialVideoScreen;
import com.example.helloworld.VideoScreen.Video;
import com.example.helloworld.VideoScreen.VideoAdapter;
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

    ImageView tutorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // View Pager Functions
        videoList = new ArrayList<>();
        viewPager2 = findViewById(R.id.viewPager2);

        videoList.add(new Video("android.resource://" + getPackageName() + "/" + R.raw.newstandpos, "Individual Stunts", "    This video shows individual stunts which is turk stand and rocking chair."));
        videoList.add(new Video("android.resource://" + getPackageName() + "/" + R.raw.standpos3, "Basic Stand Position", "    This video shows basic stand position in gymnastic which is egg roll or tuck roll sideward, log roll. human ball and jump to full turn."));
        videoList.add(new Video("android.resource://" + getPackageName() + "/" + R.raw.standpos4, "Basic Stand Position", "    This video shows basic stand position in gymnastic which is dog walk, frog kick, lame dog, frog jump, inc worm walk, crab walk and hip walk."));
        videoList.add(new Video("android.resource://" + getPackageName() + "/" + R.raw.newstandfirst, "Basic Position Standing", "    This video shows basic standing position in gymnastic which is feet together, stride position, lunge position, half-knee bend and full-knees bend."));
        videoList.add(new Video("android.resource://" + getPackageName() + "/" + R.raw.handposition, "Basic Hand Position", "    This video shows basic hand position in gymnastic which is hands on waists, hands on chest, hands on shoulder, hands on neck and hands on hips."));
        videoList.add(new Video("android.resource://" + getPackageName() + "/" + R.raw.armsposition, "Basic Arms Position", "    This video shows basic arms position in gymnastic which is arms forward, arms sideward, arms upward, arms in oblique position and front arms in oblique position."));
        videoList.add(new Video("android.resource://" + getPackageName() + "/" + R.raw.armsposition2, "Basic Arms Position", "    This video shows basic arms position in gymnastic which is back arms in oblique position, sideward arms in oblique position, upward arms in oblique position, arms in T position and Reverse T."));
        videoList.add(new Video("android.resource://" + getPackageName() + "/" + R.raw.newarmsupport, "Arms Support Position", "    This video shows arm supports which is supine or back arm support, prone or front arm support and side arm support."));
        videoList.add(new Video("android.resource://" + getPackageName() + "/" + R.raw.sitting, "Basic Position Sitting", "    This video shows basic sitting position in gymnastic which is long sitting, hook sitting, long sitting rest, tuck sitting, stride sitting, side sitting, hurdle sitting, heels sit, cross sitting and frog sitting,."));
        videoList.add(new Video("android.resource://" + getPackageName() + "/" + R.raw.kneeling, "Basic Position Kneeling", "    This video shows basic kneeling position in gymnastic which is kneeling, stride kneeling, half-kneeling and kneeling position one leg extended."));
        videoList.add(new Video("android.resource://" + getPackageName() + "/" + R.raw.lying, "Basic Position Lying", "    This video shows basic lying position in gymnastic which is back or supine lying, front lying, side lying, hook lying position and tuck lying position."));
        videoList.add(new Video("android.resource://" + getPackageName() + "/" + R.raw.fourbased, "Basic Position Four Based", "    This video shows basic four based position in gymnastic which is dog stand position and bridge stand position."));
        videoList.add(new Video("android.resource://" + getPackageName() + "/" + R.raw.standpos2, "Basic Stand Position", "    This video shows basic stand position in gymnastic which is prone rocking, ankle hold, coffee grinder."));

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

        tutorial = findViewById(R.id.tutorial);
        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TutorialVideoScreen.class);
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