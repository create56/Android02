package com.example.mealrecord;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView title;
    Toolbar toolbar;
    Button addDietButton;
    ImageButton myPageButton;
    ImageButton bodySizeButton;
    ImageButton iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // 식단 추가 버튼
        addDietButton = findViewById(R.id.addDietButton);
        // 마이페이지 버튼
        myPageButton = findViewById(R.id.myPageButton);
        // 신체 기록 추가 버튼
        bodySizeButton = findViewById(R.id.bodySizeButton);

        title = findViewById(R.id.mealRecordText);

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }); // end title.setOnClickListener

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        // 당일 식단 보여줄 뷰
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        DailyRecordAdapter adapter = new DailyRecordAdapter();
        // 예시로 데이터 넣었어요!
        adapter.addItem(new DailyRecord("아침", "시리얼"));
        adapter.addItem(new DailyRecord("점심", "갈비탕"));
        adapter.addItem(new DailyRecord("저녁","찜닭"));

        recyclerView.setAdapter(adapter);

        // 식단 추가 버튼 누르면 식단 추가 카테고리 페이지로 이동
        addDietButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddDietCategory.class);
                startActivity(intent);
            }
        }); // end addDietButton.setOnClickListener

        // 신체 기록  추가 버튼 누르면 신체기록 추가 페이지로 이동
        bodySizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddBodySize.class);
                startActivity(intent);
            }
        }); // end bodySizeButton.setOnClickListener

        iv = findViewById(R.id.movetoCalendar);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
                startActivity(intent);
            }
        });
    }
}