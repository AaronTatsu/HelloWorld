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

public class ChestExercise_3_text extends AppCompatActivity {

    ImageView bckBtn;

    //Theme SharedPreferences
    private View chestIsoTextParentView;
    private TextView chestIsoTextTV, chestIsoTextTV1;

    private ThemeSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chest_exercise3_text);

        bckBtn = findViewById(R.id.back_pressed);
        bckBtn.setOnClickListener(new View.OnClickListener() {
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

        chestIsoTextParentView = findViewById(R.id.chestIsoTextParentView);
        chestIsoTextTV = findViewById(R.id.chestIsoTextTV);
        chestIsoTextTV1 = findViewById(R.id.chestIsoTextTV1);

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

            chestIsoTextTV.setTextColor(white);
            chestIsoTextParentView.setBackgroundColor(bgblack);

        }else{

            chestIsoTextTV.setTextColor(black);
            chestIsoTextParentView.setBackgroundColor(bgwhite);
        }
    }

    // Language View
    private void updateLangView() {
        if(settings.getCustomLang().equals(ThemeSettings.ENG_LANG)){

            chestIsoTextTV.setText("Isometric Chest Squeeze");
            chestIsoTextTV1.setText(R.string.push_squeeze);
            settings.setCustomLang(ThemeSettings.ENG_LANG);

        }else if (settings.getCustomLang().equals(ThemeSettings.TAG_LANG)){

            chestIsoTextTV.setText("Isometrikong Pagpisil sa Dibdib");
            chestIsoTextTV1.setText(R.string.push_squeeze_tag);
            settings.setCustomLang(ThemeSettings.TAG_LANG);

        }
    }

    // Text Size View
    private void updateSizeView() {
        if(settings.getCustomSize().equals(ThemeSettings.SMALL_SIZE)){

            chestIsoTextTV.setTextSize(24);
            chestIsoTextTV1.setTextSize(12);
            settings.setCustomSize(ThemeSettings.SMALL_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.MEDIUM_SIZE)){

            chestIsoTextTV.setTextSize(26);
            chestIsoTextTV1.setTextSize(14);
            settings.setCustomSize(ThemeSettings.MEDIUM_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.LARGE_SIZE)){

            chestIsoTextTV.setTextSize(28);
            chestIsoTextTV1.setTextSize(16);
            settings.setCustomSize(ThemeSettings.LARGE_SIZE);

        }
    }

    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), ChestExercise_3.class);
        startActivity(intent);
    }
}