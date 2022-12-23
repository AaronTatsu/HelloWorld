package com.example.helloworld.Settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helloworld.R;
import com.example.helloworld.SettingsActivity;

public class SettingsAboutUs extends AppCompatActivity {

    // Intent
    ImageView bckBtn;
    TextView contactBtn;

    // SharedPreferences
    private View aboutUsParentView, aboutDescView;
    private TextView aboutTitleTV, logoText, aboutTV1, aboutText1, aboutText2, aboutVersionTV, gotoContact;

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
        updateThemeView();
        updateLangView();
        updateSizeView();

    }
    private void initWidgets() {

        aboutUsParentView = findViewById(R.id.aboutUsParentView);
        aboutDescView = findViewById(R.id.aboutDescView);
        logoText = findViewById(R.id.logoText);
        aboutTitleTV = findViewById(R.id.aboutTitleTV);
        aboutTV1 = findViewById(R.id.aboutTV1);
        aboutText1 = findViewById(R.id.aboutText1);
        aboutText2 = findViewById(R.id.aboutText2);
        aboutVersionTV = findViewById(R.id.aboutVersionTV);
        gotoContact = findViewById(R.id.gotoContact);
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

    // Language View
    private void updateLangView() {
        if(settings.getCustomLang().equals(ThemeSettings.ENG_LANG)){

            aboutTitleTV.setText("About Us");
            aboutTV1.setText("Greetings, ");
            aboutVersionTV.setText("Version 1.0");
            aboutText1.setText(R.string.about_us);
            aboutText2.setText(R.string.about_us1);
            gotoContact.setText("Click here to contact us!");

        }else if (settings.getCustomLang().equals(ThemeSettings.TAG_LANG)){

            aboutTitleTV.setText("Info ng gumawa");
            aboutTV1.setText("Pagbati, ");
            aboutText1.setText(R.string.about_us_tag);
            aboutText2.setText(R.string.about_us1_tag);
            aboutVersionTV.setText("Bersyon 1.0");
            gotoContact.setText("Pindutin ito upang makontak kami!");

        }
    }
    // Text Size View
    private void updateSizeView() {
        if(settings.getCustomSize().equals(ThemeSettings.SMALL_SIZE)){

            aboutTitleTV.setTextSize(16);
            aboutTV1.setTextSize(18);
            aboutText1.setTextSize(12);
            aboutText2.setTextSize(12);
            aboutVersionTV.setTextSize(13);
            gotoContact.setTextSize(13);

        }else if (settings.getCustomSize().equals(ThemeSettings.MEDIUM_SIZE)){

            aboutTitleTV.setTextSize(18);
            aboutTV1.setTextSize(20);
            aboutText1.setTextSize(14);
            aboutText2.setTextSize(14);
            aboutVersionTV.setTextSize(15);
            gotoContact.setTextSize(15);

        }else if (settings.getCustomSize().equals(ThemeSettings.LARGE_SIZE)){

            aboutTitleTV.setTextSize(20);
            aboutTV1.setTextSize(22);
            aboutText1.setTextSize(16);
            aboutText2.setTextSize(16);
            aboutVersionTV.setTextSize(17);
            gotoContact.setTextSize(17);

        }
    }

    // Back Button Intent
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }
}