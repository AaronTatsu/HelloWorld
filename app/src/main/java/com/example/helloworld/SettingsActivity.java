package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    // For Firebase
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    // For Intent
    Button profbtn;
    ImageView bckBtn, btn1, btn2, btn3, btn4, btn5, btn6;

    // For Theme

    private SwitchCompat themeSwitch;
    private View parentView;
    private TextView settingsTV, userTV, nightmodeTV, notificationsTV, securityTV, langtV, contactTV, aboutTV, FAQsTV, logOutTV;

    private ThemeSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // For Firebase

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView fullNameText = (TextView) findViewById(R.id.userName);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    String fullName = userProfile.fullname;

                    fullNameText.setText(fullName);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SettingsActivity.this, "Something error happened!", Toast.LENGTH_LONG).show();
            }
        });

        // Intents
        profbtn = (Button) findViewById(R.id.Profilebtn);
        profbtn.setOnClickListener(this);

        bckBtn = (ImageView) findViewById(R.id.back_pressed);
        bckBtn.setOnClickListener(this);

        btn1 = (ImageView) findViewById(R.id.settings_btnSecurity);
        btn1.setOnClickListener(this);

        btn2 = (ImageView) findViewById(R.id.settings_btnLang);
        btn2.setOnClickListener(this);

        btn3 = (ImageView) findViewById(R.id.settings_btnMessage);
        btn3.setOnClickListener(this);

        btn4 = (ImageView) findViewById(R.id.settings_btnAbout);
        btn4.setOnClickListener(this);

        btn5 = (ImageView) findViewById(R.id.settings_btnFaqs);
        btn5.setOnClickListener(this);

        btn6 = (ImageView) findViewById(R.id.settings_btnLogOut);
        btn6.setOnClickListener(this);

        //Bottom Navigation Intent
        BottomNavigationView bottomNavigationView = findViewById(R.id.BottonNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.nav_set);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_bmi:
                        startActivity(new Intent(getApplicationContext()
                                , BMIActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_cal:
                        startActivity(new Intent(getApplicationContext()
                                , ExtraActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_vid:
                        startActivity(new Intent(getApplicationContext()
                                , MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_exe:
                        startActivity(new Intent(getApplicationContext()
                                , ExerciseActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_set:
                        startActivity(new Intent(getApplicationContext()
                                , SettingsActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        // For Themes

        settings = (ThemeSettings) getApplication();

        initWidgets();
        loadSharedPreferences();
        initSwitchListener();

    }

    private void initWidgets() {

        parentView = findViewById(R.id.parentView);
        themeSwitch = findViewById(R.id.themeSwitch);
        settingsTV = findViewById(R.id.settingsTV);
        userTV = findViewById(R.id.userName);
        nightmodeTV = findViewById(R.id.nightModeTV);
        notificationsTV = findViewById(R.id.notificationTV);
        securityTV = findViewById(R.id.securityTV);
        langtV = findViewById(R.id.langTV);
        contactTV = findViewById(R.id.contactTV);
        aboutTV = findViewById(R.id.aboutTV);
        FAQsTV = findViewById(R.id.FAQsTV);
        logOutTV = findViewById(R.id.logOutTV);

    }

    private void loadSharedPreferences() {

        SharedPreferences sharedPreferences = getSharedPreferences(ThemeSettings.PREFERENCES,MODE_PRIVATE);
        String theme = sharedPreferences.getString(ThemeSettings.CUSTOM_THEME, ThemeSettings.LIGHT_THEME);
        boolean status = sharedPreferences.getBoolean(ThemeSettings.SWITCH_STATUS, false);
        settings.setSwitchStatus(status);
        settings.setCustomTheme(theme);
        themeSwitch.setChecked(settings.switchStatus);
        updateView();

    }

    private void initSwitchListener() {

        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    settings.setCustomTheme(ThemeSettings.DARK_THEME);
                }else{
                    settings.setCustomTheme(ThemeSettings.LIGHT_THEME);
                }

                SharedPreferences.Editor editor = getSharedPreferences(ThemeSettings.PREFERENCES, MODE_PRIVATE).edit();
                editor.putString(ThemeSettings.CUSTOM_THEME, settings.getCustomTheme());
                editor.apply();
                updateView();
            }
        });
    }

    private void updateView() {

        SharedPreferences.Editor editor = getSharedPreferences(ThemeSettings.PREFERENCES, MODE_PRIVATE).edit();

        final int black = ContextCompat.getColor(this, R.color.black);
        final int bgwhite = ContextCompat.getColor(this, R.color.light_white);
        final int white = ContextCompat.getColor(this, R.color.light_white);

        if(themeSwitch.isChecked()){

            settingsTV.setTextColor(white);
            userTV.setTextColor(white);
            nightmodeTV.setTextColor(white);
            notificationsTV.setTextColor(white);
            securityTV.setTextColor(white);
            langtV.setTextColor(white);
            contactTV.setTextColor(white);
            aboutTV.setTextColor(white);
            FAQsTV.setTextColor(white);
            logOutTV.setTextColor(white);
            parentView.setBackgroundColor(black);
            themeSwitch.setChecked(true);
            editor.putString(ThemeSettings.CUSTOM_THEME, ThemeSettings.DARK_THEME);
            editor.putBoolean(ThemeSettings.SWITCH_STATUS, true);
            editor.apply();


        }else{
            settingsTV.setTextColor(black);
            userTV.setTextColor(black);
            nightmodeTV.setTextColor(black);
            notificationsTV.setTextColor(black);
            securityTV.setTextColor(black);
            langtV.setTextColor(black);
            contactTV.setTextColor(black);
            aboutTV.setTextColor(black);
            FAQsTV.setTextColor(black);
            logOutTV.setTextColor(black);
            parentView.setBackgroundColor(bgwhite);
            themeSwitch.setChecked(false);
            editor.putString(ThemeSettings.CUSTOM_THEME, ThemeSettings.LIGHT_THEME);
            editor.putBoolean(ThemeSettings.SWITCH_STATUS, false);
            editor.apply();
        }
    }

    // Intent View Listener
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Profilebtn:
                startActivity(new Intent(this, UserProfileActivity.class));
                break;

            case R.id.back_pressed:
                startActivity(new Intent(this, MainActivity.class));
                break;

            case R.id.settings_btnSecurity:
                startActivity(new Intent(this, SecurityActivity.class));
                break;

            case R.id.settings_btnLang:
                startActivity(new Intent(this, NotAvailablePage.class));
                break;

            case R.id.settings_btnMessage:
                startActivity(new Intent(this, SettingsContactUs.class));
                break;

            case R.id.settings_btnAbout:
                startActivity(new Intent(this, SettingsAboutUs.class));
                break;

            case R.id.settings_btnFaqs:
                startActivity(new Intent(this, SettingsFrequentAsked.class));
                break;

            case R.id.settings_btnLogOut:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}