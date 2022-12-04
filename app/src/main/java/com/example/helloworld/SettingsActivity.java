package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    //For Firebase
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    // For Intent
    Button profbtn;
    ImageView bckBtn, btn1, btn2, btn3, btn4, btn5, btn6;

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
    }
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
                startActivity(new Intent(this, NotAvailablePage.class));
                break;

            case R.id.settings_btnLang:
                startActivity(new Intent(this, NotAvailablePage.class));
                break;

            case R.id.settings_btnMessage:
                startActivity(new Intent(this, NotAvailablePage.class));
                break;

            case R.id.settings_btnAbout:
                startActivity(new Intent(this, NotAvailablePage.class));
                break;

            case R.id.settings_btnFaqs:
                startActivity(new Intent(this, NotAvailablePage.class));
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