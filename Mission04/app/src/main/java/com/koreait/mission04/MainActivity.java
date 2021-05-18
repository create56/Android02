package com.koreait.mission04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText inputText;
    TextView counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputText);
        counter = findViewById(R.id.counter);


       KeyPressEventHandler kpeh = new KeyPressEventHandler(counter);

        // 버튼 클릭(onClick)과는 다르게
        // 입력했을떄를 잡으려면 이벤트 핸들러를 직접 등록
        // 이벤트 핸들러 -> TextWatcher 인터페이스를 상속받은 객체만 가능
        inputText.addTextChangedListener(kpeh);
    }
    public void onButton1Clicked(View v) {
        //editText에 입력된 문자열을 가져와서
        String text = inputText.getText().toString();

        // Toast를 사용해서 "입력된문자열"을 전송합니다.를 보여주도록
        Toast.makeText(this,text + "을 전송합니다",Toast.LENGTH_LONG).show();
    }

}