package com.example.mealrecord;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class AddBodySize extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_body_size);

        Button bodySizeSave = findViewById(R.id.bodySizeSave);
        bodySizeSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 데이터 저장 후 메인 페이지로 이동
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}