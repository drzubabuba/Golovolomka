package com.example.golovolomka2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int COUNT_OF_QUESTIONS = 100;

    // SharedPreferences
    private static final String PREFS_FILE = "GamePref";
    private static final String PREF_COUNT = "Count";
    SharedPreferences settings;

    public static final String LEVEL_KEY = "LEVEL";

    public int countOfAnsweredQuestions;
    Button chooseLevelBtn;
    Button restartGameBtn;
    Button playBtn;

    public static ArrayList<Question> listOfQuestions = new ArrayList<>(COUNT_OF_QUESTIONS);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settings = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);
        initButtons();
        initQuestions();
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
                restartGame();
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

    public void restartGame(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Вы дейтвительно хотите начать игру заново?");

        builder.setCancelable(false)
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Сохраняем результат в SharedPreferences
                        SharedPreferences.Editor prefEditor = settings.edit();
                        prefEditor.putInt(PREF_COUNT, 0);
                        prefEditor.apply();
                        countOfAnsweredQuestions = 0;
                    }
                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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

    //Добавление вопросов в список
    public void initQuestions(){

        //1-10
        listOfQuestions.add(new Question("Устройство для управления курсором", "Грызун", "мышь"));
        listOfQuestions.add(new Question("День недели", "? обитания", "среда"));
        listOfQuestions.add(new Question("Почтовая ?", "? автомобиля", "марка"));
        listOfQuestions.add(new Question("Оружие", "Овощ", "лук"));
        listOfQuestions.add(new Question("Дверной ?", "Крепость", "замок"));
        listOfQuestions.add(new Question("Родник", "? от дома", "ключ"));
        listOfQuestions.add(new Question("Одежда", "? милиции", "наряд"));
        listOfQuestions.add(new Question("1 балл", "Заостренная палка", "кол"));
        listOfQuestions.add(new Question("Торговая ?", "Скамейка", "лавка"));
        listOfQuestions.add(new Question("Объединение", "и, а, или", "союз"));

        //11-20
        listOfQuestions.add(new Question("Луна", "Единица измерения времени", "месяц"));
        listOfQuestions.add(new Question("? для глаз", "Баллы", "очки"));
        listOfQuestions.add(new Question("Ед. измерения скорости судов", "На веревке", "узел"));
        listOfQuestions.add(new Question("Плетенный пучок волос", "Сельхоз инструмент", "коса"));
        listOfQuestions.add(new Question("Стадия развития бабочки", "Танковая ?", "гусеница"));
        listOfQuestions.add(new Question("? аккумулятора", "? по утрам", "зарядка"));
        listOfQuestions.add(new Question("Следование порядку, закону или установленным правилам.", "Спортивная или научная ?", "дисциплина"));
        listOfQuestions.add(new Question("Уголовная ?", "? в газете", "статья"));
        listOfQuestions.add(new Question("? пироги", "Микроволновая ?", "печь"));
        listOfQuestions.add(new Question("Спортивный ?", "? упражнений", "комплекс"));

        //21-30
        listOfQuestions.add(new Question("Морской ?", "USB-?", "порт"));
        listOfQuestions.add(new Question("8/13", "Мелкие свинцовые шарики для стрельбы из охотничьего ружья", "дробь"));
        listOfQuestions.add(new Question("Командный ?", "Сверхъестественное существо", "дух"));
        listOfQuestions.add(new Question("Отсутствие войны", "Земля", "мир"));
        listOfQuestions.add(new Question("Мелодия", "? преступления", "мотив"));
        listOfQuestions.add(new Question("Результат, вывод", "Расследование обстоятельств, связанных с преступлением", "следствие"));
        listOfQuestions.add(new Question("? функции", "? работы (расписание)", "график"));
        listOfQuestions.add(new Question("Солнце является его источником", "Появиться на ?", "свет"));
        listOfQuestions.add(new Question("Любовные отношения", "Мужское имя", "роман"));
        listOfQuestions.add(new Question("Игральная ?", "Твёрдый орган живого организма", "кость"));

        //31-40
        listOfQuestions.add(new Question("Химический элемент", "Хвойный лес", "бор"));
        listOfQuestions.add(new Question("Ровная ледяная поверхность", "Дорожно-строительная машина", "каток"));
        listOfQuestions.add(new Question("Хищное млекопитающее семейства куньих", "Проявление нежности", "ласка"));
        listOfQuestions.add(new Question("Верховный бог в германо-скандинавской мифологии", "? в поле не воин", "один"));
        listOfQuestions.add(new Question("Уличная неутеплённая конструкция в виде крыши", "Пас по воздуху (в футболе)", "навес"));
        listOfQuestions.add(new Question("? передач", "Компьютерная ?", "программа"));
        listOfQuestions.add(new Question("Непроверенные или недостоверные сведения", "Водоплавающая птица", "утка"));
        listOfQuestions.add(new Question("Сообщение на интернет-форуме", "Ответственная должность", "пост"));
        listOfQuestions.add(new Question("Ненормативная лексика", "Плетёная подстилка из грубого материала", "мат"));
        listOfQuestions.add(new Question("Часть живого организма", "Крупный музыкальный инструмент", "орган"));


        //41-50
        listOfQuestions.add(new Question("Орган воздушного питания и газообмена растений", "? бумаги", "лист"));
        listOfQuestions.add(new Question("Действие или их совокупность для достижения какой-либо цели", "Хирургическая ?", "операция"));
        listOfQuestions.add(new Question("Образец", "Метематическая задача", "пример"));
        listOfQuestions.add(new Question("Вредоносная компьютерная программа", "Микроорганизм", "вирус"));
        listOfQuestions.add(new Question("Длинная узкая черта", "? движения", "полоса"));
        listOfQuestions.add(new Question("Дорожный ?", "Водяной ?", "знак"));
        listOfQuestions.add(new Question("Электро-магнитное ?", "Безлесная равнина, пространство", "поле"));
        listOfQuestions.add(new Question("Сосуд для кипячения воды", "Новичок, неопытный в чём-н. человек", "чайник"));
        listOfQuestions.add(new Question("Письменная принадлежность", "? от холодильника, окна и др.", "ручка"));
        listOfQuestions.add(new Question("Точно установленная расценка оплаты труда или определённого вида услуг", "порода небольших собак с короткими ногами и длинным туловищем", "такса"));

        //51-60
        listOfQuestions.add(new Question("Боевой порядок римских войск", "Представитель пресмыкающихся", "черепаха"));
        listOfQuestions.add(new Question("Взятие, получение", "Болевой ?", "прием"));
        listOfQuestions.add(new Question("Водопроводный ?", "Машина, для подъёма и перемещения груза", "кран"));
        listOfQuestions.add(new Question("Столовый прибор", "Двойной удар в шахматах", "вилка"));
        listOfQuestions.add(new Question("Способность издавать звуки", "? избирателя", "голос"));
        listOfQuestions.add(new Question("Верхняя часть строения", "Покровительствующее лицо, организация или преступная группировка", "крыша"));
        listOfQuestions.add(new Question("Зачаток побега растения", "Орган выделительной системы", "почка"));
        listOfQuestions.add(new Question("Миг, мгновение", "Клей «?» ", "момент"));
        listOfQuestions.add(new Question("Главный стебель деревьев", "Труба для разгона пули", "ствол"));
        listOfQuestions.add(new Question("Настил в доме, помещении", "м или ж", "пол"));

        //61-70
        listOfQuestions.add(new Question("Загородный дом", "? показаний", "дача"));
        listOfQuestions.add(new Question("Прикусить ?", "Английский ?", "язык"));
        listOfQuestions.add(new Question("Спрос и ?", "Единица человеческой речи", "предложение"));
        listOfQuestions.add(new Question("Отдача (имущества) в обеспечение обязательства, под ссуду.", "? успеха", "залог"));
        listOfQuestions.add(new Question("То, что взято взаймы", "Воинский ?", "долг"));
        listOfQuestions.add(new Question("? по Европе", "Первобытный дикий бык", "тур"));
        listOfQuestions.add(new Question("Спортсмен", "Порода собак", "боксер"));
        listOfQuestions.add(new Question("Семейный союз", "Некачественный товар", "брак"));
        listOfQuestions.add(new Question("Контурная ?", "Козырная", "карта"));
        listOfQuestions.add(new Question("Насекомое", "Скрытое подслушивающее устройство", "жучок"));

        //71-80
        listOfQuestions.add(new Question("Тонкое листовое железо", "Выражение крайнего удивления", "жесть"));
        listOfQuestions.add(new Question("Сорт конфет в виде кубиков", "Травянистое растение с крупными цветками фиолетовой окраски", "ирис"));
        listOfQuestions.add(new Question("Мышца ноги человека", "Яйца рыб", "икра"));
        listOfQuestions.add(new Question("Штурмовой якорь", "Домашнее животное", "кошка"));
        listOfQuestions.add(new Question("Зажиточный крестьянин-собственник", "Кисть руки с плотно прижатыми к ней пальцами", "кулак"));
        listOfQuestions.add(new Question("Оборонительное сооружение", "? алкогольного напитка", "крепость"));
        listOfQuestions.add(new Question("Искусственное русло для воды", "Телевизионный ?", "канал"));
        listOfQuestions.add(new Question("Сустав ноги", "Звено в родословной, поколение", "колено"));
        listOfQuestions.add(new Question("Ювелирное украшение, носимое на шее", "Единица количества электричества", "кулон"));
        listOfQuestions.add(new Question("Брус дверной или оконной рамы", "Оплошность, проступок", "косяк"));

        //81-90
        listOfQuestions.add(new Question("Два экземпляра чего-либо", "Учебное занятие", "пара"));
        listOfQuestions.add(new Question("Политическая организация", "определенное количество какой-либо продукции", "партия"));
        listOfQuestions.add(new Question("Боеприпас", "в Древнем Риме — богатый человек, покровительствующий малоимущим", "патрон"));
        listOfQuestions.add(new Question("Судьба, обычно злая, несчастливая", "Музыкальное направление", "рок"));
        listOfQuestions.add(new Question("Ровная наклонная поверхность", "Хищная рыба, отличающаяся плоским телом", "скат"));
        listOfQuestions.add(new Question("Тайна", "Вещество, вырабатываемое железами организма", "секрет"));
        listOfQuestions.add(new Question("Поворотный указатель в виде тонкого стержня", "Встреча для выяснения отношений", "стрелка"));
        listOfQuestions.add(new Question("Короткая застольная речь", "поджаренный ломтик хлеба", "тост"));
        listOfQuestions.add(new Question("Волокно растительного происхождения", "Негромкий звук удара", "хлопок"));
        listOfQuestions.add(new Question("Шпаргалка", "Приспособление всадника, прикрепляемое к заднику сапог", "шпора"));

        //91-100
        listOfQuestions.add(new Question("Тон звука и его графическое обозначение", "? протеста", "нота"));
        listOfQuestions.add(new Question("Процесс принудительного удаления жидкости", "Маленькая баранка", "сушка"));
        listOfQuestions.add(new Question("Ягодовидный плод", "Драгоценный камень характерного красного оттенка", "гранат"));
        listOfQuestions.add(new Question("Вид застёжки", "Гигантский электрический искровой разряд", "молния"));
        listOfQuestions.add(new Question("Расческа", "Небольшой вырост на голове у птиц", "гребешок"));
        listOfQuestions.add(new Question("Часть руки", "Инструмент художника", "кисть"));
        listOfQuestions.add(new Question("Небольшой зверек", "Углубление в земле", "норка"));
        listOfQuestions.add(new Question("Небольшая муха", "Деталь прицела", "мушка"));
        listOfQuestions.add(new Question("Ругань", "Устаревшее значение битвы", "брань"));
        listOfQuestions.add(new Question("Перетертые злаки", "Переживание", "мука"));

    }

}
