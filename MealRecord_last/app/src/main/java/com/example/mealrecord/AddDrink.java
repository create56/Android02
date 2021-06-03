package com.example.mealrecord;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
// 음료 추가
public class AddDrink extends AppCompatActivity {
    TextView selectedDrink;
    String[] drinks = {"음료", "물", "음료수", "커피"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_drink_activity);

        // 선택된 음료 종류
        selectedDrink=findViewById(R.id.selectedDrink);
        // 선택할 음료 스피너
        Spinner drinkCategory = findViewById(R.id.drinkCategory);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, drinks);
        drinkCategory.setAdapter(adapter);

        // 스피너에서 음료를 선택했을 때 텍스트뷰에 출력
        drinkCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                selectedDrink.setText(drinks[position]);
            }

            public void onNothingSelected(AdapterView<?> adapterView){
                selectedDrink.setText("");
            }
        }); // end drinkCategory.setOnItemClickListener
    }
}
