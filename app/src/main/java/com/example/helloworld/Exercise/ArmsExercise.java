package com.example.helloworld.Exercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helloworld.ExerciseActivity;
import com.example.helloworld.R;
import com.example.helloworld.Settings.ThemeSettings;

public class ArmsExercise extends AppCompatActivity {

    //Intents
    ImageView bckBtn, btn1, btn2, btn3;

    //Theme SharedPreferences
    private View armsParentView;
    private TextView armsTitleTV, armsTV1, armsTV2, armsTV3, armsRepeatTV1, armsRepeatTV2, armsRepeatTV3, armsTimeTV1, armsTimeTV2, armsTimeTV3;

    private ThemeSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arms_exercise);

        //Back Button Pressed
        bckBtn = findViewById(R.id.back_pressed);
        bckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ExerciseActivity.class);
                startActivity(intent);
            }
        });
        //Chest Category Buttons
        btn1 = findViewById(R.id.arms_btn_1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ArmsExercise_1.class);
                startActivity(intent);
            }
        });

        btn2 = findViewById(R.id.arms_btn_2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ArmsExercise_2.class);
                startActivity(intent);
            }
        });

        btn3 = findViewById(R.id.arms_btn_3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ArmsExercise_3.class);
                startActivity(intent);
            }
        });

        // Theme SharedPreferences
        settings = (ThemeSettings) getApplication();

        initWidgets();
        loadSharedPreferences();
        updateThemeView();
    }
    private void initWidgets() {

        armsParentView = findViewById(R.id.armsParentView);
        armsTitleTV = findViewById(R.id.armsTitleTV);
        armsTV1 = findViewById(R.id.armsTV1);
        armsTV2 = findViewById(R.id.armsTV2);
        armsTV3 = findViewById(R.id.armsTV3);
        armsRepeatTV1 = findViewById(R.id.armsRepeatTV1);
        armsRepeatTV2 = findViewById(R.id.armsRepeatTV2);
        armsRepeatTV3 = findViewById(R.id.armsRepeatTV3);
        armsTimeTV1 = findViewById(R.id.armsTimeTV1);
        armsTimeTV2 = findViewById(R.id.armsTimeTV2);
        armsTimeTV3 = findViewById(R.id.armsTimeTV3);

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

            armsTitleTV.setTextColor(white);
            armsParentView.setBackgroundColor(bgblack);

        }else{

            armsTitleTV.setTextColor(black);
            armsParentView.setBackgroundColor(bgwhite);
        }
    }

    // Language View
    private void updateLangView() {
        if(settings.getCustomLang().equals(ThemeSettings.ENG_LANG)){

            armsTitleTV.setText("Arms Exercise");
            armsTV1.setText("Triceps Dip");
            armsTV2.setText("Plank up and down");
            armsTV3.setText("Triangle Push Ups");
            armsRepeatTV1.setText("Repeat 2 Times");
            armsRepeatTV2.setText("Repeat 2 Times");
            armsRepeatTV3.setText("Repeat 2 Times");
            armsTimeTV1.setText("1:00 MINUTE");
            armsTimeTV2.setText("1:00 MINUTE");
            armsTimeTV3.setText("1:00 MINUTE");
            settings.setCustomLang(ThemeSettings.ENG_LANG);

        }else if (settings.getCustomLang().equals(ThemeSettings.TAG_LANG)){

            armsTitleTV.setText("Ehersisyo sa Kamay");
            armsTV1.setText("Paglubog ng Triseps");
            armsTV2.setText("Plank pataas at pababa");
            armsTV3.setText("Tatsulok na Pagdiin-Angat");
            armsRepeatTV1.setText("Ulitin ng 2 beses");
            armsRepeatTV2.setText("Ulitin ng 2 beses");
            armsRepeatTV3.setText("Ulitin ng 2 beses");
            armsTimeTV1.setText("1:00 MINUTO");
            armsTimeTV2.setText("1:00 MINUTO");
            armsTimeTV3.setText("1:00 MINUTO");
            settings.setCustomLang(ThemeSettings.TAG_LANG);

        }
    }

    // Text Size View
    private void updateSizeView() {
        if(settings.getCustomSize().equals(ThemeSettings.SMALL_SIZE)){

            armsTitleTV.setTextSize(16);
            armsTV1.setTextSize(13);
            armsTV2.setTextSize(13);
            armsTV3.setTextSize(13);
            armsRepeatTV1.setTextSize(10);
            armsRepeatTV2.setTextSize(10);
            armsRepeatTV3.setTextSize(10);
            armsTimeTV1.setTextSize(13);
            armsTimeTV2.setTextSize(13);
            armsTimeTV3.setTextSize(13);
            settings.setCustomSize(ThemeSettings.SMALL_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.MEDIUM_SIZE)){

            armsTitleTV.setTextSize(18);
            armsTV1.setTextSize(15);
            armsTV2.setTextSize(15);
            armsTV3.setTextSize(15);
            armsRepeatTV1.setTextSize(12);
            armsRepeatTV2.setTextSize(12);
            armsRepeatTV3.setTextSize(12);
            armsTimeTV1.setTextSize(15);
            armsTimeTV2.setTextSize(15);
            armsTimeTV3.setTextSize(15);
            settings.setCustomSize(ThemeSettings.MEDIUM_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.LARGE_SIZE)){

            armsTitleTV.setTextSize(20);
            armsTV1.setTextSize(17);
            armsTV2.setTextSize(17);
            armsTV3.setTextSize(17);
            armsRepeatTV1.setTextSize(14);
            armsRepeatTV2.setTextSize(14);
            armsRepeatTV3.setTextSize(14);
            armsTimeTV1.setTextSize(17);
            armsTimeTV2.setTextSize(17);
            armsTimeTV3.setTextSize(17);
            settings.setCustomSize(ThemeSettings.LARGE_SIZE);

        }
    }

    //Back Button Intent
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), ExerciseActivity.class);
        startActivity(intent);
    }
}