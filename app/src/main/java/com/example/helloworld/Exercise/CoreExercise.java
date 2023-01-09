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

public class CoreExercise extends AppCompatActivity {

    //Intents
    ImageView bckBtn, btn1, btn2, btn3;

    //Theme SharedPreferences
    private View coreParentView;
    private TextView coreTitleTV, coreTV1, coreTV2, coreTV3, coreRepeatTV1, coreRepeatTV2, coreRepeatTV3, coreTimeTV1, coreTimeTV2, coreTimeTV3;

    private ThemeSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core_exercise);

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
        btn1 = findViewById(R.id.core_btn_1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CoreExercise_1.class);
                startActivity(intent);
            }
        });

        btn2 = findViewById(R.id.core_btn_2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CoreExercise_2.class);
                startActivity(intent);
            }
        });

        btn3 = findViewById(R.id.core_btn_3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CoreExercise_3.class);
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

        coreParentView = findViewById(R.id.coreParentView);
        coreTitleTV = findViewById(R.id.coreTitleTV);
        coreTV1 = findViewById(R.id.coreTV1);
        coreTV2 = findViewById(R.id.coreTV2);
        coreTV3 = findViewById(R.id.coreTV3);
        coreRepeatTV1 = findViewById(R.id.coreRepeatTV1);
        coreRepeatTV2 = findViewById(R.id.coreRepeatTV2);
        coreRepeatTV3 = findViewById(R.id.coreRepeatTV3);
        coreTimeTV1 = findViewById(R.id.coreTimeTV1);
        coreTimeTV2 = findViewById(R.id.coreTimeTV2);
        coreTimeTV3 = findViewById(R.id.coreTimeTV3);

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

            coreTitleTV.setTextColor(white);
            coreParentView.setBackgroundColor(bgblack);

        }else{

            coreTitleTV.setTextColor(black);
            coreParentView.setBackgroundColor(bgwhite);
        }
    }

    // Language View
    private void updateLangView() {
        if(settings.getCustomLang().equals(ThemeSettings.ENG_LANG)){

            coreTitleTV.setText("Core & Abdominal Exercise");
            coreTV1.setText("Plank");
            coreTV2.setText("Bicycle Crunches");
            coreTV3.setText("Side Plank");
            coreRepeatTV1.setText("Repeat 2 Times");
            coreRepeatTV2.setText("Repeat 2 Times");
            coreRepeatTV3.setText("Repeat 2 Times");
            coreTimeTV1.setText("1:00 MINUTE");
            coreTimeTV2.setText("1:00 MINUTE");
            coreTimeTV3.setText("1:00 MINUTE");
            settings.setCustomLang(ThemeSettings.ENG_LANG);

        }else if (settings.getCustomLang().equals(ThemeSettings.TAG_LANG)){

            coreTitleTV.setText("Ehersisyo sa Tiyan at Apdo");
            coreTV1.setText("Plank");
            coreTV2.setText("Mga Crunches ng Bisikleta");
            coreTV3.setText("Plank Pagilid");
            coreRepeatTV1.setText("Ulitin ng 2 beses");
            coreRepeatTV2.setText("Ulitin ng 2 beses");
            coreRepeatTV3.setText("Ulitin ng 2 beses");
            coreTimeTV1.setText("1:00 MINUTO");
            coreTimeTV2.setText("1:00 MINUTO");
            coreTimeTV3.setText("1:00 MINUTO");
            settings.setCustomLang(ThemeSettings.TAG_LANG);

        }
    }

    // Text Size View
    private void updateSizeView() {
        if(settings.getCustomSize().equals(ThemeSettings.SMALL_SIZE)){

            coreTitleTV.setTextSize(16);
            coreTV1.setTextSize(13);
            coreTV2.setTextSize(13);
            coreTV3.setTextSize(13);
            coreRepeatTV1.setTextSize(10);
            coreRepeatTV2.setTextSize(10);
            coreRepeatTV3.setTextSize(10);
            coreTimeTV1.setTextSize(13);
            coreTimeTV2.setTextSize(13);
            coreTimeTV3.setTextSize(13);
            settings.setCustomSize(ThemeSettings.SMALL_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.MEDIUM_SIZE)){

            coreTitleTV.setTextSize(18);
            coreTV1.setTextSize(15);
            coreTV2.setTextSize(15);
            coreTV3.setTextSize(15);
            coreRepeatTV1.setTextSize(12);
            coreRepeatTV2.setTextSize(12);
            coreRepeatTV3.setTextSize(12);
            coreTimeTV1.setTextSize(15);
            coreTimeTV2.setTextSize(15);
            coreTimeTV3.setTextSize(15);
            settings.setCustomSize(ThemeSettings.MEDIUM_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.LARGE_SIZE)){

            coreTitleTV.setTextSize(20);
            coreTV1.setTextSize(17);
            coreTV2.setTextSize(17);
            coreTV3.setTextSize(17);
            coreRepeatTV1.setTextSize(14);
            coreRepeatTV2.setTextSize(14);
            coreRepeatTV3.setTextSize(14);
            coreTimeTV1.setTextSize(17);
            coreTimeTV2.setTextSize(17);
            coreTimeTV3.setTextSize(17);
            settings.setCustomSize(ThemeSettings.LARGE_SIZE);

        }
    }

    //Back Button Intent
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), ExerciseActivity.class);
        startActivity(intent);
    }
}