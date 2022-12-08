package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class SettingsFrequentAsked extends AppCompatActivity {

    ImageView bckBtn;

    ArrayList<FAQsClass> arrayList;
    RecyclerView recyclerView;
    FAQsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_frequent_asked);

        recyclerView = findViewById(R.id.faqsRecyclerView);
        arrayList = new ArrayList<>();

        arrayList.add(new FAQsClass("What is TikTonic?", "\tTikTonic is a user friendly fitness and exercise application that helps the user to have an entertaining time while doing their workouts.", false));
        arrayList.add(new FAQsClass("What is Augmented Reality?", "\tAugmented Reality is overlaying digital visual elements, sound or other sensory information unto the real life settings..", false));
        arrayList.add(new FAQsClass("Why do we need to exercise?", "\tExercise is beneficial to not just your body, but also the mental health. It improves your body, thinking ability and time management. It also prevents you from having diseases due to a physically active body.", false));
        arrayList.add(new FAQsClass("How do I know if Im doing the right thing?", "\tThe application helps you with many different exercises and still making more informations that would help the application to flourish more and be reliable.", false));
        arrayList.add(new FAQsClass("Is TikTonic available in App Store?", "\t TikTonic Android Application is still not available in the app store due to it still not competent enough and the programmers still develops new features to entertain the users.", false));
        arrayList.add(new FAQsClass("Why the application is so buggy?", "\t Tiktonic Android Application is still in it's developmental stage and may contains numerous bugs. Please let us know through the contact us page, if ever you had an encounter with this bugs.", false));

        adapter = new FAQsAdapter(arrayList, SettingsFrequentAsked.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Back Button Intent
        bckBtn = findViewById(R.id.back_pressed);
        bckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }
}