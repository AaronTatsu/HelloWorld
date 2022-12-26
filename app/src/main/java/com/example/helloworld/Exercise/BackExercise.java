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

public class BackExercise extends AppCompatActivity {

    // Intents
    ImageView bckBtn, btn1, btn2, btn3;

    //Theme SharedPreferences
    private View backParentView;
    private TextView backTitleTV, backTV1, backTV2, backTV3, backRepeatTV1, backRepeatTV2, backRepeatTV3, backTimeTV1, backTimeTV2, backTimeTV3;

    private ThemeSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_exercise);

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
        btn1 = findViewById(R.id.back_btn_1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BackExercise_1.class);
                startActivity(intent);
            }
        });

        btn2 = findViewById(R.id.back_btn_2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BackExercise_2.class);
                startActivity(intent);
            }
        });

        btn3 = findViewById(R.id.back_btn_3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BackExercise_3.class);
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

        backParentView = findViewById(R.id.backParentView);
        backTitleTV = findViewById(R.id.backTitleTV);
        backTV1 = findViewById(R.id.backTV1);
        backTV2 = findViewById(R.id.backTV2);
        backTV3 = findViewById(R.id.backTV3);
        backRepeatTV1 = findViewById(R.id.backRepeatTV1);
        backRepeatTV2 = findViewById(R.id.backRepeatTV2);
        backRepeatTV3 = findViewById(R.id.backRepeatTV3);
        backTimeTV1 = findViewById(R.id.backTimeTV1);
        backTimeTV2 = findViewById(R.id.backTimeTV2);
        backTimeTV3 = findViewById(R.id.backTimeTV3);

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

            backTitleTV.setTextColor(white);
            backParentView.setBackgroundColor(bgblack);

        }else{

            backTitleTV.setTextColor(black);
            backParentView.setBackgroundColor(bgwhite);
        }
    }

    // Language View
    private void updateLangView() {
        if(settings.getCustomLang().equals(ThemeSettings.ENG_LANG)){

            backTitleTV.setText("Back Exercise");
            backTV1.setText("Superman Y");
            backTV2.setText("Bird Dog");
            backTV3.setText("Superman T");
            backRepeatTV1.setText("Repeat 2 Times");
            backRepeatTV2.setText("Repeat 2 Times");
            backRepeatTV3.setText("Repeat 2 Times");
            backTimeTV1.setText("1:00 MINUTE");
            backTimeTV2.setText("1:00 MINUTE");
            backTimeTV3.setText("1:00 MINUTE");
            settings.setCustomLang(ThemeSettings.ENG_LANG);

        }else if (settings.getCustomLang().equals(ThemeSettings.TAG_LANG)){

            backTitleTV.setText("Ehersisyo sa Likod");
            backTV1.setText("Superman Y");
            backTV2.setText("Ibon Aso");
            backTV3.setText("Superman T");
            backRepeatTV1.setText("Ulitin ng 2 beses");
            backRepeatTV2.setText("Ulitin ng 2 beses");
            backRepeatTV3.setText("Ulitin ng 2 beses");
            backTimeTV1.setText("1:00 MINUTO");
            backTimeTV2.setText("1:00 MINUTO");
            backTimeTV3.setText("1:00 MINUTO");
            settings.setCustomLang(ThemeSettings.TAG_LANG);

        }
    }

    // Text Size View
    private void updateSizeView() {
        if(settings.getCustomSize().equals(ThemeSettings.SMALL_SIZE)){

            backTitleTV.setTextSize(16);
            backTV1.setTextSize(13);
            backTV2.setTextSize(13);
            backTV3.setTextSize(13);
            backRepeatTV1.setTextSize(10);
            backRepeatTV2.setTextSize(10);
            backRepeatTV3.setTextSize(10);
            backTimeTV1.setTextSize(13);
            backTimeTV2.setTextSize(13);
            backTimeTV3.setTextSize(13);
            settings.setCustomSize(ThemeSettings.SMALL_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.MEDIUM_SIZE)){

            backTitleTV.setTextSize(18);
            backTV1.setTextSize(15);
            backTV2.setTextSize(15);
            backTV3.setTextSize(15);
            backRepeatTV1.setTextSize(12);
            backRepeatTV2.setTextSize(12);
            backRepeatTV3.setTextSize(12);
            backTimeTV1.setTextSize(15);
            backTimeTV2.setTextSize(15);
            backTimeTV3.setTextSize(15);
            settings.setCustomSize(ThemeSettings.MEDIUM_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.LARGE_SIZE)){

            backTitleTV.setTextSize(20);
            backTV1.setTextSize(17);
            backTV2.setTextSize(17);
            backTV3.setTextSize(17);
            backRepeatTV1.setTextSize(14);
            backRepeatTV2.setTextSize(14);
            backRepeatTV3.setTextSize(14);
            backTimeTV1.setTextSize(17);
            backTimeTV2.setTextSize(17);
            backTimeTV3.setTextSize(17);
            settings.setCustomSize(ThemeSettings.LARGE_SIZE);

        }
    }

    // Back Button Intent
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), ExerciseActivity.class);
        startActivity(intent);
    }
}