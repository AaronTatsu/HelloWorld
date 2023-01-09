package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helloworld.Settings.ThemeSettings;
import com.example.helloworld.ToDoList.AddNewTask;
import com.example.helloworld.ToDoList.DialogCloseListener;
import com.example.helloworld.ToDoList.RecyclerTouchHelper;
import com.example.helloworld.ToDoList.ToDoAdapter;
import com.example.helloworld.ToDoList.ToDoDBHandler;
import com.example.helloworld.ToDoList.ToDoModel;
import com.example.helloworld.Tutorial.TutorialToDo;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ToDoListActivity extends AppCompatActivity implements DialogCloseListener {

    // Activity Variables
    private RecyclerView taskRecyclerView;
    private ToDoAdapter taskAdapter;
    private FloatingActionButton fab;

    private List<ToDoModel> taskList;
    private ToDoDBHandler db;

    //Theme Settings
    private View notesParentView;
    private TextView notesTitleTV, taskTextTV;

    private ThemeSettings settings;

    // Back Button Intent
    ImageView bckBtn, tutorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        //Back Button & Tutorial Intent
        bckBtn = findViewById(R.id.back_pressed);
        bckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        tutorial = findViewById(R.id.tutorial);
        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TutorialToDo.class);
                startActivity(intent);
            }
        });

        // Main Activity
        db = new ToDoDBHandler(this);
        db.openDatabase();

        taskList = new ArrayList<>();

        taskRecyclerView = findViewById(R.id.taskRecyclerView);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new ToDoAdapter(db, this);
        taskRecyclerView.setAdapter(taskAdapter);

        fab = findViewById(R.id.fab);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerTouchHelper(taskAdapter));
        itemTouchHelper.attachToRecyclerView(taskRecyclerView);

        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        taskAdapter.setTask(taskList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);
            }
        });

        //Bottom Navigation Intent
        BottomNavigationView bottomNavigationView = findViewById(R.id.BottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.nav_cal);
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

    private void initWidgets() {

        notesParentView = findViewById(R.id.notesParentView);
        notesTitleTV = findViewById(R.id.notesTitleTV);
        taskTextTV = findViewById(R.id.taskTextTV);

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

    private void updateThemeView() {

        final int black = ContextCompat.getColor(this, R.color.black);
        final int bgblack = ContextCompat.getColor(this, R.color.light_black);
        final int bgwhite = ContextCompat.getColor(this, R.color.light_white);
        final int white = ContextCompat.getColor(this, R.color.white);

        if(settings.getCustomTheme().equals(ThemeSettings.DARK_THEME)){

            notesTitleTV.setTextColor(white);
            taskTextTV.setTextColor(white);
            notesParentView.setBackgroundColor(bgblack);

        }else{

            notesTitleTV.setTextColor(black);
            taskTextTV.setTextColor(black);
            notesParentView.setBackgroundColor(bgwhite);
        }
    }

    // Language View
    private void updateLangView() {
        if(settings.getCustomLang().equals(ThemeSettings.ENG_LANG)){

            notesTitleTV.setText("Check Notes");
            taskTextTV.setText("Tasks");
            settings.setCustomLang(ThemeSettings.ENG_LANG);

        }else if (settings.getCustomLang().equals(ThemeSettings.TAG_LANG)){

            notesTitleTV.setText("Suriin ang mga Tala");
            taskTextTV.setText("Mga Gawain");
            settings.setCustomLang(ThemeSettings.TAG_LANG);

        }
    }

    // Text Size View
    private void updateSizeView() {
        if(settings.getCustomSize().equals(ThemeSettings.SMALL_SIZE)){

            notesTitleTV.setTextSize(16);
            taskTextTV.setTextSize(30);
            settings.setCustomSize(ThemeSettings.SMALL_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.MEDIUM_SIZE)){

            notesTitleTV.setTextSize(18);
            taskTextTV.setTextSize(32);
            settings.setCustomSize(ThemeSettings.MEDIUM_SIZE);

        }else if (settings.getCustomSize().equals(ThemeSettings.LARGE_SIZE)){

            notesTitleTV.setTextSize(20);
            taskTextTV.setTextSize(34);
            settings.setCustomSize(ThemeSettings.LARGE_SIZE);

        }
    }

    // Main Activity
    @Override
    public void handleDialogClose(DialogInterface dialog){
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        taskAdapter.setTask(taskList);
        taskAdapter.notifyDataSetChanged();
    }

    // Back Button Intent
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}