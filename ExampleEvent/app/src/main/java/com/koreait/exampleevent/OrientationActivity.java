package com.koreait.exampleevent;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OrientationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "onCreate 호출됨", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        Toast.makeText(this, "onStart 호출됨", Toast.LENGTH_LONG).show();
        super.onStart();
    }

    @Override
    protected void onStop() {
        Toast.makeText(this, "onStop 호출됨", Toast.LENGTH_LONG).show();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this, "onDestroy 호출됨", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }
}