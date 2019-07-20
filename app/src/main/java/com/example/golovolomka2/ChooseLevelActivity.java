package com.example.golovolomka2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;

public class ChooseLevelActivity extends AppCompatActivity {

    // SharedPreferences
    private static final String PREFS_FILE = "GamePref";
    private static final String PREF_COUNT = "Count";
    SharedPreferences settings;

    public static final String LEVEL_KEY = "LEVEL";

    public int countOfAnsweredQuestions;


    ArrayList<Button> levelButtons = new ArrayList<>(40);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);

        settings = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);

        initButtons();

        getCountOfAnsweredQuestion();
        disableButtons();

    }

    // Инициализация кнопок
    public void initButtons(){
        levelButtons.add((Button) findViewById(R.id.btn_level_1));
        levelButtons.add((Button) findViewById(R.id.btn_level_2));
        levelButtons.add((Button) findViewById(R.id.btn_level_3));
        levelButtons.add((Button) findViewById(R.id.btn_level_4));
        levelButtons.add((Button) findViewById(R.id.btn_level_5));
        levelButtons.add((Button) findViewById(R.id.btn_level_6));
        levelButtons.add((Button) findViewById(R.id.btn_level_7));
        levelButtons.add((Button) findViewById(R.id.btn_level_8));
        levelButtons.add((Button) findViewById(R.id.btn_level_9));
        levelButtons.add((Button) findViewById(R.id.btn_level_10));

        levelButtons.add((Button) findViewById(R.id.btn_level_11));
        levelButtons.add((Button) findViewById(R.id.btn_level_12));
        levelButtons.add((Button) findViewById(R.id.btn_level_13));
        levelButtons.add((Button) findViewById(R.id.btn_level_14));
        levelButtons.add((Button) findViewById(R.id.btn_level_15));
        levelButtons.add((Button) findViewById(R.id.btn_level_16));
        levelButtons.add((Button) findViewById(R.id.btn_level_17));
        levelButtons.add((Button) findViewById(R.id.btn_level_18));
        levelButtons.add((Button) findViewById(R.id.btn_level_19));
        levelButtons.add((Button) findViewById(R.id.btn_level_20));

        levelButtons.add((Button) findViewById(R.id.btn_level_21));
        levelButtons.add((Button) findViewById(R.id.btn_level_22));
        levelButtons.add((Button) findViewById(R.id.btn_level_23));
        levelButtons.add((Button) findViewById(R.id.btn_level_24));
        levelButtons.add((Button) findViewById(R.id.btn_level_25));
        levelButtons.add((Button) findViewById(R.id.btn_level_26));
        levelButtons.add((Button) findViewById(R.id.btn_level_27));
        levelButtons.add((Button) findViewById(R.id.btn_level_28));
        levelButtons.add((Button) findViewById(R.id.btn_level_29));
        levelButtons.add((Button) findViewById(R.id.btn_level_30));

        levelButtons.add((Button) findViewById(R.id.btn_level_31));
        levelButtons.add((Button) findViewById(R.id.btn_level_32));
        levelButtons.add((Button) findViewById(R.id.btn_level_33));
        levelButtons.add((Button) findViewById(R.id.btn_level_34));
        levelButtons.add((Button) findViewById(R.id.btn_level_35));
        levelButtons.add((Button) findViewById(R.id.btn_level_36));
        levelButtons.add((Button) findViewById(R.id.btn_level_37));
        levelButtons.add((Button) findViewById(R.id.btn_level_38));
        levelButtons.add((Button) findViewById(R.id.btn_level_39));
        levelButtons.add((Button) findViewById(R.id.btn_level_40));
    }

    // Обработчик нажатия кнопок
    public void chooseBtnClick(View view){
        Button b = (Button)view;
        Intent intent = new Intent(this, PlayActivity.class);
        intent.putExtra(LEVEL_KEY, b.getText());
        startActivityForResult(intent, 1);
    }

    //Получить информацию о количестве пройденных уровней из SharedPreferences
    public void getCountOfAnsweredQuestion() {
        countOfAnsweredQuestions = settings.getInt(PREF_COUNT,0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getCountOfAnsweredQuestion();
        disableButtons();
    }

    // Запрет доступа к уровням
    public void disableButtons(){
        for(int i = 0; i < countOfAnsweredQuestions+1; i++){
            levelButtons.get(i).setEnabled(true);
            levelButtons.get(i).setBackgroundResource(R.color.colorPrimary);
        }

        for(int i = countOfAnsweredQuestions+1; i < 40; i++){
            levelButtons.get(i).setEnabled(false);
            levelButtons.get(i).setBackgroundResource(R.color.disabledButtonColor);
        }
    }
}
