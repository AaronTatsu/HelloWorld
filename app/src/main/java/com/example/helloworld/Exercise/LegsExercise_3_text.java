package com.example.helloworld.Exercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helloworld.R;
import com.example.helloworld.Settings.ThemeSettings;

public class LegsExercise_3_text extends AppCompatActivity {

    ImageView bckBtn;

    //Theme SharedPreferences
    private View legsDonkeyTextParentView;
    private TextView legsDonkeyTextTV, legsDonkeyTextTV1;

    private ThemeSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legs_exercise3_text);

        bckBtn = findViewById(R.id.back_pressed);
        bckBtn.setOnClickListener(new View.OnClickListener() {
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

        legsDonkeyTextParentView = findViewById(R.id.legsDonkeyTextParentView);
        legsDonkeyTextTV = findViewById(R.id.legsDonkeyTextTV);
        legsDonkeyTextTV1 = findViewById(R.id.legsDonkeyTextTV1);

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

        final int black = ContextCompat.getColor(this, R.color.dark_gray);
        final int bgblack = ContextCompat.getColor(this, R.color.light_black);
        final int bgwhite = ContextCompat.getColor(this, R.color.light_white);
        final int white = ContextCompat.getColor(this, R.color.light_white);

        if(settings.getCustomTheme().equals(ThemeSettings.DARK_THEME)){

            legsDonkeyTextTV.setTextColor(white);
            legsDonkeyTextParentView.setBackgroundColor(bgblack);

        }else{

            legsDonkeyTextTV.setTextColor(black);
            legsDonkeyTextParentView.setBackgroundColor(bgwhite);
        }
    }

    // Language View
    private void updateLangView() {
        if(settings.getCustomLang().equals(ThemeSettings.ENG_LANG)){

            legsDonkeyTextTV.setText("Donkey Kicks");
            legsDonkeyTextTV1.setText(R.string.donkey_kicks);
            settings.setCustomLang(ThemeSettings.ENG_LANG);

        }else if (settings.getCustomLang().equals(ThemeSettings.TAG_LANG)){

            legsDonkeyTextTV.setText("Sipa ng Donkey");
            legsDonkeyTextTV1.setText(R.string.donkey_kicks_tag);
            settings.setCustomLang(ThemeSettings.TAG_LANG);

        }
    }

    // Text Size View
    private void updateSizeView() {
        if(settings.getCustomSize().equals(ThemeSettings.SMALL_SIZE)){

            legsDonkeyTextTV.setTextSize(24);
            legsDonkeyTextTV1.setTextSize(12);
            settings.setCustomSize(ThemeSettings.SMALL_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.MEDIUM_SIZE)){

            legsDonkeyTextTV.setTextSize(26);
            legsDonkeyTextTV1.setTextSize(14);
            settings.setCustomSize(ThemeSettings.MEDIUM_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.LARGE_SIZE)){

            legsDonkeyTextTV.setTextSize(28);
            legsDonkeyTextTV1.setTextSize(16);
            settings.setCustomSize(ThemeSettings.LARGE_SIZE);

        }
    }

    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), LegsExercise_3.class);
        startActivity(intent);
    }
}