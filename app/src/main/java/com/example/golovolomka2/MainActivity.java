package com.example.golovolomka2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // SharedPreferences
    private static final String PREFS_FILE = "GamePref";
    private static final String PREF_COUNT = "Count";
    SharedPreferences settings;

    public static final String LEVEL_KEY = "LEVEL";

    public int countOfAnsweredQuestions;
    Button chooseLevelBtn;
    Button restartGameBtn;
    Button playBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settings = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);
        initButtons();
        getCountOfAnsweredQuestion();
    }

    //Инициализация кнопок
    void initButtons(){
        chooseLevelBtn = findViewById(R.id.menu_ChooseLevelBtn);
        chooseLevelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChooseLevelActivity();
            }
        });
        restartGameBtn = findViewById(R.id.menu_RestartBtn);
        restartGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Сохраняем результат в SharedPreferences
                SharedPreferences.Editor prefEditor = settings.edit();
                prefEditor.putInt(PREF_COUNT, 0);
                prefEditor.apply();
                countOfAnsweredQuestions = 0;
            }
        });
        playBtn = findViewById(R.id.menu_PlayBtn);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPlayActivity();
            }
        });
    }

    public void openPlayActivity(){
        Intent intent = new Intent(this, PlayActivity.class);
        intent.putExtra(LEVEL_KEY, String.valueOf(countOfAnsweredQuestions+1));
        startActivityForResult(intent, 1);
    }


    //Переход на активити выбора уровней
    public void openChooseLevelActivity(){
        Intent intent = new Intent(this, ChooseLevelActivity.class);
        //intent.putExtra("COUNT", countOfAnsweredQuestions);
        startActivity(intent);
    }


    //Получить информацию о количестве пройденных уровней из SharedPreferences
    public void getCountOfAnsweredQuestion() {
        countOfAnsweredQuestions = settings.getInt(PREF_COUNT,0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getCountOfAnsweredQuestion();
    }

}
