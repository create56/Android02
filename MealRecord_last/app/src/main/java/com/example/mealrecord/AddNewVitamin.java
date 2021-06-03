package com.example.mealrecord;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddNewVitamin extends AppCompatActivity {
    EditText newVitamin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_vitamin);

        Button addNewVitamin = findViewById(R.id.addNewVitamin);
        newVitamin = findViewById(R.id.newVitaminName);

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
