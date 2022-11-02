package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BMIActivity extends AppCompatActivity {
    EditText height, weight;
    ImageView male, female;
    LinearLayout malelayout, femalelayout;
    Button btncal;
    TextView result, condition;
    float h=0,w=0,bmi=0;
    String user="0";
    String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiactivity);

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
                male.setColorFilter(getResources().getColor(R.color.green));
                female.setColorFilter(getResources().getColor(R.color.black));
                user="Male";
            }
        });
        femalelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                female.setColorFilter(getResources().getColor(R.color.green));
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
                else if (TextUtils.isEmpty(str2)){
                    weight.setError("Enter Weight");
                    weight.requestFocus();
                    return;
                }
                else
                {
                    calculate();
                }
            }
        });

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
                                , CalendarActivity.class));
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
    }
    //BMI
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
    //BackPressed
    public void onBackPressed() {
        Toast.makeText(BMIActivity.this, "Press Home Button", Toast.LENGTH_SHORT).show();
    }
}