package com.example.golovolomka2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EndActivity extends AppCompatActivity {

    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        backBtn = findViewById(R.id.backToMainMenu);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMainMenu();

            }
        });
    }

    public void backToMainMenu(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
