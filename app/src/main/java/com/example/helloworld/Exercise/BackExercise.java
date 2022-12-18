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

public class BackExercise extends AppCompatActivity {

    // Intents
    ImageView bckBtn, btn1, btn2, btn3;

    //Theme SharedPreferences
    private View backParentView;
    private TextView backTitleTV;

    private ThemeSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_exercise);

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
        btn1 = findViewById(R.id.back_btn_1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BackExercise_1.class);
                startActivity(intent);
            }
        });

        btn2 = findViewById(R.id.back_btn_2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BackExercise_2.class);
                startActivity(intent);
            }
        });

        btn3 = findViewById(R.id.back_btn_3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BackExercise_3.class);
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

        backParentView = findViewById(R.id.backParentView);
        backTitleTV = findViewById(R.id.backTitleTV);
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

            backTitleTV.setTextColor(white);
            backParentView.setBackgroundColor(bgblack);

        }else{

            backTitleTV.setTextColor(black);
            backParentView.setBackgroundColor(bgwhite);
        }
    }

    // Back Button Intent
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), ExerciseActivity.class);
        startActivity(intent);
    }
}