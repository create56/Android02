package com.example.mealrecord;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
// 음료 추가

public class AddDrink extends AppCompatActivity {
    TextView title;
    TextView selectedDrink;
    String[] drinks = {"음료", "물", "음료수", "커피"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_drink_activity);

        Button saveDrink = findViewById(R.id.drinkSaveButton);

        title = findViewById(R.id.mealRecordText);
        // 상단바에 MealRecord 클릭 시 메인 화면으로 이동
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        // 선택된 음료 종류
        selectedDrink=findViewById(R.id.selectedDrink);

        // 선택할 음료 스피너
        Spinner drinkCategory = findViewById(R.id.drinkCategory);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, drinks);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        drinkCategory.setAdapter(adapter);

        // 스피너에서 음료를 선택했을 때 텍스트뷰에 출력
        drinkCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDrink.setText(drinks[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedDrink.setText("");
            }
        }); // end drinkCategory.setOnItemSelectedListener

        // 저장하기 버튼 눌렀을 때 데이터 저장 후 메인 화면으로 이동
        saveDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }); // end saveDrink.setOnClickListener
    }
}
