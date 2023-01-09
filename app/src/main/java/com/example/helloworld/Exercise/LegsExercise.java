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

public class LegsExercise extends AppCompatActivity {

    //Intents
    ImageView bckBtn, btn1, btn2, btn3;

    //Theme SharedPreferences
    private View legsParentView;
    private TextView legsTitleTV, legsTV1, legsTV2, legsTV3, legsRepeatTV1, legsRepeatTV2, legsRepeatTV3, legsTimeTV1,legsTimeTV2, legsTimeTV3;

    private ThemeSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legs_exercise);

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
        btn1 = findViewById(R.id.legs_btn_1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LegsExercise_1.class);
                startActivity(intent);
            }
        });

        btn2 = findViewById(R.id.legs_btn_2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LegsExercise_2.class);
                startActivity(intent);
            }
        });

        btn3 = findViewById(R.id.legs_btn_3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LegsExercise_3.class);
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

        legsParentView = findViewById(R.id.legsParentView);
        legsTitleTV = findViewById(R.id.legsTitleTV);
        legsTV1 = findViewById(R.id.legsTV1);
        legsTV2 = findViewById(R.id.legsTV2);
        legsTV3 = findViewById(R.id.legsTV3);
        legsRepeatTV1 = findViewById(R.id.legsRepeatTV1);
        legsRepeatTV2 = findViewById(R.id.legsRepeatTV2);
        legsRepeatTV3 = findViewById(R.id.legsRepeatTV3);
        legsTimeTV1 = findViewById(R.id.legsTimeTV1);
        legsTimeTV2 = findViewById(R.id.legsTimeTV2);
        legsTimeTV3 = findViewById(R.id.legsTimeTV3);

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

            legsTitleTV.setTextColor(white);
            legsParentView.setBackgroundColor(bgblack);

        }else{

            legsTitleTV.setTextColor(black);
            legsParentView.setBackgroundColor(bgwhite);
        }
    }

    // Language View
    private void updateLangView() {
        if(settings.getCustomLang().equals(ThemeSettings.ENG_LANG)){

            legsTitleTV.setText("Legs Exercise");
            legsTV1.setText("Jump Squats");
            legsTV2.setText("Reverse Lunge");
            legsTV3.setText("Donkey Kicks");
            legsRepeatTV1.setText("Repeat 2 Times");
            legsRepeatTV2.setText("Repeat 2 Times");
            legsRepeatTV3.setText("Repeat 2 Times");
            legsTimeTV1.setText("1:00 MINUTE");
            legsTimeTV2.setText("1:00 MINUTE");
            legsTimeTV3.setText("1:00 MINUTE");
            settings.setCustomLang(ThemeSettings.ENG_LANG);

        }else if (settings.getCustomLang().equals(ThemeSettings.TAG_LANG)){

            legsTitleTV.setText("Ehersisyo sa Binti");
            legsTV1.setText("Squat Pagkatalon");
            legsTV2.setText("Pabaliktad Daluhong");
            legsTV3.setText("Sipa ng Donkey");
            legsRepeatTV1.setText("Ulitin ng 2 beses");
            legsRepeatTV2.setText("Ulitin ng 2 beses");
            legsRepeatTV3.setText("Ulitin ng 2 beses");
            legsTimeTV1.setText("1:00 MINUTO");
            legsTimeTV2.setText("1:00 MINUTO");
            legsTimeTV3.setText("1:00 MINUTO");
            settings.setCustomLang(ThemeSettings.TAG_LANG);

        }
    }

    // Text Size View
    private void updateSizeView() {
        if(settings.getCustomSize().equals(ThemeSettings.SMALL_SIZE)){

            legsTitleTV.setTextSize(16);
            legsTV1.setTextSize(13);
            legsTV2.setTextSize(13);
            legsTV3.setTextSize(13);
            legsRepeatTV1.setTextSize(10);
            legsRepeatTV2.setTextSize(10);
            legsRepeatTV3.setTextSize(10);
            legsTimeTV1.setTextSize(13);
            legsTimeTV2.setTextSize(13);
            legsTimeTV3.setTextSize(13);
            settings.setCustomSize(ThemeSettings.SMALL_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.MEDIUM_SIZE)){

            legsTitleTV.setTextSize(18);
            legsTV1.setTextSize(15);
            legsTV2.setTextSize(15);
            legsTV3.setTextSize(15);
            legsRepeatTV1.setTextSize(12);
            legsRepeatTV2.setTextSize(12);
            legsRepeatTV3.setTextSize(12);
            legsTimeTV1.setTextSize(15);
            legsTimeTV2.setTextSize(15);
            legsTimeTV3.setTextSize(15);
            settings.setCustomSize(ThemeSettings.MEDIUM_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.LARGE_SIZE)){

            legsTitleTV.setTextSize(20);
            legsTV1.setTextSize(17);
            legsTV2.setTextSize(17);
            legsTV3.setTextSize(17);
            legsRepeatTV1.setTextSize(14);
            legsRepeatTV2.setTextSize(14);
            legsRepeatTV3.setTextSize(14);
            legsTimeTV1.setTextSize(17);
            legsTimeTV2.setTextSize(17);
            legsTimeTV3.setTextSize(17);
            settings.setCustomSize(ThemeSettings.LARGE_SIZE);

        }
    }

    //Back Button Intent
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), ExerciseActivity.class);
        startActivity(intent);
    }
}