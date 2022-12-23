package com.example.helloworld;

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

import com.airbnb.lottie.L;
import com.example.helloworld.AccountEntry.LoginActivity;
import com.example.helloworld.Settings.SecurityActivity;
import com.example.helloworld.Settings.SettingsAboutUs;
import com.example.helloworld.Settings.SettingsContactUs;
import com.example.helloworld.Settings.SettingsFrequentAsked;
import com.example.helloworld.Settings.ThemeSettings;
import com.example.helloworld.Settings.User;
import com.example.helloworld.Settings.UserProfileActivity;
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
    ImageView bckBtn, btn1, btn2, btn3, btn4, btn5, btn6, btn7;

    // For Theme
    private SwitchCompat themeSwitch;
    private View parentView;
    private TextView settingsTV, userTV, nightmodeTV, notificationsTV, securityTV, langtV, sizeTV, contactTV, aboutTV, FAQsTV, logOutTV;

    private ThemeSettings settings;

    // For Notification
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

        btn3 = (ImageView) findViewById(R.id.settings_btnSize);
        btn3.setOnClickListener(this);

        btn4 = (ImageView) findViewById(R.id.settings_btnMessage);
        btn4.setOnClickListener(this);

        btn5 = (ImageView) findViewById(R.id.settings_btnAbout);
        btn5.setOnClickListener(this);

        btn6 = (ImageView) findViewById(R.id.settings_btnFaqs);
        btn6.setOnClickListener(this);

        btn7 = (ImageView) findViewById(R.id.settings_btnLogOut);
        btn7.setOnClickListener(this);

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
        updateThemeView();
        updateLangView();
        updateSizeView();

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
        sizeTV = findViewById(R.id.sizeTV);
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

        //Lang
        String lang = sharedPreferences.getString(ThemeSettings.CUSTOM_LANG, ThemeSettings.ENG_LANG);
        settings.setCustomLang(lang);
        updateLangView();

        //Size
        String size = sharedPreferences.getString(ThemeSettings.CUSTOM_SIZE, ThemeSettings.MEDIUM_SIZE);
        settings.setCustomSize(size);
        updateSizeView();

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
            sizeTV.setTextColor(white);
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
            sizeTV.setTextColor(black);
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
                showChangeLanguageDialog();
                break;

            case R.id.settings_btnSize:
                showChangeSizeDialog();
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

    // Language Settings
    private void showChangeLanguageDialog() {
        final String[] listItems = {"English", "Filipino"};
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
        builder.setTitle("Choose Language...");
        builder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int lang) {
                if(lang == 0){
                    Toast.makeText(SettingsActivity.this, "English Language Selected!", Toast.LENGTH_SHORT).show();
                    settings.setCustomLang(ThemeSettings.ENG_LANG);
                    dialog.dismiss();
                }else if (lang == 1){
                    Toast.makeText(SettingsActivity.this, "Filipino Language Selected!", Toast.LENGTH_SHORT).show();
                    settings.setCustomLang(ThemeSettings.TAG_LANG);
                    dialog.dismiss();
                }

                SharedPreferences.Editor editor = getSharedPreferences(ThemeSettings.PREFERENCES, MODE_PRIVATE).edit();
                editor.putString(ThemeSettings.CUSTOM_LANG, settings.getCustomLang());
                editor.apply();
                updateLangView();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void updateLangView() {
        if(settings.getCustomLang().equals(ThemeSettings.ENG_LANG)){

            settingsTV.setText("Settings");
            profbtn.setText("Edit Profile");
            nightmodeTV.setText("Dark Mode");
            notificationsTV.setText("Notifications");
            securityTV.setText("Security and Privacy");
            langtV.setText("Languages");
            sizeTV.setText("Text Size");
            contactTV.setText("Contact Us");
            aboutTV.setText("About Us");
            FAQsTV.setText("FAQs");
            logOutTV.setText("Log Out");
            settings.setCustomLang(ThemeSettings.ENG_LANG);

        }else if (settings.getCustomLang().equals(ThemeSettings.TAG_LANG)){

            settingsTV.setText("Mga Setting");
            profbtn.setText("Porpolyo'y Baguhin");
            nightmodeTV.setText("Gawing Gabi");
            notificationsTV.setText("Abiso");
            securityTV.setText("Seguridad");
            langtV.setText("Wika");
            sizeTV.setText("Laki ng Teksto");
            contactTV.setText("Kontak");
            aboutTV.setText("Info ng gumawa");
            FAQsTV.setText("Mga Madalas na Katanungan");
            logOutTV.setText("Pag Alis");
            settings.setCustomLang(ThemeSettings.TAG_LANG);

        }
    }

    // Text Size Settings
    private void showChangeSizeDialog() {
        final String[] listItems = {"Small", "Medium", "Large"};
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
        builder.setTitle("Choose Text Size...");
        builder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int size) {
                if(size == 0){
                    Toast.makeText(SettingsActivity.this, "Small Text Size Selected!", Toast.LENGTH_SHORT).show();
                    settings.setCustomSize(ThemeSettings.SMALL_SIZE);
                    dialog.dismiss();
                }
                else if (size == 1){
                    Toast.makeText(SettingsActivity.this, "Medium Text Size Selected!", Toast.LENGTH_SHORT).show();
                    settings.setCustomSize(ThemeSettings.MEDIUM_SIZE);
                    dialog.dismiss();
                }else if (size == 2){
                    Toast.makeText(SettingsActivity.this, "Large Text Size Selected!", Toast.LENGTH_SHORT).show();
                    settings.setCustomSize(ThemeSettings.LARGE_SIZE);
                    dialog.dismiss();
                }

                SharedPreferences.Editor editor = getSharedPreferences(ThemeSettings.PREFERENCES, MODE_PRIVATE).edit();
                editor.putString(ThemeSettings.CUSTOM_SIZE, settings.getCustomSize());
                editor.apply();
                updateSizeView();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
    private void updateSizeView() {
        if(settings.getCustomSize().equals(ThemeSettings.SMALL_SIZE)){

            settingsTV.setTextSize(16);
            profbtn.setTextSize(13);
            nightmodeTV.setTextSize(12);
            notificationsTV.setTextSize(12);
            securityTV.setTextSize(12);
            langtV.setTextSize(12);
            sizeTV.setTextSize(12);
            contactTV.setTextSize(12);
            aboutTV.setTextSize(12);
            FAQsTV.setTextSize(12);
            logOutTV.setTextSize(12);
            settings.setCustomSize(ThemeSettings.SMALL_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.MEDIUM_SIZE)){

            settingsTV.setTextSize(18);
            profbtn.setTextSize(15);
            nightmodeTV.setTextSize(14);
            notificationsTV.setTextSize(14);
            securityTV.setTextSize(14);
            langtV.setTextSize(14);
            sizeTV.setTextSize(14);
            contactTV.setTextSize(14);
            aboutTV.setTextSize(14);
            FAQsTV.setTextSize(14);
            logOutTV.setTextSize(14);
            settings.setCustomSize(ThemeSettings.MEDIUM_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.LARGE_SIZE)){

            settingsTV.setTextSize(20);
            profbtn.setTextSize(17);
            nightmodeTV.setTextSize(16);
            notificationsTV.setTextSize(16);
            securityTV.setTextSize(16);
            langtV.setTextSize(16);
            sizeTV.setTextSize(16);
            contactTV.setTextSize(16);
            aboutTV.setTextSize(16);
            FAQsTV.setTextSize(16);
            logOutTV.setTextSize(16);
            settings.setCustomSize(ThemeSettings.LARGE_SIZE);

        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}