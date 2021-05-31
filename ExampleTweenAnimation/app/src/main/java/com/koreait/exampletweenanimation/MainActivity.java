package com.koreait.exampletweenanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 정방향만 구현
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale);

                v.startAnimation(anim);
            }
        });
    }

    public void onButton1Clicked(View v) {
        // 정방향/ 역방향 구현
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale2);

        v.startAnimation(anim);
    }
}