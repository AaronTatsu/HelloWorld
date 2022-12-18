package com.example.helloworld.Exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.helloworld.Exercise.ChestExercise_3;
import com.example.helloworld.R;

public class PushUp_3_Squeeze extends AppCompatActivity {

    ImageView bckBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_up3_squeeze);

        bckBtn = findViewById(R.id.back_pressed);
        bckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChestExercise_3.class);
                startActivity(intent);
            }
        });
    }
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), ChestExercise_3.class);
        startActivity(intent);
    }
}