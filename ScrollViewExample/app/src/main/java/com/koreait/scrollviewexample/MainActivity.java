package com.koreait.scrollviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

   HorizontalScrollView hsv;
    ImageView iv;
    BitmapDrawable bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hsv = findViewById(R.id.h_scrollView);
        iv = findViewById(R.id.imageView);
        hsv.setHorizontalScrollBarEnabled(true);
         //첫 화면에서 보여줄 이미지들

        // 이미지 리소스(파일)을 가져오기 위한 객체 생성
        Resources res = getResources();
        // drawble 폴더에 저장한 이미지 파일을 자바 코드로 불러옴
        bd = (BitmapDrawable)res.getDrawable(R.drawable.image01); //형변환
        // 이미지 파일의 너비
        int bitmapWidth = bd.getIntrinsicWidth();
        // 이미지 파일의 높이
        int bitmapHeight = bd.getIntrinsicHeight();

        iv.setImageDrawable(bd);
        iv.getLayoutParams().width = bitmapWidth;
        iv.getLayoutParams().height = bitmapHeight;
    }

    public void onButton1Clicked(View v) {
        Resources res = getResources();
        // drawble 폴더에 저장한 이미지 파일을 자바 코드로 불러옴
        bd = (BitmapDrawable)res.getDrawable(R.drawable.image02); //형변환
        // 이미지 파일의 너비
        int bitmapWidth = bd.getIntrinsicWidth();
        // 이미지 파일의 높이
        int bitmapHeight = bd.getIntrinsicHeight();

        iv.setImageDrawable(bd);
        iv.getLayoutParams().width = bitmapWidth;
        iv.getLayoutParams().height = bitmapHeight;
    }
}