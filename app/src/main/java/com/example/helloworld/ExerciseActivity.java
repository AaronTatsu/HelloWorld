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
import com.example.helloworld.Exercise.BodyExercise;
import com.example.helloworld.Exercise.ChestExercise;
import com.example.helloworld.Exercise.CoreExercise;
import com.example.helloworld.Exercise.LegsExercise;
import com.example.helloworld.Settings.ThemeSettings;
import com.example.helloworld.Tutorial.TutorialExercise;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ExerciseActivity extends AppCompatActivity {
    // Intents
    ImageView bckBtn, tutorial;
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

        //Tutorial Intent
        tutorial = findViewById(R.id.tutorial);
        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TutorialExercise.class);
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
                Intent intent = new Intent(getApplicationContext(), CoreExercise.class);
                startActivity(intent);
            }
        });

        btn5 = findViewById(R.id.cat_btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LegsExercise.class);
                startActivity(intent);
            }
        });

        btn6 = findViewById(R.id.cat_btn6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BodyExercise.class);
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
        updateSizeView();

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
            btn1.setText("Start Exercise >>");
            btn2.setText("Start Exercise >>");
            btn3.setText("Start Exercise >>");
            btn4.setText("Start Exercise >>");
            btn5.setText("Start Exercise >>");
            btn6.setText("Start Exercise >>");
            settings.setCustomLang(ThemeSettings.ENG_LANG);

        }else if (settings.getCustomLang().equals(ThemeSettings.TAG_LANG)){

            exerciseTitleTV.setText("Ehersisyo");
            exerciseCatTV.setText("Mga Kategorya");
            chestExeTV1.setText("Ehersisyo sa Dibdib");
            armsExeTV1.setText("Ehersisyo sa Kamay");
            backExeTV1.setText("Ehersisyo sa Likod");
            coreExeTV1.setText("Ehersisyo ng Tiyan at Bisig");
            legsExeTV1.setText("Ehersisyo sa Binti");
            fullExeTV1.setText("Buong Ehersisyo sa Katawan");
            btn1.setText("Simulan ang Ehersisyo >>");
            btn2.setText("Simulan ang Ehersisyo >>");
            btn3.setText("Simulan ang Ehersisyo >>");
            btn4.setText("Simulan ang Ehersisyo >>");
            btn5.setText("Simulan ang Ehersisyo >>");
            btn6.setText("Simulan ang Ehersisyo >>");
            settings.setCustomLang(ThemeSettings.TAG_LANG);

        }
    }

    // Text Size View
    private void updateSizeView() {
        if(settings.getCustomSize().equals(ThemeSettings.SMALL_SIZE)){

            exerciseTitleTV.setTextSize(16);
            exerciseCatTV.setTextSize(24);
            chestExeTV1.setTextSize(18);
            armsExeTV1.setTextSize(18);
            backExeTV1.setTextSize(18);
            coreExeTV1.setTextSize(15);
            legsExeTV1.setTextSize(18);
            fullExeTV1.setTextSize(18);
            btn1.setTextSize(12);
            btn2.setTextSize(12);
            btn3.setTextSize(12);
            btn4.setTextSize(12);
            btn5.setTextSize(12);
            btn6.setTextSize(12);
            settings.setCustomSize(ThemeSettings.SMALL_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.MEDIUM_SIZE)){

            exerciseTitleTV.setTextSize(18);
            exerciseCatTV.setTextSize(26);
            chestExeTV1.setTextSize(20);
            armsExeTV1.setTextSize(20);
            backExeTV1.setTextSize(20);
            coreExeTV1.setTextSize(17);
            legsExeTV1.setTextSize(20);
            fullExeTV1.setTextSize(20);
            btn1.setTextSize(14);
            btn2.setTextSize(14);
            btn3.setTextSize(14);
            btn4.setTextSize(14);
            btn5.setTextSize(14);
            btn6.setTextSize(14);
            settings.setCustomSize(ThemeSettings.MEDIUM_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.LARGE_SIZE)){

            exerciseTitleTV.setTextSize(20);
            exerciseCatTV.setTextSize(28);
            chestExeTV1.setTextSize(22);
            armsExeTV1.setTextSize(22);
            backExeTV1.setTextSize(22);
            coreExeTV1.setTextSize(19);
            legsExeTV1.setTextSize(22);
            fullExeTV1.setTextSize(22);
            btn1.setTextSize(16);
            btn2.setTextSize(16);
            btn3.setTextSize(16);
            btn4.setTextSize(16);
            btn5.setTextSize(16);
            btn6.setTextSize(16);
            settings.setCustomSize(ThemeSettings.LARGE_SIZE);

        }
    }

    // Back Button Intent
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}