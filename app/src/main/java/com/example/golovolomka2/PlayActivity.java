package com.example.golovolomka2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import static com.example.golovolomka2.MainActivity.listOfQuestions;

public class PlayActivity extends AppCompatActivity {

    private InterstitialAd mInterstital;

    // SharedPreferences
    private static final String PREFS_FILE = "GamePref";
    private static final String PREF_COUNT = "Count";
    SharedPreferences settings;

    private static final int NUMBER_OF_QUESTIONS = 100;

    int levelID;
    public int countOfAnsweredQuestions;


    public TextView lvl;
    public TextView firstHint;
    public TextView secondHint;
    public EditText userAnswer;
    public RelativeLayout layout;
    public Button nextLevelBtn;
    public ImageButton hintBtn;
    public ImageButton backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        MobileAds.initialize(this,"ca-app-pub-5845066134579557~7077637026");

        Intent intent = getIntent();
        levelID = Integer.parseInt(intent.getStringExtra(ChooseLevelActivity.LEVEL_KEY));
        settings = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);

        initViews();

        mInterstital = new InterstitialAd(this);
//        ca-app-pub-3940256099942544/1033173712 - тестовый
//        ca-app-pub-5845066134579557/2220950060 - нащ
        mInterstital.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        loadAd();

        mInterstital.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                //Toast.makeText(getApplicationContext(),"Add loaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                //Toast.makeText(getApplicationContext(),"Some trouble with load"+i,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClosed() {
                userAnswer.setText("");
                userAnswer.setHint(listOfQuestions.get(levelID-1).getAnswer());
                loadAd();
            }
        });





    }

    private  void loadAd(){
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        mInterstital.loadAd(adRequest);
    }

    // Инициализация элементов интерфейса
    public void initViews(){
        lvl = findViewById(R.id.txtLevel);
        firstHint = findViewById(R.id.txtFirstHint);
        secondHint = findViewById(R.id.txtSecondHint);
        userAnswer = findViewById(R.id.userAnswer);
        layout = findViewById(R.id.playLayout);
        nextLevelBtn = findViewById(R.id.nextLevelBtn);
        lvl = findViewById(R.id.txtLevel);
        hintBtn = findViewById(R.id.hintButton);
        backBtn = findViewById(R.id.backChooseLevel);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        userAnswer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkAnswer(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        nextLevelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                levelID++;
                setCurrentQuestion();
            }
        });
        hintBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PlayActivity.this);
                builder.setTitle("Окно подсказки")
                        .setMessage("Посмотреть рекламу чтобы получить подсказку?")
                        .setCancelable(false)
                        .setNegativeButton("Нет,сам справлюсь)",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                        .setPositiveButton("Да,трудно(", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Toast.makeText(getApplicationContext(),"Ведутся работы! Далее тут будет реклама",Toast.LENGTH_SHORT).show();
                                if(mInterstital.isLoaded()){
                                    mInterstital.show();
                                    loadAd();
                                }else{
                                    Toast.makeText(getApplicationContext(),"Упс. Не получилось загрузить рекламу(",Toast.LENGTH_SHORT).show();
                                    loadAd();
                                }
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        setCurrentQuestion();
    }

    public void setCurrentQuestion(){
        userAnswer.setText("");
        userAnswer.setHint("Общее слово");
        userAnswer.setInputType(InputType.TYPE_CLASS_TEXT);
        firstHint.setText(listOfQuestions.get(levelID-1).getFirstHint());
        secondHint.setText(listOfQuestions.get(levelID-1).getSecondHint());
        lvl.setText("Уровень " + levelID);
        nextLevelBtn.setEnabled(false);
        nextLevelBtn.setVisibility(View.INVISIBLE);
        layout.setBackgroundResource(R.color.colorPrimaryDark);
        backBtn.setBackgroundResource(R.color.colorPrimaryDark);
        hintBtn.setVisibility(View.VISIBLE);

    }



    // Проверка правильности введенного значения
    public void checkAnswer(CharSequence cs){
        if(cs.toString().toLowerCase().equals(listOfQuestions.get(levelID-1).getAnswer())){
            hideKeyboard(this);
            layout.setBackgroundResource(R.color.colorAccent);
            backBtn.setBackgroundResource(R.color.colorAccent);
            hintBtn.setVisibility(View.INVISIBLE);

            countOfAnsweredQuestions = settings.getInt(PREF_COUNT,0);

            if(levelID == (countOfAnsweredQuestions + 1)){
                // Сохраняем результат в SharedPreferences
                SharedPreferences.Editor prefEditor = settings.edit();
                prefEditor.putInt(PREF_COUNT, levelID);
                prefEditor.apply();

                if(levelID == NUMBER_OF_QUESTIONS){
                    //Молодец. Чемпион!!!

                    // Оставляем число отвеченных вопросов равным 100
                    prefEditor.putInt(PREF_COUNT, levelID - 1);
                    prefEditor.apply();

                    Intent intent = new Intent(this, EndActivity.class);
                    startActivity(intent);
                    return;
                }
                userAnswer.setInputType(InputType.TYPE_NULL);
                nextLevelBtn.setEnabled(true);
                nextLevelBtn.setVisibility(View.VISIBLE);
            }


        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}
