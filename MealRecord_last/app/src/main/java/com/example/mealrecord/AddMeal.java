package com.example.mealrecord;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddMeal extends AppCompatActivity {
    EditText foodDetails;
    Button addPicButton;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_meal_activity);

        // 음식 설명
        foodDetails = findViewById(R.id.foodDetails);
        // 사진 추가할 버튼
        addPicButton = findViewById(R.id.addPicButton);
        // 저장 버튼
        saveButton = findViewById(R.id.saveButton);

        // 사진 추가 버튼 눌렀을 때
        addPicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }); // end addPicButton.setOnClickListener

        // 저장하기 버튼 눌렀을 때
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 저장 후 메인 화면으로 이동
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }); // end saveButton.setOnClickListener
    }
}
