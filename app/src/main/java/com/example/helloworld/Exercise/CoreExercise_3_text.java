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

public class CoreExercise_3_text extends AppCompatActivity {

    ImageView bckBtn;

    //Theme SharedPreferences
    private View coreSideTextParentView;
    private TextView coreSideTextTV, coreSideTextTV1;

    private ThemeSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core_exercise3_text);

        bckBtn = findViewById(R.id.back_pressed);
        bckBtn.setOnClickListener(new View.OnClickListener() {
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

        coreSideTextParentView = findViewById(R.id.coreSideTextParentView);
        coreSideTextTV = findViewById(R.id.coreSideTextTV);
        coreSideTextTV1 = findViewById(R.id.coreSideTextTV1);

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

            coreSideTextTV.setTextColor(white);
            coreSideTextParentView.setBackgroundColor(bgblack);

        }else{

            coreSideTextTV.setTextColor(black);
            coreSideTextParentView.setBackgroundColor(bgwhite);
        }
    }

    // Language View
    private void updateLangView() {
        if(settings.getCustomLang().equals(ThemeSettings.ENG_LANG)){

            coreSideTextTV.setText("Side Plank");
            coreSideTextTV1.setText(R.string.side_plank);
            settings.setCustomLang(ThemeSettings.ENG_LANG);

        }else if (settings.getCustomLang().equals(ThemeSettings.TAG_LANG)){

            coreSideTextTV.setText("Plank Patigilid");
            coreSideTextTV1.setText(R.string.side_plank_tag);
            settings.setCustomLang(ThemeSettings.TAG_LANG);

        }
    }

    // Text Size View
    private void updateSizeView() {
        if(settings.getCustomSize().equals(ThemeSettings.SMALL_SIZE)){

            coreSideTextTV.setTextSize(24);
            coreSideTextTV1.setTextSize(12);
            settings.setCustomSize(ThemeSettings.SMALL_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.MEDIUM_SIZE)){

            coreSideTextTV.setTextSize(26);
            coreSideTextTV1.setTextSize(14);
            settings.setCustomSize(ThemeSettings.MEDIUM_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.LARGE_SIZE)){

            coreSideTextTV.setTextSize(28);
            coreSideTextTV1.setTextSize(16);
            settings.setCustomSize(ThemeSettings.LARGE_SIZE);

        }
    }

    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), CoreExercise_3.class);
        startActivity(intent);
    }
}