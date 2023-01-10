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

public class BodyExercise extends AppCompatActivity {

    // Intents
    ImageView bckBtn, btn1, btn2, btn3;

    //Theme SharedPreferences
    private View bodyParentView;
    private TextView bodyTitleTV, bodyTV1, bodyTV2, bodyTV3, bodyRepeatTV1, bodyRepeatTV2, bodyRepeatTV3, bodyTimeTV1, bodyTimeTV2, bodyTimeTV3;

    private ThemeSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_exercise);

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
        btn1 = findViewById(R.id.body_btn_1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BodyExercise_1.class);
                startActivity(intent);
            }
        });

        btn2 = findViewById(R.id.body_btn_2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BodyExercise_2.class);
                startActivity(intent);
            }
        });

        btn3 = findViewById(R.id.body_btn_3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BodyExercise_3.class);
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

        bodyParentView = findViewById(R.id.bodyParentView);
        bodyTitleTV = findViewById(R.id.bodyTitleTV);
        bodyTV1 = findViewById(R.id.bodyTV1);
        bodyTV2 = findViewById(R.id.bodyTV2);
        bodyTV3 = findViewById(R.id.bodyTV3);
        bodyRepeatTV1 = findViewById(R.id.bodyRepeatTV1);
        bodyRepeatTV2 = findViewById(R.id.bodyRepeatTV2);
        bodyRepeatTV3 = findViewById(R.id.bodyRepeatTV3);
        bodyTimeTV1 = findViewById(R.id.bodyTimeTV1);
        bodyTimeTV2 = findViewById(R.id.bodyTimeTV2);
        bodyTimeTV3 = findViewById(R.id.bodyTimeTV3);

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

            bodyTitleTV.setTextColor(white);
            bodyParentView.setBackgroundColor(bgblack);

        }else{

            bodyTitleTV.setTextColor(black);
            bodyParentView.setBackgroundColor(bgwhite);
        }
    }

    // Language View
    private void updateLangView() {
        if(settings.getCustomLang().equals(ThemeSettings.ENG_LANG)){

            bodyTitleTV.setText("Full Body Exercise");
            bodyTV1.setText("Burpee");
            bodyTV2.setText("Mountain Climbers");
            bodyTV3.setText("Bear Crawls");
            bodyRepeatTV1.setText("Repeat 2 Times");
            bodyRepeatTV2.setText("Repeat 2 Times");
            bodyRepeatTV3.setText("Repeat 2 Times");
            bodyTimeTV1.setText("1:00 MINUTE");
            bodyTimeTV2.setText("1:00 MINUTE");
            bodyTimeTV3.setText("1:00 MINUTE");
            settings.setCustomLang(ThemeSettings.ENG_LANG);

        }else if (settings.getCustomLang().equals(ThemeSettings.TAG_LANG)){

            bodyTitleTV.setText("Ehersisyo sa Buong Katawan");
            bodyTV1.setText("Burpee");
            bodyTV2.setText("Mamumundok");
            bodyTV3.setText("Oso na Gumagapang");
            bodyRepeatTV1.setText("Ulitin ng 2 beses");
            bodyRepeatTV2.setText("Ulitin ng 2 beses");
            bodyRepeatTV3.setText("Ulitin ng 2 beses");
            bodyTimeTV1.setText("1:00 MINUTO");
            bodyTimeTV2.setText("1:00 MINUTO");
            bodyTimeTV3.setText("1:00 MINUTO");
            settings.setCustomLang(ThemeSettings.TAG_LANG);

        }
    }

    // Text Size View
    private void updateSizeView() {
        if(settings.getCustomSize().equals(ThemeSettings.SMALL_SIZE)){

            bodyTitleTV.setTextSize(16);
            bodyTV1.setTextSize(13);
            bodyTV2.setTextSize(13);
            bodyTV3.setTextSize(13);
            bodyRepeatTV1.setTextSize(10);
            bodyRepeatTV2.setTextSize(10);
            bodyRepeatTV3.setTextSize(10);
            bodyTimeTV1.setTextSize(13);
            bodyTimeTV2.setTextSize(13);
            bodyTimeTV3.setTextSize(13);
            settings.setCustomSize(ThemeSettings.SMALL_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.MEDIUM_SIZE)){

            bodyTitleTV.setTextSize(18);
            bodyTV1.setTextSize(15);
            bodyTV2.setTextSize(15);
            bodyTV3.setTextSize(15);
            bodyRepeatTV1.setTextSize(12);
            bodyRepeatTV2.setTextSize(12);
            bodyRepeatTV3.setTextSize(12);
            bodyTimeTV1.setTextSize(15);
            bodyTimeTV2.setTextSize(15);
            bodyTimeTV3.setTextSize(15);
            settings.setCustomSize(ThemeSettings.MEDIUM_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.LARGE_SIZE)){

            bodyTitleTV.setTextSize(20);
            bodyTV1.setTextSize(17);
            bodyTV2.setTextSize(17);
            bodyTV3.setTextSize(17);
            bodyRepeatTV1.setTextSize(14);
            bodyRepeatTV2.setTextSize(14);
            bodyRepeatTV3.setTextSize(14);
            bodyTimeTV1.setTextSize(17);
            bodyTimeTV2.setTextSize(17);
            bodyTimeTV3.setTextSize(17);
            settings.setCustomSize(ThemeSettings.LARGE_SIZE);

        }
    }

    // Back Button Intent
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), ExerciseActivity.class);
        startActivity(intent);
    }
}