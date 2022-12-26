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

public class ChestExercise extends AppCompatActivity {

    // Intent
    ImageView bckBtn, btn1, btn2, btn3;

    //Theme SharedPreferences
    private View chestParentView;
    private TextView chestTitleTV, chestTV1, chestTV2, chestTV3, chestRepeatTV1, chestRepeatTV2, chestRepeatTV3, chestTimeTV1, chestTimeTV2, chestTimeTV3;

    private ThemeSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chest_exercise);

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
        btn1 = findViewById(R.id.chest_btn_1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChestExercise_1.class);
                startActivity(intent);
            }
        });

        btn2 = findViewById(R.id.chest_btn_2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChestExercise_2.class);
                startActivity(intent);
            }
        });

        btn3 = findViewById(R.id.chest_btn_3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChestExercise_3.class);
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

        chestParentView = findViewById(R.id.chestParentView);
        chestTitleTV = findViewById(R.id.chestTitleTV);
        chestTV1 = findViewById(R.id.chestTV1);
        chestTV2 = findViewById(R.id.chestTV2);
        chestTV3 = findViewById(R.id.chestTV3);
        chestRepeatTV1 = findViewById(R.id.chestRepeatTV1);
        chestRepeatTV2 = findViewById(R.id.chestRepeatTV2);
        chestRepeatTV3 = findViewById(R.id.chestRepeatTV3);
        chestTimeTV1 = findViewById(R.id.chestTimeTV1);
        chestTimeTV2 = findViewById(R.id.chestTimeTV2);
        chestTimeTV3 = findViewById(R.id.chestTimeTV3);

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

            chestTitleTV.setTextColor(white);
            chestParentView.setBackgroundColor(bgblack);

        }else{

            chestTitleTV.setTextColor(black);
            chestParentView.setBackgroundColor(bgwhite);
        }
    }

    // Language View
    private void updateLangView() {
        if(settings.getCustomLang().equals(ThemeSettings.ENG_LANG)){

            chestTitleTV.setText("Chest Exercise");
            chestTV1.setText("Push Up");
            chestTV2.setText("Push Up Shuffle");
            chestTV3.setText("Isometric Squeeze Chest");
            chestRepeatTV1.setText("Repeat 2 Times");
            chestRepeatTV2.setText("Repeat 2 Times");
            chestRepeatTV3.setText("Repeat 2 Times");
            chestTimeTV1.setText("1:00 MINUTE");
            chestTimeTV2.setText("1:00 MINUTE");
            chestTimeTV3.setText("1:00 MINUTE");
            settings.setCustomLang(ThemeSettings.ENG_LANG);

        }else if (settings.getCustomLang().equals(ThemeSettings.TAG_LANG)){

            chestTitleTV.setText("Ehersisyo sa Dibdib");
            chestTV1.setText("Pagdiin-Angat");
            chestTV2.setText("Pagdiin-Angat Lumipat");
            chestTV3.setText("Isometrikong Pagpisil sa Dibdib");
            chestRepeatTV1.setText("Ulitin ng 2 beses");
            chestRepeatTV2.setText("Ulitin ng 2 beses");
            chestRepeatTV3.setText("Ulitin ng 2 beses");
            chestTimeTV1.setText("1:00 MINUTO");
            chestTimeTV2.setText("1:00 MINUTO");
            chestTimeTV3.setText("1:00 MINUTO");
            settings.setCustomLang(ThemeSettings.TAG_LANG);

        }
    }

    // Text Size View
    private void updateSizeView() {
        if(settings.getCustomSize().equals(ThemeSettings.SMALL_SIZE)){

            chestTitleTV.setTextSize(16);
            chestTV1.setTextSize(13);
            chestTV2.setTextSize(13);
            chestTV3.setTextSize(13);
            chestRepeatTV1.setTextSize(10);
            chestRepeatTV2.setTextSize(10);
            chestRepeatTV3.setTextSize(10);
            chestTimeTV1.setTextSize(13);
            chestTimeTV2.setTextSize(13);
            chestTimeTV3.setTextSize(13);
            settings.setCustomSize(ThemeSettings.SMALL_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.MEDIUM_SIZE)){

            chestTitleTV.setTextSize(18);
            chestTV1.setTextSize(15);
            chestTV2.setTextSize(15);
            chestTV3.setTextSize(15);
            chestRepeatTV1.setTextSize(12);
            chestRepeatTV2.setTextSize(12);
            chestRepeatTV3.setTextSize(12);
            chestTimeTV1.setTextSize(15);
            chestTimeTV2.setTextSize(15);
            chestTimeTV3.setTextSize(15);
            settings.setCustomSize(ThemeSettings.MEDIUM_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.LARGE_SIZE)){

            chestTitleTV.setTextSize(20);
            chestTV1.setTextSize(17);
            chestTV2.setTextSize(17);
            chestTV3.setTextSize(17);
            chestRepeatTV1.setTextSize(14);
            chestRepeatTV2.setTextSize(14);
            chestRepeatTV3.setTextSize(14);
            chestTimeTV1.setTextSize(17);
            chestTimeTV2.setTextSize(17);
            chestTimeTV3.setTextSize(17);
            settings.setCustomSize(ThemeSettings.LARGE_SIZE);

        }
    }

    // Back Button Intent
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), ExerciseActivity.class);
        startActivity(intent);
    }
}