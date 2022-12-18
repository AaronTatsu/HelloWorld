package com.example.helloworld.Settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.helloworld.R;

public class SecurityActivity extends AppCompatActivity {

    private TextView titleTV;
    private View parentViews;

    private ThemeSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);

        settings = (ThemeSettings) getApplication();

        initWidgets();
        loadSharedPreferences();;
        updateView();

    }

    private void initWidgets() {
        titleTV = findViewById(R.id.TitleTV);
        parentViews = findViewById(R.id.parentViews);

    }

    private void loadSharedPreferences() {

        SharedPreferences sharedPreferences = getSharedPreferences(ThemeSettings.PREFERENCES,MODE_PRIVATE);
        String theme = sharedPreferences.getString(ThemeSettings.CUSTOM_THEME, ThemeSettings.LIGHT_THEME);
        settings.setCustomTheme(theme);
    }

    private void updateView() {

        final int black = ContextCompat.getColor(this, R.color.black);
        final int white = ContextCompat.getColor(this, R.color.light_white);

        if(settings.getCustomTheme().equals(ThemeSettings.DARK_THEME)){
            titleTV.setTextColor(white);
            parentViews.setBackgroundColor(black);

        }else{
            titleTV.setTextColor(black);
            parentViews.setBackgroundColor(white);
        }
    }
}