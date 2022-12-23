package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helloworld.Exercise.ArmsExercise;
import com.example.helloworld.Exercise.BackExercise;
import com.example.helloworld.Exercise.ChestExercise;
import com.example.helloworld.Settings.ThemeSettings;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ExerciseActivity extends AppCompatActivity {
    // Intents
    ImageView bckBtn;
    Button btn1, btn2, btn3, btn4, btn5, btn6;

    // Theme SharedPreferences
    private View exerciseParentView;
    private TextView exerciseTitleTV, exerciseCatTV, chestExeTV1, armsExeTV1, backExeTV1, coreExeTV1, legsExeTV1, fullExeTV1;

    private ThemeSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        //BackButtonPressed
        bckBtn = findViewById(R.id.back_pressed);
        bckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        //Categories Intent
        btn1 = findViewById(R.id.cat_btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChestExercise.class);
                startActivity(intent);
            }
        });

        btn2 = findViewById(R.id.cat_btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ArmsExercise.class);
                startActivity(intent);
            }
        });

        btn3 = findViewById(R.id.cat_btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BackExercise.class);
                startActivity(intent);
            }
        });

        btn4 = findViewById(R.id.cat_btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NotAvailablePage.class);
                startActivity(intent);
            }
        });

        btn5 = findViewById(R.id.cat_btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NotAvailablePage.class);
                startActivity(intent);
            }
        });

        btn6 = findViewById(R.id.cat_btn6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NotAvailablePage.class);
                startActivity(intent);
            }
        });

        //Button Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.BottonNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.nav_exe);
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

        // Theme SharedPreferences

        settings = (ThemeSettings) getApplication();

        initWidgets();
        loadSharedPreferences();
        updateThemeView();
    }
    private void initWidgets() {

        exerciseParentView = findViewById(R.id.exerciseParentView);
        exerciseTitleTV = findViewById(R.id.exerciseTitleTV);
        exerciseCatTV = findViewById(R.id.exerciseCatTV);
        chestExeTV1 = findViewById(R.id.chestExeTV1);
        armsExeTV1 = findViewById(R.id.armsExeTV1);
        backExeTV1 = findViewById(R.id.backExeTV1);
        coreExeTV1 = findViewById(R.id.coreExeTV1);
        legsExeTV1 = findViewById(R.id.legsExeTV1);
        fullExeTV1 = findViewById(R.id.fullExeTV1);
    }

    private void loadSharedPreferences() {

        SharedPreferences sharedPreferences = getSharedPreferences(ThemeSettings.PREFERENCES,MODE_PRIVATE);

        //Theme
        String theme = sharedPreferences.getString(ThemeSettings.CUSTOM_THEME, ThemeSettings.CUSTOM_THEME);
        settings.setCustomTheme(theme);
        updateThemeView();


        //Lang
        String lang = sharedPreferences.getString(ThemeSettings.CUSTOM_LANG, ThemeSettings.CUSTOM_LANG);
        settings.setCustomLang(lang);
        updateLangView();

        //Size
        String size = sharedPreferences.getString(ThemeSettings.CUSTOM_SIZE, ThemeSettings.CUSTOM_SIZE);
        settings.setCustomSize(size);

    }

    private void updateThemeView() {

        final int black = ContextCompat.getColor(this, R.color.black);
        final int bgblack = ContextCompat.getColor(this, R.color.light_black);
        final int bgwhite = ContextCompat.getColor(this, R.color.light_white);
        final int white = ContextCompat.getColor(this, R.color.light_white);

        if(settings.getCustomTheme().equals(ThemeSettings.DARK_THEME)){

            exerciseTitleTV.setTextColor(white);
            exerciseCatTV.setTextColor(white);
            exerciseParentView.setBackgroundColor(bgblack);

        }else{

            exerciseTitleTV.setTextColor(black);
            exerciseCatTV.setTextColor(black);
            exerciseParentView.setBackgroundColor(bgwhite);
        }
    }

    // Language View
    private void updateLangView() {
        if(settings.getCustomLang().equals(ThemeSettings.ENG_LANG)){

            exerciseTitleTV.setText("Exercise");
            exerciseCatTV.setText("Categories");
            chestExeTV1.setText("Chest Exercise");
            armsExeTV1.setText("Arms Exercise");
            backExeTV1.setText("Back Exercise");
            coreExeTV1.setText("Core &amp; Abdominal Exercise");
            legsExeTV1.setText("Legs Exercise");
            fullExeTV1.setText("Full Body Exercise");
            settings.setCustomLang(ThemeSettings.ENG_LANG);

        }else if (settings.getCustomLang().equals(ThemeSettings.TAG_LANG)){

            exerciseTitleTV.setText("Exercise");
            exerciseCatTV.setText("Categories");
            chestExeTV1.setText("Chest Exercise");
            armsExeTV1.setText("Arms Exercise");
            backExeTV1.setText("Back Exercise");
            coreExeTV1.setText("Core &amp; Abdominal Exercise");
            legsExeTV1.setText("Legs Exercise");
            fullExeTV1.setText("Full Body Exercise");
            settings.setCustomLang(ThemeSettings.TAG_LANG);

        }
    }

    // Back Button Intent
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}