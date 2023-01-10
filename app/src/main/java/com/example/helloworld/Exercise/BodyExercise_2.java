package com.example.helloworld.Exercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.helloworld.NotAvailablePage;
import com.example.helloworld.R;
import com.example.helloworld.Settings.ThemeSettings;

public class BodyExercise_2 extends AppCompatActivity {

    //OnBackPressed
    ImageView bckBtn, btn1, btn2;

    // CountDownTimer
    SeekBar timer_sb;
    TextView timer_tv;
    Button start_btn;
    CountDownTimer countDownTimer;
    Boolean counterIsActive = false;
    MediaPlayer mediaPlayer;

    //Theme SharedPreferences
    private View bodyClimbersParentView;
    private TextView bodyClimbersTV1, bodyClimbersTV2, bodyClimbersTV3;

    private ThemeSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_exercise2);

        //OnBackPressed Function
        bckBtn = findViewById(R.id.back_pressed);
        bckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BodyExercise.class);
                startActivity(intent);
            }
        });

        //View in AR
        btn1 = findViewById(R.id.ARbtn);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NotAvailablePage.class);
                startActivity(intent);
            }
        });

        //View in Text
        btn2 = findViewById(R.id.textbtn);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BodyExercise_2_text.class);
                startActivity(intent);
            }
        });
        // CountDownTimerFunction
        timer_sb = findViewById(R.id.timer_sb);
        timer_tv = findViewById(R.id.timer_tv);
        start_btn = findViewById(R.id.start_btn);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
        timer_sb.setMax(300);
        timer_sb.setProgress(60);
        timer_sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                update(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Theme SharedPreferences
        settings = (ThemeSettings) getApplication();

        initWidgets();
        loadSharedPreferences();
        updateThemeView();
    }
    private void initWidgets() {

        bodyClimbersParentView = findViewById(R.id.bodyClimbersParentView);
        bodyClimbersTV1 = findViewById(R.id.bodyClimbersTV1);
        bodyClimbersTV2 = findViewById(R.id.bodyClimbersTV2);
        bodyClimbersTV3 = findViewById(R.id.bodyClimbersTV3);

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

            bodyClimbersTV1.setTextColor(white);
            bodyClimbersTV2.setTextColor(white);
            bodyClimbersTV3.setTextColor(white);
            timer_tv.setTextColor(white);
            bodyClimbersParentView.setBackgroundColor(bgblack);

        }else{

            bodyClimbersTV1.setTextColor(black);
            bodyClimbersTV2.setTextColor(black);
            bodyClimbersTV3.setTextColor(black);
            timer_tv.setTextColor(black);
            bodyClimbersParentView.setBackgroundColor(bgwhite);
        }
    }

    // Language View
    private void updateLangView() {
        if(settings.getCustomLang().equals(ThemeSettings.ENG_LANG)){

            bodyClimbersTV1.setText("Mountain Climbers");
            bodyClimbersTV2.setText("View in AR");
            bodyClimbersTV3.setText("View in Text");
            start_btn.setText("START");
            settings.setCustomLang(ThemeSettings.ENG_LANG);

        }else if (settings.getCustomLang().equals(ThemeSettings.TAG_LANG)){

            bodyClimbersTV1.setText("Mamumundok");
            bodyClimbersTV2.setText("Tignan sa AR");
            bodyClimbersTV3.setText("Tignan sa Teksto");
            start_btn.setText("SIMULAN");
            settings.setCustomLang(ThemeSettings.TAG_LANG);

        }
    }

    // Text Size View
    private void updateSizeView() {
        if(settings.getCustomSize().equals(ThemeSettings.SMALL_SIZE)){

            bodyClimbersTV1.setTextSize(24);
            bodyClimbersTV2.setTextSize(12);
            bodyClimbersTV3.setTextSize(12);
            start_btn.setTextSize(12);
            settings.setCustomSize(ThemeSettings.SMALL_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.MEDIUM_SIZE)){

            bodyClimbersTV1.setTextSize(26);
            bodyClimbersTV2.setTextSize(14);
            bodyClimbersTV3.setTextSize(14);
            start_btn.setTextSize(14);
            settings.setCustomSize(ThemeSettings.MEDIUM_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.LARGE_SIZE)){

            bodyClimbersTV1.setTextSize(28);
            bodyClimbersTV2.setTextSize(16);
            bodyClimbersTV3.setTextSize(16);
            start_btn.setTextSize(16);
            settings.setCustomSize(ThemeSettings.LARGE_SIZE);

        }
    }

    // Countdown Methods
    private void update(int progress) {
        int minutes = progress / 60;
        int seconds = progress % 60;
        String secondsFinal = "";
        if(seconds <= 9){
            secondsFinal = "0" + seconds;
        }else{
            secondsFinal = "" + seconds;
        }
        timer_sb.setProgress(progress);
        timer_tv.setText("" + minutes + ":" + secondsFinal);
    }

    public void start_timer(View view) {
        if(counterIsActive == false){
            counterIsActive = true;
            timer_sb.setEnabled(false);
            start_btn.setText("STOP");
            countDownTimer = new CountDownTimer(timer_sb.getProgress() * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    update((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    reset();
                    if (mediaPlayer != null)
                        mediaPlayer.start();
                }
            }.start();
        }else{
            reset();
        }
    }

    private void reset() {
        timer_tv.setText("1:00");
        timer_sb.setProgress(60);
        countDownTimer.cancel();
        start_btn.setText("START");
        timer_sb.setEnabled(true);
        counterIsActive = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(counterIsActive){
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(counterIsActive){
            countDownTimer.cancel();
        }
    }
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), BodyExercise.class);
        startActivity(intent);
    }
}