package com.koreait.examplelooper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText et;
    TextView tv;

    Handler handler = new Handler();

    ProcessThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.editText);
        tv = findViewById(R.id.textView);

        Button btn  =findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = et.getText().toString();
                Message msg = Message.obtain();
                msg.obj = input;

                thread.processHandler.sendMessage(msg);
            }
        });

        thread = new ProcessThread();
    }
    class ProcessThread extends Thread {
        ProcessHandler processHandler = new ProcessHandler();

        @Override
        public void run() {
            Looper.prepare();
            Looper.loop();
        }
        // obj로 받은 메세지를 쓰레드로
        class ProcessHandler extends Handler {
            public void handleMessage(Message msg) {
                final String output = msg.obj + "from thread.";

                // output에 저장된 메세지 출력력
               handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(output);
                    } // end run
                }); // end post
            } //end handMessage
        } // end ProcessHandler
    }
}