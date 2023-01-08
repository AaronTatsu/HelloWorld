package com.example.helloworld.Tutorial;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.helloworld.R;

public class TutorialAdapter extends PagerAdapter {

    private Context ctx;
    private int[] imageArray = new int[] {R.drawable.tutorialwelcome, R.drawable.tutorialbmi, R.drawable.tutorialtodo, R.drawable.tutorialvideoscreen, R.drawable.tutorialsettings, R.drawable.tutorialsettings2, R.drawable.tutorialexercise, R.drawable.tutorialexe2, R.drawable.tutorialexe3};

    TutorialAdapter(Context context) {ctx = context;}

    @Override
    public int getCount() {
        return imageArray.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(ctx);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(imageArray[position]);
        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
}
