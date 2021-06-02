package com.koreait.examplethreadanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView iv;

    ArrayList<Drawable> drawableList = new ArrayList<Drawable>();
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        drawableList.add(res.getDrawable(R.drawable.face1));
        drawableList.add(res.getDrawable(R.drawable.face2));
        drawableList.add(res.getDrawable(R.drawable.face3));
        drawableList.add(res.getDrawable(R.drawable.face4));
        drawableList.add(res.getDrawable(R.drawable.face5));

        iv = findViewById(R.id.imageView);
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimThread thread = new AnimThread();
                thread.start();
             }
        });
    } // end onCreate

    class AnimThread extends Thread {
        public void run() {
            int index = 0;
            for(int i=0; i<100; i++) {
                final Drawable drawable = drawableList.get(index);
                index += 1;
                if(index > 4) {
                    index = 0;
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                       iv.setImageDrawable(drawable);
                    }
                }); // end handler.post

            }
        }
    }
}