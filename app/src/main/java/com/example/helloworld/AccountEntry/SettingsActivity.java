package com.example.helloworld.AccountEntry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
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

import com.example.helloworld.BMIActivity;
import com.example.helloworld.ExerciseActivity;
import com.example.helloworld.LoginActivity;
import com.example.helloworld.MainActivity;
import com.example.helloworld.NotAvailablePage;
import com.example.helloworld.R;
import com.example.helloworld.Settings.SecurityActivity;
import com.example.helloworld.Settings.SettingsAboutUs;
import com.example.helloworld.Settings.SettingsContactUs;
import com.example.helloworld.Settings.SettingsFrequentAsked;
import com.example.helloworld.Settings.ThemeSettings;
import com.example.helloworld.Settings.User;
import com.example.helloworld.Settings.UserProfileActivity;
import com.example.helloworld.ToDoListActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
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

    // for Notification

    private SwitchCompat notifSwitch;

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
                                , ToDoListActivity.class));
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
        initThemeSwitchListener();
        initNotifSwitchListener();

    }

    private void initWidgets() {

        //Theme
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

        //Notification
        notifSwitch = findViewById(R.id.notifSwitch);

    }

    private void loadSharedPreferences() {

        SharedPreferences sharedPreferences = getSharedPreferences(ThemeSettings.PREFERENCES,MODE_PRIVATE);

        //Theme
        String theme = sharedPreferences.getString(ThemeSettings.CUSTOM_THEME, ThemeSettings.LIGHT_THEME);
        settings.setCustomTheme(theme);
        updateThemeView();

        //Notif
        String notif = sharedPreferences.getString(ThemeSettings.CUSTOM_NOTIF, ThemeSettings.NOTIF_ON);
        settings.setCustomNotif(notif);
        updateNotifView();

    }

    private void initThemeSwitchListener() {

        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    Toast.makeText(SettingsActivity.this, "Night Mode is turned on!", Toast.LENGTH_SHORT).show();
                    settings.setCustomTheme(ThemeSettings.DARK_THEME);
                }else{
                    Toast.makeText(SettingsActivity.this, "Night Mode is turned off!", Toast.LENGTH_SHORT).show();
                    settings.setCustomTheme(ThemeSettings.LIGHT_THEME);
                }

                SharedPreferences.Editor editor = getSharedPreferences(ThemeSettings.PREFERENCES, MODE_PRIVATE).edit();
                editor.putString(ThemeSettings.CUSTOM_THEME, settings.getCustomTheme());
                editor.apply();
                updateThemeView();
            }
        });
    }

    private void updateThemeView() {

        final int black = ContextCompat.getColor(this, R.color.black);
        final int bgblack = ContextCompat.getColor(this, R.color.light_black);
        final int bgwhite = ContextCompat.getColor(this, R.color.light_white);
        final int white = ContextCompat.getColor(this, R.color.light_white);

        if(settings.getCustomTheme().equals(ThemeSettings.DARK_THEME)){

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
            parentView.setBackgroundColor(bgblack);
            themeSwitch.setChecked(true);

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
        }
    }

    private void initNotifSwitchListener() {

        notifSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    Toast.makeText(SettingsActivity.this, "Notification is turned on!", Toast.LENGTH_SHORT).show();
                    settings.setCustomNotif(ThemeSettings.NOTIF_ON);
                } else {
                    Toast.makeText(SettingsActivity.this, "Notification is turned off!", Toast.LENGTH_SHORT).show();
                    settings.setCustomNotif(ThemeSettings.NOTIF_OFF);
                }

                SharedPreferences.Editor editor = getSharedPreferences(ThemeSettings.PREFERENCES, MODE_PRIVATE).edit();
                editor.putString(ThemeSettings.CUSTOM_NOTIF, settings.getCustomNotif());
                editor.apply();
                updateNotifView();
            }
        });
    }
    private void updateNotifView() {
        if(settings.getCustomNotif().equals(ThemeSettings.NOTIF_ON)){

            notifSwitch.setChecked(true);
        }else{

            notifSwitch.setChecked(false);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setTitle("Confirm Logout");
                builder.setMessage("Are you sure you want to log out?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(SettingsActivity.this, "Successfully Logged Out!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                break;
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}