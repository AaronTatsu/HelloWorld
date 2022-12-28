package com.example.helloworld.AccountEntry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.NotAvailablePage;
import com.example.helloworld.R;
import com.example.helloworld.Settings.ThemeSettings;
import com.example.helloworld.Settings.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    // Register Variables
    private ImageView logoImage, eyeIcon1, eyeIcon2;
    private TextView bckLogin, termsCon, termsPrivacy;
    private Button btnRegister;
    private EditText editFullName, editAge, editEmail, editPassword, editConPassword;
    private ProgressBar progressBar;

    private static final String TAG = "RegisterActivity";

    private FirebaseAuth mAuth;

    // Theme SharedPreferences
    private View registerParentView;
    private TextView logoText, termsAgreementText, termsAgreementText1, alreadyAccTxt;

    private ThemeSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        // Intents
        logoImage = (ImageView) findViewById(R.id.logoImage);
        logoImage.setOnClickListener(this);

        eyeIcon1 = (ImageView) findViewById(R.id.eyeIcon1);
        eyeIcon1.setOnClickListener(this);

        eyeIcon2 = (ImageView) findViewById(R.id.eyeIcon2);
        eyeIcon2.setOnClickListener(this);

        termsCon = (TextView) findViewById(R.id.termsAgreementCon);
        termsCon.setOnClickListener(this);

        termsPrivacy = (TextView) findViewById(R.id.termsAgreementPrivacy);
        termsPrivacy.setOnClickListener(this);

        bckLogin = (TextView) findViewById(R.id.bckLogin);
        bckLogin.setOnClickListener(this);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        editFullName = (EditText) findViewById(R.id.fullname);
        editAge = (EditText) findViewById(R.id.age);
        editEmail = (EditText) findViewById(R.id.emailadd);
        editPassword = (EditText) findViewById(R.id.password);
        editConPassword = (EditText) findViewById(R.id.conPassword);

        progressBar = (ProgressBar) findViewById(R.id.progressBars);


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

        registerParentView = findViewById(R.id.registerParentView);
        logoText = findViewById(R.id.LogoText);
        termsAgreementText = findViewById(R.id.termsAgreementText);
        termsAgreementText1 = findViewById(R.id.termsAgreementText1);
        alreadyAccTxt = findViewById(R.id.alreadyAccTxt);

    }

    // Theme View
    private void updateThemeView() {

        final int black = ContextCompat.getColor(this, R.color.black);
        final int hint_gray = ContextCompat.getColor(this, R.color.hint_gray);
        final int bgblack = ContextCompat.getColor(this, R.color.light_black);
        final int bgwhite = ContextCompat.getColor(this, R.color.light_white);
        final int white = ContextCompat.getColor(this, R.color.white);

        if(settings.getCustomTheme().equals(ThemeSettings.DARK_THEME)){

            logoText.setTextColor(white);
            editFullName.setTextColor(white);
            editFullName.setHintTextColor(white);
            editAge.setTextColor(white);
            editAge.setHintTextColor(white);
            editEmail.setTextColor(white);
            editEmail.setHintTextColor(white);
            editPassword.setTextColor(white);
            editPassword.setHintTextColor(white);
            editConPassword.setTextColor(white);
            editConPassword.setHintTextColor(white);
            termsAgreementText.setTextColor(white);
            termsAgreementText1.setTextColor(white);
            alreadyAccTxt.setTextColor(white);
            btnRegister.setTextColor(black);
            btnRegister.setBackgroundColor(white);
            bckLogin.setTextColor(white);
            eyeIcon1.setColorFilter(white);
            eyeIcon2.setColorFilter(white);
            registerParentView.setBackgroundColor(bgblack);

        }else{

            logoText.setTextColor(black);
            editFullName.setTextColor(black);
            editFullName.setHintTextColor(hint_gray);
            editAge.setTextColor(black);
            editAge.setHintTextColor(hint_gray);
            editEmail.setTextColor(black);
            editEmail.setHintTextColor(hint_gray);
            editPassword.setTextColor(black);
            editPassword.setHintTextColor(hint_gray);
            editConPassword.setTextColor(black);
            editConPassword.setHintTextColor(hint_gray);
            termsAgreementText.setTextColor(black);
            termsAgreementText1.setTextColor(black);
            alreadyAccTxt.setTextColor(black);
            btnRegister.setTextColor(white);
            btnRegister.setBackgroundColor(black);
            bckLogin.setTextColor(black);
            eyeIcon1.setColorFilter(black);
            eyeIcon2.setColorFilter(black);
            registerParentView.setBackgroundColor(bgwhite);
        }
    }

    // Language View
    private void updateLangView() {
        if(settings.getCustomLang().equals(ThemeSettings.ENG_LANG)){

            termsAgreementText.setText("By creating an account, you are agreeing to our ");
            termsAgreementText1.setText("and ");
            btnRegister.setText("REGISTER");
            alreadyAccTxt.setText("Already have an account? ");
            bckLogin.setText("Login Now");
            settings.setCustomLang(ThemeSettings.ENG_LANG);

        }else if (settings.getCustomLang().equals(ThemeSettings.TAG_LANG)){

            termsAgreementText.setText("Sa paggawa ng account, sumasang-ayon ka sa ");
            termsAgreementText1.setText("at ");
            btnRegister.setText("MAG REHISTRO");
            alreadyAccTxt.setText("Mayroon nang account? ");
            bckLogin.setText("Mag Login");
            settings.setCustomLang(ThemeSettings.TAG_LANG);

        }
    }

    // Text Size View
    private void updateSizeView() {
        if(settings.getCustomSize().equals(ThemeSettings.SMALL_SIZE)){

            logoText.setTextSize(38);
            editFullName.setTextSize(16);
            editAge.setTextSize(16);
            editEmail.setTextSize(16);
            editPassword.setTextSize(16);
            editConPassword.setTextSize(16);
            termsAgreementText.setTextSize(7);
            termsAgreementText1.setTextSize(7);
            termsCon.setTextSize(7);
            termsPrivacy.setTextSize(7);
            btnRegister.setTextSize(20);
            alreadyAccTxt.setTextSize(12);
            bckLogin.setTextSize(12);
            settings.setCustomSize(ThemeSettings.SMALL_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.MEDIUM_SIZE)){

            logoText.setTextSize(40);
            editFullName.setTextSize(18);
            editAge.setTextSize(18);
            editEmail.setTextSize(18);
            editPassword.setTextSize(18);
            editConPassword.setTextSize(18);
            termsAgreementText.setTextSize(9);
            termsAgreementText1.setTextSize(9);
            termsCon.setTextSize(9);
            termsPrivacy.setTextSize(9);
            btnRegister.setTextSize(22);
            alreadyAccTxt.setTextSize(14);
            bckLogin.setTextSize(14);
            settings.setCustomSize(ThemeSettings.MEDIUM_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.LARGE_SIZE)){

            logoText.setTextSize(42);
            editFullName.setTextSize(20);
            editAge.setTextSize(20);
            editEmail.setTextSize(20);
            editPassword.setTextSize(20);
            editConPassword.setTextSize(20);
            termsAgreementText.setTextSize(11);
            termsAgreementText1.setTextSize(11);
            termsCon.setTextSize(11);
            termsPrivacy.setTextSize(11);
            btnRegister.setTextSize(24);
            alreadyAccTxt.setTextSize(16);
            bckLogin.setTextSize(16);
            settings.setCustomSize(ThemeSettings.LARGE_SIZE);

        }
    }

    // Register Methods
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logoImage:
                startActivity(new Intent(this, LoginActivity.class));
                break;

            case R.id.eyeIcon1:
                if (editPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    eyeIcon1.setImageResource(R.drawable.invisible_eye_icon);
                } else {
                    editPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    eyeIcon1.setImageResource(R.drawable.visible_eye_icon);
                }
                break;

            case R.id.eyeIcon2:
                if (editConPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    editConPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    eyeIcon2.setImageResource(R.drawable.invisible_eye_icon);
                } else {
                    editConPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    eyeIcon2.setImageResource(R.drawable.visible_eye_icon);
                }
                break;

            case R.id.termsAgreementCon:
                startActivity(new Intent(this, NotAvailablePage.class));
                break;

            case R.id.termsAgreementPrivacy:
                startActivity(new Intent(this, NotAvailablePage.class));
                break;

            case R.id.bckLogin:
                startActivity(new Intent(this, LoginActivity.class));
                break;

            case R.id.btnRegister:
                btnRegister();
                break;
        }

    }

    private void btnRegister() {
        String fullname = editFullName.getText().toString().trim();
        String age = editAge.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        String conpassword = editConPassword.getText().toString().trim();

        if(fullname.isEmpty()){
            editFullName.setError("Name is required");
            editFullName.requestFocus();
            return;
        }
        if(age.isEmpty()){
            editAge.setError("Age is required");
            editAge.requestFocus();
            return;
        }
        if(email.isEmpty()){
            editEmail.setError("Email is required");
            editEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editEmail.setError("Please provide valid email!");
            editEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editPassword.setError("Password is required");
            editPassword.requestFocus();
            return;
        }
        if(!password.equals(conpassword)){
            editPassword.setError("Password are not matching");
            editPassword.requestFocus();
            editConPassword.requestFocus();
            return;
        }
        if(password.length() < 6){
            editPassword.setError("Minimum password length is 6 characters");
            editPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            User user = new User(fullname, age, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                    }else{
                                        Toast.makeText(RegisterActivity.this,"Failed to register! Try Again", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else{
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e){
                                editPassword.setError("Your password is too weak! Please try again.");
                            } catch (FirebaseAuthInvalidCredentialsException e){
                                editEmail.setError("Your email is invalid or already in use.");
                            } catch (FirebaseAuthUserCollisionException e){
                                editEmail.setError("The email is already in used. Please enter another email.");
                            } catch (Exception e){
                                Log.e(TAG, e.getMessage());
                                Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    }
                });
    }
}