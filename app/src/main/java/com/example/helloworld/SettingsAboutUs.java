package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingsAboutUs extends AppCompatActivity {

    // Intent
    ImageView bckBtn;
    TextView contactBtn;

    // SharedPreferences
    private View aboutUsParentView, aboutDescView;
    private TextView aboutTitleTV, logoText, aboutTV1, aboutVersionTV;

    private ThemeSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_about_us);

        //Intents
        bckBtn = findViewById(R.id.back_pressed);
        bckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        contactBtn = findViewById(R.id.gotoContact);
        contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingsContactUs.class);
                startActivity(intent);
            }
        });
        //Theme SharedPreferences
        settings = (ThemeSettings) getApplication();

        initWidgets();
        loadSharedPreferences();
        updateThemeView();
    }
    private void initWidgets() {

        aboutUsParentView = findViewById(R.id.aboutUsParentView);
        aboutDescView = findViewById(R.id.aboutDescView);
        logoText = findViewById(R.id.logoText);
        aboutTitleTV = findViewById(R.id.aboutTitleTV);
        aboutTV1 = findViewById(R.id.aboutTV1);
        aboutVersionTV = findViewById(R.id.aboutVersionTV);
    }
    private void loadSharedPreferences() {

        SharedPreferences sharedPreferences = getSharedPreferences(ThemeSettings.PREFERENCES,MODE_PRIVATE);
        String theme = sharedPreferences.getString(ThemeSettings.CUSTOM_THEME, ThemeSettings.LIGHT_THEME);
        settings.setCustomTheme(theme);

    }
    private void updateThemeView() {

        final int black = ContextCompat.getColor(this, R.color.black);
        final int bgblack = ContextCompat.getColor(this, R.color.light_black);
        final int gray = ContextCompat.getColor(this, R.color.grayest);
        final int bgwhite = ContextCompat.getColor(this, R.color.light_white);
        final int white = ContextCompat.getColor(this, R.color.light_white);

        if(settings.getCustomTheme().equals(ThemeSettings.DARK_THEME)){

            logoText.setTextColor(white);
            aboutTitleTV.setTextColor(white);
            aboutTV1.setTextColor(white);
            aboutVersionTV.setTextColor(white);
            aboutDescView.setBackgroundColor(gray);
            aboutUsParentView.setBackgroundColor(bgblack);

        }else{

            logoText.setTextColor(black);
            aboutTitleTV.setTextColor(black);
            aboutTV1.setTextColor(black);
            aboutVersionTV.setTextColor(black);
            aboutDescView.setBackgroundColor(gray);
            aboutUsParentView.setBackgroundColor(bgwhite);
        }
    }

    // Back Button Intent
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }
}