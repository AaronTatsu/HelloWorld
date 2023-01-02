package com.example.helloworld.AccountEntry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.MainActivity;
import com.example.helloworld.R;
import com.example.helloworld.Settings.ThemeSettings;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    // Log In Activity Methods
    private ImageView eyeIcon;
    private TextView register, forgotpass;
    private EditText editEmail, editPassword;
    private Button btnLogin;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    // Theme SharedPreferences
    private View loginParentView;
    ImageView langBtn;
    private TextView logoText, registerTV;

    private ThemeSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Intents
        eyeIcon = (ImageView) findViewById(R.id.eyeIcon);
        eyeIcon.setOnClickListener(this);

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        forgotpass = (TextView) findViewById(R.id.forgotPass);
        forgotpass.setOnClickListener(this);

        btnLogin = (Button) findViewById(R.id.btnlogin);
        btnLogin.setOnClickListener(this);

        editEmail = (EditText) findViewById(R.id.editEmailAdd);
        editPassword = (EditText) findViewById(R.id.editPassword);

        mAuth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        //Theme SharedPreferences
        langBtn = (ImageView) findViewById(R.id.langBtn);

        settings = (ThemeSettings) getApplication();

        initWidgets();
        loadSharedPreferences();
        langClickListener();

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

        loginParentView = findViewById(R.id.loginParentView);
        logoText = findViewById(R.id.LogoText);
        registerTV = findViewById(R.id.registerTV);

    }

    private void langClickListener(){
        langBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(LoginActivity.this, langBtn);

                popupMenu.getMenuInflater().inflate(R.menu.lang_pop_up_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.nav_eng:
                                Toast.makeText(LoginActivity.this, "Selected English Language", Toast.LENGTH_SHORT).show();
                                settings.setCustomLang(ThemeSettings.ENG_LANG);
                                SharedPreferences.Editor editor = getSharedPreferences(ThemeSettings.PREFERENCES, MODE_PRIVATE).edit();
                                editor.putString(ThemeSettings.CUSTOM_LANG, settings.getCustomLang());
                                editor.apply();
                                updateLangView();
                                return true;

                            case R.id.nav_tag:
                                Toast.makeText(LoginActivity.this, "Selected Tagalog Language", Toast.LENGTH_SHORT).show();
                                settings.setCustomLang(ThemeSettings.TAG_LANG);
                                editor = getSharedPreferences(ThemeSettings.PREFERENCES, MODE_PRIVATE).edit();
                                editor.putString(ThemeSettings.CUSTOM_LANG, settings.getCustomLang());
                                editor.apply();
                                updateLangView();
                                return true;
                        }
                        return false;
                    }
                });
                popupMenu.show();

                MenuPopupHelper menuHelper = new MenuPopupHelper(LoginActivity.this, (MenuBuilder) popupMenu.getMenu(), langBtn);
                menuHelper.setForceShowIcon(true);
                menuHelper.show();
            }
        });
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
            editEmail.setTextColor(white);
            editEmail.setHintTextColor(white);
            editPassword.setTextColor(white);
            editPassword.setHintTextColor(white);
            forgotpass.setTextColor(white);
            register.setTextColor(white);
            btnLogin.setTextColor(black);
            btnLogin.setBackgroundColor(white);
            registerTV.setTextColor(white);
            eyeIcon.setColorFilter(white);
            loginParentView.setBackgroundColor(bgblack);

        }else{

            logoText.setTextColor(black);
            editEmail.setTextColor(black);
            editEmail.setHintTextColor(hint_gray);
            editPassword.setTextColor(black);
            editPassword.setHintTextColor(hint_gray);
            forgotpass.setTextColor(gray);
            register.setTextColor(black);
            btnLogin.setTextColor(white);
            btnLogin.setBackgroundColor(black);
            registerTV.setTextColor(black);
            eyeIcon.setColorFilter(black);
            loginParentView.setBackgroundColor(bgwhite);
        }
    }

    // Language View
    private void updateLangView() {
        if(settings.getCustomLang().equals(ThemeSettings.ENG_LANG)){

            btnLogin.setText("LOGIN");
            registerTV.setText("Don't have an account?");
            register.setText("Sign Up Now");
            forgotpass.setText("Forgot Password?");
            settings.setCustomLang(ThemeSettings.ENG_LANG);

        }else if (settings.getCustomLang().equals(ThemeSettings.TAG_LANG)){

            btnLogin.setText("PUMASOK");
            registerTV.setText("Wala pang account?");
            register.setText("Mag sign up");
            forgotpass.setText("Nakalimutan ang Password?");
            settings.setCustomLang(ThemeSettings.TAG_LANG);

        }
    }

    // Text Size View
    private void updateSizeView() {
        if(settings.getCustomSize().equals(ThemeSettings.SMALL_SIZE)){

            logoText.setTextSize(38);
            editPassword.setTextSize(16);
            editEmail.setTextSize(16);
            btnLogin.setTextSize(20);
            register.setTextSize(16);
            forgotpass.setTextSize(13);
            settings.setCustomSize(ThemeSettings.SMALL_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.MEDIUM_SIZE)){

            logoText.setTextSize(40);
            editPassword.setTextSize(18);
            editEmail.setTextSize(18);
            btnLogin.setTextSize(22);
            register.setTextSize(18);
            forgotpass.setTextSize(15);
            settings.setCustomSize(ThemeSettings.MEDIUM_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.LARGE_SIZE)){

            logoText.setTextSize(42);
            editPassword.setTextSize(20);
            editEmail.setTextSize(20);
            btnLogin.setTextSize(24);
            register.setTextSize(20);
            forgotpass.setTextSize(17);
            settings.setCustomSize(ThemeSettings.LARGE_SIZE);

        }
    }

    // Eye Icon Functions
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.eyeIcon:
                if (editPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    eyeIcon.setImageResource(R.drawable.invisible_eye_icon);
                } else {
                    editPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    eyeIcon.setImageResource(R.drawable.visible_eye_icon);
                }
                break;

            case R.id.register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;

            case R.id.forgotPass:
                startActivity(new Intent(this, ForgotPasswordActivity.class));
                break;

            case R.id.btnlogin:
                btnLogin();
                break;
        }
    }

    // Login Functions
    private void btnLogin() {
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editEmail.setError("Email is required");
            editEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError("Please provide a valid email!");
            editEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editPassword.setError("Password is required");
            editPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editPassword.setError("Minimum password length is 6 characters");
            editPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if (user.isEmailVerified()) {
                        Toast.makeText(LoginActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        user.sendEmailVerification();
                        Toast.makeText(LoginActivity.this, "Please check your email to verify your account!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Failed to login! Try again!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    // Auto Login Functions
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (mAuth.getCurrentUser() != null && user.isEmailVerified()){
            Toast.makeText(this, "Already logged in!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }else{
            return;
        }
    }
    public void onBackPressed() {
        Toast.makeText(LoginActivity.this, "Press Home Button to exit", Toast.LENGTH_SHORT).show();
    }
}