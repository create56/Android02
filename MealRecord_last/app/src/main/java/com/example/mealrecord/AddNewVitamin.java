package com.example.mealrecord;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class AddNewVitamin extends AppCompatActivity {
    TextView title;
    EditText newVitamin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_vitamin);

        Button addNewVitamin = findViewById(R.id.addNewVitamin);
        newVitamin = findViewById(R.id.newVitaminName);

        title = findViewById(R.id.mealRecordText);
        // 상단바에 MealRecord 텍스트뷰 클릭 시 메인 화면으로 이동
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        addNewVitamin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 데이터를 저장하고 영양제 등록 화면으로 이동
                Intent intent = new Intent(getApplicationContext(), AddVitamin.class);
                startActivity(intent);
            }
        });

    }
}
