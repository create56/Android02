package com.koreait.exampleservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }
    // 서비스가 서버의 역활을 하면서 액티비티로 데이터를 전달하는 동의 역활을 할 떄
    // 이 메서드를 적절히 오버라이딩 하면 됨
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("MyService","onCreate() 호출됨");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    // 서비스가 실행될떄 호출되는 매서드
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("MyService","onStartCommend() 호출");

        if(intent == null) {
            // 서비스가 실행됬을떄  인텐트를 전달받지 못헀다면은
            // START_STICKY -> 비정상 종료
            // 서비스의 비정상 종료이므로 시스템이 자동으로 재시작을 시켜줌
            // 비정상 종료가 아니라 정상종료 상태로 끝내고 싶으면
            // 아무 값(1을 뺸)이나 return 하면 됨
           return Service.START_STICKY;
        } else {

            String commend = intent.getStringExtra("commend");
            String name = intent.getStringExtra("name");

            Log.d("MyService","commend :" + commend +", name :" +name);

            for (int i=0; i<5; i++) {
                try{
                   Thread.sleep(1000);
                } catch (Exception e) {
                    //아무것도 처리하지 않음
                }
                Log.d("MyService","Waiting" + i + "seconds.");
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }
}