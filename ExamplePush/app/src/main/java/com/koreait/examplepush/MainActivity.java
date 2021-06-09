package com.koreait.examplepush;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView tv1;
    TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.textView);
        tv2 = findViewById(R.id.textView2);

        // FCM에 내 앱이 성공적으로 등록 되었을 떄
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this,new OnSuccessListener<InstanceIdResult>() {
           public void onSuccess(InstanceIdResult result) {
               // 등록된 ID를 가져와서
               String newToken = result.getToken();
               // tv2에 출력
               tv2.append("등록 id :" + newToken);
           }
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 인스턴스ID확인 버튼을 클릭했을 떄 FCM에 내 앱이 성공적으로 등록되었을 떄 등록된 ID를 가져와 출력
                String instanceId = FirebaseInstanceId.getInstance().getId();

                tv2.append("확인된 인스턴스 id : " + instanceId);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        tv2.append("onNewIntent 호출됨");
        if(intent != null) {
            processIntent(intent);
        }

        super.onNewIntent(intent);
    }

    private void processIntent(Intent intent) {
        String from = intent.getStringExtra("from");
        if(from == null) {
            tv2.append("from is null");
            return;
        }

        String contents = intent.getStringExtra("contents");
        tv2.append("DATA : " + from + " ," + contents);
        tv1.setText("[" + from + "]로부터 수신한 데이터 : " + contents);
    }


}