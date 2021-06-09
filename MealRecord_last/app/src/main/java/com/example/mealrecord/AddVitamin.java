package com.example.mealrecord;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AddVitamin extends AppCompatActivity {
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_vitamin_activity);

        Button addVitamin = findViewById(R.id.newVitamin);
        Button saveVitamin = findViewById(R.id.saveVitamin);

        title = findViewById(R.id.mealRecordText);
        // 상단바에 MealRecord 텍스트 뷰 클릭 시 메인 화면으로 이동
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }); // end title.setOnClickListener

        // 영양제 추가 버튼을 눌렀을 때
        addVitamin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 영양제 추가 버튼을 누르면 새로운 영양제 등록 화면으로 이동
                Intent intent = new Intent(getApplicationContext(), AddNewVitamin.class);
                startActivity(intent);
            }
        });

        // 저장하기 버튼 눌렀을 때
        saveVitamin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 저장하기 버튼을 누르면 데이터 저장 후 메인 하면으로 이동
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.vitaminRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        VitaminAdapter adapter = new VitaminAdapter();

        // 리싸이클러 뷰 더미값 테스트
        adapter.addItem(new Vitamin("영양제1", true));
        adapter.addItem(new Vitamin("영양제2", false));
        adapter.addItem(new Vitamin("영양제3", true));

        recyclerView.setAdapter(adapter);

    }
}
