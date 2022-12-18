package com.example.helloworld.Settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class UserProfileActivity extends AppCompatActivity {

    //For Firebase
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    //For Intent
    ImageView bckBtn;

    //Theme SharedPreferences
    private View userProfileParentView;
    private TextView userProfileTitleTV, username;
    private EditText fullNameText1, ageText, emailAddText;
    private Button updateBtn;
    private String fullName, age, emailAdd;

    private ThemeSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // For Firebase

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView fullNameText = (TextView) findViewById(R.id.username);
        fullNameText1 = findViewById(R.id.fullname);
        ageText = findViewById(R.id.age);
        emailAddText = findViewById(R.id.emailadd);
        updateBtn = findViewById(R.id.updateBtn);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    fullName = userProfile.fullname;
                    age = userProfile.age;
                    emailAdd = userProfile.email;

                    fullNameText.setText(fullName);
                    fullNameText1.setText(fullName);
                    ageText.setText(age);
                    emailAddText.setText(emailAdd);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserProfileActivity.this, "Something error happened!", Toast.LENGTH_LONG).show();
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBtn();
            }
        });

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

        userProfileParentView = findViewById(R.id.userProfileParentView);
        userProfileTitleTV = findViewById(R.id.userProfileTitleTV);
        username = findViewById(R.id.username);
    }
    private void loadSharedPreferences() {

        SharedPreferences sharedPreferences = getSharedPreferences(ThemeSettings.PREFERENCES,MODE_PRIVATE);
        String theme = sharedPreferences.getString(ThemeSettings.CUSTOM_THEME, ThemeSettings.LIGHT_THEME);
        settings.setCustomTheme(theme);

    }
    private void updateThemeView() {

        final int black = ContextCompat.getColor(this, R.color.black);
        final int bgblack = ContextCompat.getColor(this, R.color.light_black);
        final int bgwhite = ContextCompat.getColor(this, R.color.light_white);
        final int white = ContextCompat.getColor(this, R.color.light_white);

        if(settings.getCustomTheme().equals(ThemeSettings.DARK_THEME)){

            userProfileTitleTV.setTextColor(white);
            username.setTextColor(white);
            username.setHintTextColor(bgwhite);
            userProfileParentView.setBackgroundColor(bgblack);

        }else{

            userProfileTitleTV.setTextColor(black);
            username.setTextColor(black);
            username.setHintTextColor(bgblack);
            userProfileParentView.setBackgroundColor(bgwhite);
        }
    }
    // Update Profile Method
    private void updateBtn() {
        if (isNameChanged() || isEmailChanged() || isAgeChanged()){
            Toast.makeText(UserProfileActivity.this, "Profile Updated!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, SettingsActivity.class));
        }else{
            Toast.makeText(UserProfileActivity.this, "Profile is not Updated!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, SettingsActivity.class));
        }
    }

    private boolean isNameChanged() {
        if (!fullName.equals(fullNameText1.getText().toString().trim())){
            reference.child(userID).child("fullname").setValue(fullNameText1.getText().toString().trim());
            return true;
        }else{
            return false;
        }
    }

    private boolean isEmailChanged() {
        final String email = emailAddText.getText().toString().trim();
        if (!emailAdd.equals(emailAddText.getText().toString().trim())){
            user.updateEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()){
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userID);
                        Map<String,Object> map = new HashMap<>();
                        map.put("email",email);
                        databaseReference.updateChildren(map);
                        Toast.makeText(UserProfileActivity.this, "Email is changed!", Toast.LENGTH_LONG).show();
                    }else{
                        return;
                    }
                }
            });
            return true;
        }else{
            return false;
        }
    }
    private boolean isAgeChanged() {
        if (!age.equals(ageText.getText().toString().trim())){
            reference.child(userID).child("age").setValue(ageText.getText().toString().trim());
            return true;
        }else{
            return false;
        }
    }

    // Back Button Intent
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }
}