package com.example.helloworld.Settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.R;
import com.example.helloworld.SettingsActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SecurityPassword extends AppCompatActivity {

    //For Security Variables
    ImageView bckBtn, eyeIcon1, eyeIcon2;
    TextView currPassAuth;
    ProgressBar progressBar;

    //For Firebase
    private FirebaseUser user;
    private String userID;

    //Theme SharedPreferences
    private View securityPassParentView;
    private TextView securityPassTitleTV;
    private EditText oldPass, newPass, conNewPass;
    private Button updateBtn;
    private String userCurrPass;

    private ThemeSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_password);

        // Security
        progressBar = findViewById(R.id.progressBar);
        eyeIcon1 = findViewById(R.id.eyeIcon1);
        eyeIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newPass.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    newPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    eyeIcon1.setImageResource(R.drawable.invisible_eye_icon);
                } else {
                    newPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    eyeIcon1.setImageResource(R.drawable.visible_eye_icon);
                }
            }
        });
        eyeIcon2 = findViewById(R.id.eyeIcon2);
        eyeIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (conNewPass.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    conNewPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    eyeIcon2.setImageResource(R.drawable.invisible_eye_icon);
                } else {
                    conNewPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    eyeIcon2.setImageResource(R.drawable.visible_eye_icon);
                }
            }
        });


        // Firebase
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();

        // Intents
        oldPass = (EditText) findViewById(R.id.oldPass);
        newPass = (EditText) findViewById(R.id.newPass);
        conNewPass = (EditText) findViewById(R.id.conNewPass);

        updateBtn = findViewById(R.id.updateBtn);
        currPassAuth = findViewById(R.id.currPassAuth);

        newPass.setEnabled(false);
        conNewPass.setEnabled(false);
        updateBtn.setEnabled(false);

        if (user.equals("")){
            Toast.makeText(SecurityPassword.this, "Something went wrong, please check your internet connection!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SecurityPassword.this, SettingsActivity.class);
            startActivity(intent);
            finish();
        }else{
            reAuthenticate(user);
        }


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
    private void reAuthenticate(FirebaseUser user){
        currPassAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userCurrPass = oldPass.getText().toString();

                if(TextUtils.isEmpty(userCurrPass)){
                    Toast.makeText(SecurityPassword.this, "Password is empty", Toast.LENGTH_SHORT).show();
                    oldPass.setError("Please enter your password");
                    oldPass.requestFocus();
                }else {
                    progressBar.setVisibility(View.VISIBLE);

                    AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), userCurrPass);
                    user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                progressBar.setVisibility(View.GONE);

                                oldPass.setEnabled(false);
                                currPassAuth.setEnabled(false);
                                newPass.setEnabled(true);
                                conNewPass.setEnabled(true);
                                updateBtn.setEnabled(true);

                                updateBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        updatePass(user);
                                    }
                                });
                            }else {
                                try {
                                    throw task.getException();
                                } catch (Exception e) {
                                    Toast.makeText(SecurityPassword.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                            progressBar.setVisibility(View.GONE);
                        }
                    });

                }
            }
        });
    }

    // Firebase Update Button
    private void updatePass(FirebaseUser user) {
        String userNewPass = newPass.getText().toString();
        String userConNewPass = conNewPass.getText().toString();

        if(TextUtils.isEmpty(userNewPass)){
            Toast.makeText(SecurityPassword.this, "Password is empty", Toast.LENGTH_SHORT).show();
            newPass.setError("Please enter your password");
            newPass.requestFocus();
        }else if(TextUtils.isEmpty(userConNewPass)) {
            Toast.makeText(SecurityPassword.this, "Password is empty", Toast.LENGTH_SHORT).show();
            conNewPass.setError("Please re-enter your password");
            conNewPass.requestFocus();
        }else if(!userNewPass.matches(userConNewPass)){
            Toast.makeText(SecurityPassword.this, "Password did not match!", Toast.LENGTH_SHORT).show();
            conNewPass.setError("Please re-enter your password");
            conNewPass.requestFocus();
        }else if(userCurrPass.matches(userNewPass)){
            Toast.makeText(SecurityPassword.this, "Password cannot be the same as the old password!", Toast.LENGTH_SHORT).show();
            newPass.setError("Please re-enter your password");
            newPass.requestFocus();
        }else{
            progressBar.setVisibility(View.VISIBLE);

            user.updatePassword(userNewPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SecurityPassword.this, "Password changed successfully!!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SecurityPassword.this, SettingsActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        try {
                            throw task.getException();
                        } catch (Exception e) {
                            Toast.makeText(SecurityPassword.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
    }
}