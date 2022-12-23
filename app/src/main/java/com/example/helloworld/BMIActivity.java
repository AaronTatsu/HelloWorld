package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.Settings.ThemeSettings;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BMIActivity extends AppCompatActivity {

    // BMI Function
    EditText height, weight;
    ImageView male, female;
    LinearLayout malelayout, femalelayout;
    Button btncal;
    TextView result, condition;
    float h=0,w=0,bmi=0;
    String user="0";
    String res;

    // Theme Function
    private View bmiParentView;
    private TextView bmiTitleTV, bmiCalculator, txtHeight, txtWeight, txtMale, txtFemale;
    private ThemeSettings settings;

    //Button Intent
    ImageView bckBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiactivity);

        //BackButtonPressed
        bckBtn = findViewById(R.id.back_pressed);
        bckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        // BMI Function
        height=findViewById(R.id.Height);
        weight=findViewById(R.id.Weight);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        malelayout=findViewById(R.id.mlayout);
        femalelayout=findViewById(R.id.flayout);
        btncal=findViewById(R.id.bmibtn);
        result=findViewById(R.id.bmiresult);
        condition=findViewById(R.id.bmicondition);

        malelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male.setColorFilter(getResources().getColor(R.color.light_blue));
                female.setColorFilter(getResources().getColor(R.color.black));
                user="Male";
            }
        });
        femalelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                female.setColorFilter(getResources().getColor(R.color.light_blue));
                male.setColorFilter(getResources().getColor(R.color.black));
                user="Female";
            }
        });

        btncal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = height.getText().toString();
                String str2 = weight.getText().toString();
                if (user.equals("0")){
                    Toast.makeText(BMIActivity.this, "Please Enter your Gender", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(str1)){
                    height.setError("Enter Height");
                    height.requestFocus();
                    return;
                }
                else if (!TextUtils.isDigitsOnly(str1)){
                    height.setError("Please insert numbers only");
                    height.requestFocus();
                    return;
                }
                else if (TextUtils.isEmpty(str2)){
                    weight.setError("Enter Weight");
                    weight.requestFocus();
                    return;
                }
                else if (!TextUtils.isDigitsOnly(str2)){
                    height.setError("Please insert numbers only");
                    height.requestFocus();
                    return;
                }
                else
                {
                    calculate();
                }
            }
        });

        //Bottom Intent
        BottomNavigationView bottomNavigationView = findViewById(R.id.BottonNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.nav_bmi);
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

        //Theme
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

        bmiParentView = findViewById(R.id.bmiParentView);
        bmiTitleTV = findViewById(R.id.bmiTitleTV);
        bmiCalculator = findViewById(R.id.bmiCalculator);
        txtHeight = findViewById(R.id.txtHeight);
        txtWeight = findViewById(R.id.txtWeight);
        txtMale = findViewById(R.id.txtMale);
        txtFemale = findViewById(R.id.txtFemale);

    }

    // Theme View
    private void updateThemeView() {

        final int black = ContextCompat.getColor(this, R.color.black);
        final int bgblack = ContextCompat.getColor(this, R.color.light_black);
        final int bgwhite = ContextCompat.getColor(this, R.color.light_white);
        final int white = ContextCompat.getColor(this, R.color.white);

        if(settings.getCustomTheme().equals(ThemeSettings.DARK_THEME)){

            bmiTitleTV.setTextColor(white);
            bmiCalculator.setTextColor(white);
            txtHeight.setTextColor(white);
            height.setTextColor(white);
            height.setHintTextColor(white);
            txtWeight.setTextColor(white);
            weight.setTextColor(white);
            weight.setHintTextColor(white);
            txtMale.setTextColor(white);
            male.setBackgroundColor(white);
            female.setBackgroundColor(white);
            txtFemale.setTextColor(white);
            result.setBackgroundColor(white);
            result.setTextColor(black);
            condition.setBackgroundColor(white);
            condition.setTextColor(black);
            bmiParentView.setBackgroundColor(bgblack);

        }else{

            bmiCalculator.setTextColor(black);
            bmiCalculator.setTextColor(black);
            txtHeight.setTextColor(black);
            height.setTextColor(black);
            height.setHintTextColor(black);
            txtWeight.setTextColor(black);
            weight.setTextColor(black);
            txtMale.setTextColor(black);
            male.setBackgroundColor(bgwhite);
            female.setBackgroundColor(bgwhite);
            txtFemale.setTextColor(black);
            result.setBackgroundColor(black);
            result.setTextColor(white);
            condition.setBackgroundColor(black);
            condition.setTextColor(white);
            bmiParentView.setBackgroundColor(bgwhite);
        }
    }

    // Language View
    private void updateLangView() {
        if(settings.getCustomLang().equals(ThemeSettings.ENG_LANG)){

            bmiTitleTV.setText("Body Mass Index");
            bmiCalculator.setText("BMI Calculator");
            txtHeight.setText("Height");
            txtWeight.setText("Weight");
            txtMale.setText("Male");
            txtFemale.setText("Female");
            btncal.setText("Calculate");
            settings.setCustomLang(ThemeSettings.ENG_LANG);

        }else if (settings.getCustomLang().equals(ThemeSettings.TAG_LANG)){

            bmiTitleTV.setText("Indeks ng Masa ng Katawan");
            bmiCalculator.setText("Kalkulator ng BMI");
            txtHeight.setText("Tangkad");
            txtWeight.setText("Lapad");
            txtMale.setText("Lalaki");
            txtFemale.setText("Babae");
            btncal.setText("Kalkulahin");
            settings.setCustomLang(ThemeSettings.TAG_LANG);

        }
    }
    // Text Size View
    private void updateSizeView() {
        if(settings.getCustomSize().equals(ThemeSettings.SMALL_SIZE)){

            bmiTitleTV.setTextSize(16);
            bmiCalculator.setTextSize(23);
            txtHeight.setTextSize(13);
            txtWeight.setTextSize(13);
            height.setTextSize(13);
            weight.setTextSize(13);
            txtMale.setTextSize(13);
            txtFemale.setTextSize(13);
            btncal.setTextSize(18);
            settings.setCustomSize(ThemeSettings.SMALL_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.MEDIUM_SIZE)){

            bmiTitleTV.setTextSize(18);
            bmiCalculator.setTextSize(25);
            txtHeight.setTextSize(15);
            txtWeight.setTextSize(15);
            height.setTextSize(15);
            weight.setTextSize(15);
            txtMale.setTextSize(15);
            txtFemale.setTextSize(15);
            btncal.setTextSize(20);
            settings.setCustomSize(ThemeSettings.MEDIUM_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.LARGE_SIZE)){

            bmiTitleTV.setTextSize(20);
            bmiCalculator.setTextSize(27);
            txtHeight.setTextSize(17);
            txtWeight.setTextSize(17);
            height.setTextSize(17);
            weight.setTextSize(17);
            txtMale.setTextSize(17);
            txtFemale.setTextSize(17);
            btncal.setTextSize(22);
            settings.setCustomSize(ThemeSettings.LARGE_SIZE);

        }
    }

    //BMI Calculation
    private void calculate(){
        h=Float.parseFloat(height.getText().toString());
        w=Float.parseFloat(weight.getText().toString());
            float hm;
            hm = h/100;
            bmi = (w/(hm*hm));
            result.setText(Float.toString(bmi));
            if (bmi>=40){
                res = "Obese class III";
                condition.setText(res);
            }
            else if (bmi>=35){
                res = "Obese class II";
                condition.setText(res);
            }
            else if (bmi>=30){
                res = "Obese class I";
                condition.setText(res);
            }
            else if (bmi>=25){
                res = "Overweight";
                condition.setText(res);
            }
            else if (bmi>=18.5){
                res = "Normal weight";
                condition.setText(res);
            }
            else{
                res="Underweight";
                condition.setText(res);
            }

    }
    // Back Button Intent
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}