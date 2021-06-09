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

public class AddExercise extends AppCompatActivity {
    TextView title;
    TextView selectedExercise;
    String[] exercise = {"운동을 선택하세요","유산소", "무산소"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_exercise_activity);

        Button saveExercise = findViewById(R.id.exerciseSave);

        selectedExercise = findViewById(R.id.selectedExercise);

        title = findViewById(R.id.mealRecordText);
        // 상단바에 MealRecord 클릭 시 메인화면으로 이동
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        Spinner spinner = findViewById(R.id.exerciseCategory);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, exercise);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // 스피너에서 운동 선택했을 때 텍스트 뷰에서 출력
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedExercise.setText(exercise[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedExercise.setText("");
            }
        }); // end spinner.setOnItemSelectedListener

        // 저장하기 버튼 눌렀을 때
        saveExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 데이터를 저장하고 메인 화면으로 이동
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
