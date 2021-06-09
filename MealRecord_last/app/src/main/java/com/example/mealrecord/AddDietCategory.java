package com.example.mealrecord;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.net.Authenticator;
import java.util.ArrayList;

public class AddDietCategory extends AppCompatActivity {
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_diet);

        Button breakfastButton = findViewById(R.id.breakfastButton);
        Button lunchButton = findViewById(R.id.lunchButton);
        Button dinnerButton = findViewById(R.id.dinnerButton);
        Button drinkButton = findViewById(R.id.drinkButton);
        Button snackButton = findViewById(R.id.snackButton);
        Button vitaminButton = findViewById(R.id.vitaminButton);
        Button exerciseButton = findViewById(R.id.exerciseButton);

        title = findViewById(R.id.mealRecordText);
        // 상단 바에 MealRecord 클릭 시 메인 화면으로 이동
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        // 아침 버튼 클릭 시 식단 추가 액티비티로 이동
        breakfastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddMeal.class);
                startActivity(intent);
            }
        }); // end breakfastButton. setOnClickListener

        // 점심 버튼 클릭 시 식단 추가 액티비티로 이동
        lunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddMeal.class);
                startActivity(intent);
            }
        }); // end lunchButton.setOnClickListener

        // 저녁 버튼 클릭 시 식단 추가 액티비티로 이동
        dinnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddMeal.class);
                startActivity(intent);
            }
        }); // end dinnerButton.setOnClickListener

        // 간식 버튼 클릭 시 식단 추가 액티비티로 이동
        snackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddMeal.class);
                startActivity(intent);
            }
        }); // end snackButton.setONClickListener

        // 음료 버튼 클릭 시 음료 추가 액티비티로 이동
        drinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddDrink.class);
                startActivity(intent);
            }
        });

        // 영양제 버튼 클릭 시 영양제 추가 액티비티로 이동
        vitaminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddVitamin.class);
                startActivity(intent);
            }
        }); // end vitaminButton.setOnClickListener

        // 운동 버튼 클릭 시 운동 추가 액티비티로 이동
        exerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddExercise.class);
                startActivity(intent);
            }
        });
    }
}
