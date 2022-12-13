package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingsContactUs extends AppCompatActivity {

    ImageView bckBtn;

    // Theme
    private View contactParentView;
    private TextView contactTitleTv, logoText, contacttv1, contacttv2, contacttv3, contacttv4, contacttv5, contacttv6, contacttv7;

    private ThemeSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_contact_us);

        // Intent
        bckBtn = findViewById(R.id.back_pressed);
        bckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                SettingsContactUs.super.onBackPressed();
            }
        });

        //Theme
        settings = (ThemeSettings) getApplication();

        initWidgets();
        loadSharedPreferences();
        updateThemeView();
    }
    private void initWidgets() {

        contactParentView = findViewById(R.id.contactParentView);
        logoText = findViewById(R.id.LogoText);
        contactTitleTv = findViewById(R.id.contactTitleTV);
        contacttv1 = findViewById(R.id.contacttv1);
        contacttv2 = findViewById(R.id.contacttv2);
        contacttv3 = findViewById(R.id.contacttv3);
        contacttv4 = findViewById(R.id.contacttv4);
        contacttv5 = findViewById(R.id.contacttv5);
        contacttv6 = findViewById(R.id.contacttv6);
        contacttv7 = findViewById(R.id.contacttv7);

    }

    private void loadSharedPreferences() {

        SharedPreferences sharedPreferences = getSharedPreferences(ThemeSettings.PREFERENCES,MODE_PRIVATE);
        String theme = sharedPreferences.getString(ThemeSettings.CUSTOM_THEME, ThemeSettings.LIGHT_THEME);
        settings.setCustomTheme(theme);

    }

    private void updateThemeView() {

        final int black = ContextCompat.getColor(this, R.color.black);
        final int bgblack = ContextCompat.getColor(this, R.color.light_black);
        final int bgwhite = ContextCompat.getColor(this, R.color.light_white);
        final int white = ContextCompat.getColor(this, R.color.light_white);

        if(settings.getCustomTheme().equals(ThemeSettings.DARK_THEME)){

            logoText.setTextColor(white);
            contactTitleTv.setTextColor(white);
            contacttv1.setTextColor(white);
            contacttv2.setTextColor(white);
            contacttv3.setTextColor(white);
            contacttv4.setTextColor(white);
            contacttv5.setTextColor(white);
            contacttv6.setTextColor(white);
            contacttv7.setTextColor(white);
            contactParentView.setBackgroundColor(bgblack);

        }else{

            logoText.setTextColor(black);
            contactTitleTv.setTextColor(black);
            contacttv1.setTextColor(black);
            contacttv2.setTextColor(black);
            contacttv3.setTextColor(black);
            contacttv4.setTextColor(black);
            contacttv5.setTextColor(black);
            contacttv6.setTextColor(black);
            contacttv7.setTextColor(black);
            contactParentView.setBackgroundColor(bgwhite);
        }
    }

    //Back Button Intent
    public void onBackPressed(){
        SettingsContactUs.super.onBackPressed();
    }
}