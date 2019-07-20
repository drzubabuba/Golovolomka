package com.example.golovolomka2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {

    // SharedPreferences
    private static final String PREFS_FILE = "GamePref";
    private static final String PREF_COUNT = "Count";
    SharedPreferences settings;

    private static final int NUMBER_OF_QUESTIONS = 40;

    int levelID;
    public int countOfAnsweredQuestions;

    ArrayList<Question> listOfQuestions = new ArrayList<>(40);


    TextView lvl;
    TextView firstHint;
    TextView secondHint;
    EditText userAnswer;
    RelativeLayout layout;
    Button nextLevelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Intent intent = getIntent();
        levelID = Integer.parseInt(intent.getStringExtra(ChooseLevelActivity.LEVEL_KEY));
        settings = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);

        initQuestions();
        initViews();


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

        setCurrentQuestion();
    }

    public void setCurrentQuestion(){
        userAnswer.setText("");
        firstHint.setText(listOfQuestions.get(levelID-1).getFirstHint());
        secondHint.setText(listOfQuestions.get(levelID-1).getSecondHint());
        lvl.setText("Уровень " + levelID);
        nextLevelBtn.setEnabled(false);
        nextLevelBtn.setVisibility(View.INVISIBLE);
        layout.setBackgroundResource(R.color.colorPrimaryDark);
    }

    //Добавление вопросов в список
    public void initQuestions(){
        listOfQuestions.add(new Question("Дверной ?", "Крепость", "замок"));
        listOfQuestions.add(new Question("Устройство для управления курсором", "Грызун", "мышь"));
        listOfQuestions.add(new Question("Одежда", "? милиции", "наряд"));
        listOfQuestions.add(new Question("Почтовая ?", "? автомобиля", "марка"));

        listOfQuestions.add(new Question("День недели", "? обитания", "среда"));
        listOfQuestions.add(new Question("Оружие", "Овощ", "лук"));
        listOfQuestions.add(new Question("Родник", "? от дома", "ключ"));
        listOfQuestions.add(new Question("0 баллов", "Заостренная палка", "кол"));

        listOfQuestions.add(new Question("Торговая ?", "Скамейка", "лавка"));
        listOfQuestions.add(new Question("Объединение", "и, а, или", "союз"));
        listOfQuestions.add(new Question("Луна", "1\\12  года", "месяц"));
        listOfQuestions.add(new Question("? для глаз", "Баллы", "очки"));

        listOfQuestions.add(new Question("Ед. измерения скорости судов", "На веревке", "узел"));
        listOfQuestions.add(new Question("Плетенный пучок волос", "Сельхоз инструмент", "коса"));
        listOfQuestions.add(new Question("Стадия развития бабочки", "Танковая ?", "гусеница"));
        listOfQuestions.add(new Question("? аккумулятора", "? по утрам", "зарядка"));

        listOfQuestions.add(new Question("Строгий порядок", "Учебный предмет", "дисциплина"));
        listOfQuestions.add(new Question("Уголовная ?", "? в газете", "статья"));
        listOfQuestions.add(new Question("? пироги", "Микроволновая ?", "печь"));
        listOfQuestions.add(new Question("Спортивный ?", "? упражнений", "комплекс"));

        listOfQuestions.add(new Question("Морской ?", "USB-?", "порт"));
        listOfQuestions.add(new Question("8/13", "Мелкие свинцовые шарики для стрельбы из охотничьего ружья", "дробь"));
        listOfQuestions.add(new Question("Командный ?", "Сверхъестественное существо", "дух"));
        listOfQuestions.add(new Question("Отсутствие войны", "Земля", "мир"));

        listOfQuestions.add(new Question("Простейшая ритмическая единица мелодии", "? преступления", "мотив"));
        listOfQuestions.add(new Question("Результат", "Расследование обстоятельств, связанных с преступлением", "следствие"));
        listOfQuestions.add(new Question("? функции", "? работы", "график"));
        listOfQuestions.add(new Question("Солнце является его источником", "«высший ?» — элита ", "свет"));

        listOfQuestions.add(new Question("Служебный ?", "Мужское имя", "роман"));
        listOfQuestions.add(new Question("Игральная карта", "От замка", "ключ"));
        listOfQuestions.add(new Question("Химический элемент", "Хвойный лес", "бор"));
        listOfQuestions.add(new Question("Ровная ледяная поверхность", "Дорожно-строительная машина", "каток"));

        listOfQuestions.add(new Question("Хищное млекопитающее семейства куньих", "Проявление нежности", "ласка"));
        listOfQuestions.add(new Question("Верховный бог в германо-скандинавской мифологии", "? в поле не воин", "один"));
        listOfQuestions.add(new Question("Уличная неутеплённая конструкция в виде крыши", "Пас по воздуху (в футболе)", "навес"));
        listOfQuestions.add(new Question("? передач", "Компьютерная ?", "программа"));

        listOfQuestions.add(new Question("Непроверенные или недостоверные сведения", "Водоплавающая птица", "утка"));
        listOfQuestions.add(new Question("Сообщение на интернет-форуме", "Ответственная должность", "пост"));
        listOfQuestions.add(new Question("Шах и ?", "Плетёная подстилка из грубого материала", "мат"));
        listOfQuestions.add(new Question("Часть живого организма", "Крупный музыкальный инструмент", "орган"));


    }

    // Проверка правильности введенного значения
    public void checkAnswer(CharSequence cs){
        if(cs.toString().toLowerCase().equals(listOfQuestions.get(levelID-1).getAnswer())){
            hideKeyboard(this);
            layout.setBackgroundResource(R.color.colorAccent);

            countOfAnsweredQuestions = settings.getInt(PREF_COUNT,0);

            if(levelID == (countOfAnsweredQuestions + 1)){
                // Сохраняем результат в SharedPreferences
                SharedPreferences.Editor prefEditor = settings.edit();
                prefEditor.putInt(PREF_COUNT, levelID);
                prefEditor.apply();

                if(levelID == NUMBER_OF_QUESTIONS){
                    //Молодец. Чемпион!!!

                    // Оставляем число отвеченных вопросов равным 15
                    prefEditor.putInt(PREF_COUNT, levelID - 1);
                    prefEditor.apply();
                    return;
                }
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
