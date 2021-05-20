package com.koreait.exampleevent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv1;
    GestureDetector gd;
    int backkeycount

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1.findViewById(R.id.textView);

        View view = findViewById(R.id.view);
        view.setOnTouchListener(new View.onTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                float curX = event.getX();
                float curY = event.getY();

                if(action == MotionEvent.ACTION_DOWN) {
                    // 누르는 이벤트가 발생했을 떄
                    tv1.append("손가락 눌림 : " + curX + "," + curY + "\n");
                } else if(action == MotionEvent.ACTION_UP); {
                    // 눌렀다 떼는 이벤트가 발생
                    tv1.append("손가락 뗌 :" + curX + " ," + curY + "\n");
                } else if(action == MotionEvent.ACTION_MOVE) {
                    //누르고 이동하는 이벤트가 발생
                    tv1.append("손가락 이동 :" + curX + "," + curY + "\n" );
                }

                return  true;
            }
        });
        gd = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            private  void println(String data) {
                tv1.append(data + "\n");
            }
            @Override
            public boolean onDown(MotionEvent e) {
                println("onDown() 호출됨");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                println("showPress() 호출됨");

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                println("onSingleTapUp() 호출됨");
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                println("onScroll()호출됨 :" + distanceX + "," + distanceY);
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                println("onLongPress() 호출됨");

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                println("onFling() 호출됨" + velocityX + "," + velocityY);
                return true;
            }
        });

        View v2 = findViewById(R.id.view2);
         v2.setOnTouchListener(new View.onTouchListener(){
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 gd.onTouchEvent(event);
                 return true;
             }
         });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            // 현재
            backkeycount++;
            if(backkeycount == 1 ) {
                Toast.makeText(this, "시스템[BACK] 버튼이 눌렸습니다", Toast.LENGTH_SHORT).show();
            } else {
                finish();
            }

        }
        return true;
    }
}