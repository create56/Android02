package com.koreait.examplespinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView tv;

    String[] items = {"이름을 선택하세요.","mike","angel","crow","john","ginnie","sally","cohen","rice" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textVIew);

        Spinner spinner = findViewById(R.id.spinner);
        // 두번쨰 파라미터는 사용할 XML 레이아웃의 리소스 ID 값
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // 어댑터 설정(스피너도 선택 위젯으로 분류가 되므로 리싸이클러뷰처럼 어댑터를 설정해서 원본 데이터인 아이템을 관리하고 각각의 아이템을 보여주는 역활)
        // 리싸이클러뷰에서는 어댑터를 직접 구현 했는데 스피너에서는 어댑터를 직접 구현되지 않음
        // 리싸이클러뷰를 위한 어댑터는 기본적으로 제공되지 않아서 직접 구현
        // 스피너를 위한 어댑터는 기본적으로 제공되어서 제공된 어댑터를 사용
        spinner.setAdapter(adapter);
        // 옵션(아이템) 중에 하나가 선택됬을 떄 동작할 매서드를 오버라이딩
        //
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv.setText(items[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                tv.setText("");
            }

        });
    }
}