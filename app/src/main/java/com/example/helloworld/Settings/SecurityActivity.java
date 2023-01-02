package com.example.helloworld.Settings;

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

public class SecurityActivity extends AppCompatActivity {

    ImageView bckBtn, btnPass;
    private TextView securityTitleTV, securityTV;
    private View securityParentView;

    private ThemeSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);

        //Back Button Pressed
        bckBtn = findViewById(R.id.back_pressed);
        bckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecurityActivity.class);
                startActivity(intent);
            }
        });

        //Back Button Pressed
        btnPass = findViewById(R.id.btnPass);
        btnPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecurityPassword.class);
                startActivity(intent);
            }
        });

    // Theme SharedPreferences
        settings = (ThemeSettings) getApplication();

        initWidgets();
        loadSharedPreferences();;
        updateView();

    }

    private void initWidgets() {
        securityTitleTV = findViewById(R.id.securityTitleTV);
        securityTV = findViewById(R.id.securityTV);
        securityParentView = findViewById(R.id.securityParentView);

    }

    private void loadSharedPreferences() {

        SharedPreferences sharedPreferences = getSharedPreferences(ThemeSettings.PREFERENCES,MODE_PRIVATE);
        String theme = sharedPreferences.getString(ThemeSettings.CUSTOM_THEME, ThemeSettings.LIGHT_THEME);
        settings.setCustomTheme(theme);
    }

    private void updateView() {

        final int black = ContextCompat.getColor(this, R.color.light_black);
        final int white = ContextCompat.getColor(this, R.color.light_white);

        if(settings.getCustomTheme().equals(ThemeSettings.DARK_THEME)){
            securityTitleTV.setTextColor(white);
            securityTV.setTextColor(white);
            securityParentView.setBackgroundColor(black);

        }else{
            securityTitleTV.setTextColor(black);
            securityTV.setTextColor(black);
            securityParentView.setBackgroundColor(white);
        }
    }
}