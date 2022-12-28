package com.example.helloworld.AccountEntry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.R;
import com.example.helloworld.Settings.ThemeSettings;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    // Forgot Variables
    private EditText editEmailAdd;
    private Button btnForgot;
    private ProgressBar progressBar;

    FirebaseAuth mauth;

    // Theme SharedPreferences
    private View forgotParentView;
    private TextView logoText;

    private ThemeSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // Forgot Pass
        editEmailAdd = (EditText) findViewById(R.id.editEmailAdd);
        btnForgot = (Button) findViewById(R.id.btnForgot);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mauth = FirebaseAuth.getInstance();

        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });

        //Theme SharedPreferences
        settings = (ThemeSettings) getApplication();

        initWidgets();
        loadSharedPreferences();
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

    private void initWidgets() {

        forgotParentView = findViewById(R.id.forgotParentView);
        logoText = findViewById(R.id.LogoText);

    }

    // Theme View
    private void updateThemeView() {

        final int black = ContextCompat.getColor(this, R.color.black);
        final int hint_gray = ContextCompat.getColor(this, R.color.hint_gray);
        final int gray = ContextCompat.getColor(this, R.color.dark_gray);
        final int bgblack = ContextCompat.getColor(this, R.color.light_black);
        final int bgwhite = ContextCompat.getColor(this, R.color.light_white);
        final int white = ContextCompat.getColor(this, R.color.white);

        if(settings.getCustomTheme().equals(ThemeSettings.DARK_THEME)){

            logoText.setTextColor(white);
            editEmailAdd.setTextColor(white);
            editEmailAdd.setHintTextColor(white);
            btnForgot.setTextColor(black);
            btnForgot.setBackgroundColor(white);
            forgotParentView.setBackgroundColor(bgblack);

        }else{

            logoText.setTextColor(black);
            editEmailAdd.setTextColor(black);
            editEmailAdd.setHintTextColor(hint_gray);
            btnForgot.setTextColor(white);
            btnForgot.setBackgroundColor(black);
            forgotParentView.setBackgroundColor(bgwhite);
        }
    }

    // Language View
    private void updateLangView() {
        if(settings.getCustomLang().equals(ThemeSettings.ENG_LANG)){

            btnForgot.setText("RESET PASSWORD");
            settings.setCustomLang(ThemeSettings.ENG_LANG);

        }else if (settings.getCustomLang().equals(ThemeSettings.TAG_LANG)){

            btnForgot.setText("I-RESET ANG PASSWORD");
            settings.setCustomLang(ThemeSettings.TAG_LANG);

        }
    }

    // Text Size View
    private void updateSizeView() {
        if(settings.getCustomSize().equals(ThemeSettings.SMALL_SIZE)){

            logoText.setTextSize(38);
            editEmailAdd.setTextSize(16);
            btnForgot.setTextSize(20);
            settings.setCustomSize(ThemeSettings.SMALL_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.MEDIUM_SIZE)){

            logoText.setTextSize(40);
            editEmailAdd.setTextSize(18);
            btnForgot.setTextSize(22);
            settings.setCustomSize(ThemeSettings.MEDIUM_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.LARGE_SIZE)){

            logoText.setTextSize(42);
            editEmailAdd.setTextSize(20);
            btnForgot.setTextSize(24);
            settings.setCustomSize(ThemeSettings.LARGE_SIZE);

        }
    }

    // Forgot Pass Method
    private void resetPassword() {
        String email = editEmailAdd.getText().toString().trim();

        if(email.isEmpty()){
            editEmailAdd.setError("Email is required");
            editEmailAdd.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editEmailAdd.setError("Please provide a valid email!");
            editEmailAdd.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mauth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(ForgotPasswordActivity.this, "Please check your email to reset your password!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ForgotPasswordActivity.this, "Something error happened! Please try again!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}