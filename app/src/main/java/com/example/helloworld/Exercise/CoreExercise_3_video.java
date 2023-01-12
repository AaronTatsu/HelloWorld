package com.example.helloworld.Exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.helloworld.R;

public class CoreExercise_3_video extends AppCompatActivity {

    ImageView bckBtn;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arms_exercise1_video);

        bckBtn = findViewById(R.id.back_pressed);
        bckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoreExercise_3_video.super.onBackPressed();
            }
        });

        videoView = findViewById(R.id.exercise_video);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.armsposition;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.start();

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
    }
    public void onBackPressed(){
        CoreExercise_3_video.super.onBackPressed();
    }
}