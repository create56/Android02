package com.koreait.exampleasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    BackgroundTask task;
    int value;

    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb = findViewById(R.id.progressBar);

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task = new BackgroundTask();
                task.execute();
            }
        });

        Button btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.cancel(true);
            }
        });
    }

    class BackgroundTask extends AsyncTask<Integer, Integer,Integer> {

        @Override
        protected void onPreExecute() {
            value = 0;
            pb.setProgress(value);
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            while (isCancelled() == false) {
                value++;

                if(value >= 100) {
                    break;
                } else {
                    publishProgress(value);
                }

                try{
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
            }
            return value;
        }
        // ......-> 가변인자, 매개변수를 몆개 전달해야될지 정확히 정해지지 않았을 떄
        // 실제로 개발할 떄는 거의 사용하지 않는 문법
        // onProgressUpdate(1,2,3)
        // onProgressUpdate(1,2)
        // onProgressUpdate(1)
        @Override
        protected void onProgressUpdate(Integer... values) {
           pb.setProgress(values[0].intValue());
        }

        @Override
        protected void onPostExecute(Integer integer) {
          pb.setProgress(0);
        }

        @Override
        protected void onCancelled() {
            pb.setProgress(0);
        }
    }
}