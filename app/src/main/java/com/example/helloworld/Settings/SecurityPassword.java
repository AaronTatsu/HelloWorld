package com.example.helloworld.Settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helloworld.R;
import com.example.helloworld.SettingsActivity;

public class SecurityPassword extends AppCompatActivity {

    //For Intent
    ImageView bckBtn;

    //Theme SharedPreferences
    private View securityPassParentView;
    private TextView securityPassTitleTV;
    private EditText oldPass, newPass, conNewPass;
    private Button updateBtn;

    private ThemeSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_password);

        // Intents
        oldPass = (EditText) findViewById(R.id.oldPass);
        newPass = (EditText) findViewById(R.id.newPass);
        conNewPass = (EditText) findViewById(R.id.conNewPass);

        //For Back Button Intent
        bckBtn = findViewById(R.id.back_pressed);
        bckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
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

        securityPassParentView = findViewById(R.id.securityPassParentView);
        securityPassTitleTV = findViewById(R.id.securityPassTitleTV);
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

            securityPassTitleTV.setTextColor(white);
            securityPassParentView.setBackgroundColor(bgblack);

        }else{

            securityPassTitleTV.setTextColor(black);
            securityPassParentView.setBackgroundColor(bgwhite);
        }
    }

    // Language View
    private void updateLangView() {
        if(settings.getCustomLang().equals(ThemeSettings.ENG_LANG)){

            securityPassTitleTV.setText("Change Password");
            oldPass.setHint("Old Password");
            newPass.setHint("New Password");
            conNewPass.setHint("Confirm New Password");
            settings.setCustomLang(ThemeSettings.ENG_LANG);

        }else if (settings.getCustomLang().equals(ThemeSettings.TAG_LANG)){

            securityPassTitleTV.setText("Palitan ang Password");
            oldPass.setHint("Lumang Password");
            newPass.setHint("Bagong Password");
            conNewPass.setHint("Kinumpirmang Bagong Password");
            settings.setCustomLang(ThemeSettings.TAG_LANG);

        }
    }

    // Text Size View
    private void updateSizeView() {
        if(settings.getCustomSize().equals(ThemeSettings.SMALL_SIZE)){

            securityPassTitleTV.setTextSize(16);
            oldPass.setTextSize(13);
            newPass.setTextSize(13);
            conNewPass.setTextSize(13);
            settings.setCustomSize(ThemeSettings.SMALL_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.MEDIUM_SIZE)){

            securityPassTitleTV.setTextSize(18);
            oldPass.setTextSize(15);
            newPass.setTextSize(15);
            conNewPass.setTextSize(15);
            settings.setCustomSize(ThemeSettings.MEDIUM_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.LARGE_SIZE)){

            securityPassTitleTV.setTextSize(20);
            oldPass.setTextSize(17);
            newPass.setTextSize(17);
            conNewPass.setTextSize(17);
            settings.setCustomSize(ThemeSettings.LARGE_SIZE);

        }
    }
}