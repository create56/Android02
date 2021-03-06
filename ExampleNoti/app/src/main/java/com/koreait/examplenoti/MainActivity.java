package com.koreait.examplenoti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    NotificationManager notiMng;

    private static String CHANNEL_ID = "channel1";
    private static  String CHANNEL_NAME = "Channel1";

    private static String CHANNEL_ID2 = "channel2";
    private static  String CHANNEL_NAME2 = "Channel2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNoti1();
            }
        });

        Button btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNoti2();
            }
        });
    }

    private void showNoti2() {
        notiMng = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = null;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if(notiMng.getNotificationChannel(CHANNEL_ID) == null) {
                notiMng.createNotificationChannel(new NotificationChannel(CHANNEL_ID2,CHANNEL_NAME2,
                        NotificationManager.IMPORTANCE_DEFAULT)
                );

                builder = new NotificationCompat.Builder(this,CHANNEL_ID2);
            } else {
                builder = new NotificationCompat.Builder(this);
            }

            Intent intent = new Intent(this,MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,101,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            // ?????? ????????? ??????
            builder.setContentTitle("????????? ??????");
            builder.setContentTitle("?????? ??????????????????");
            builder.setSmallIcon(android.R.drawable.ic_menu_view);
            // ?????? ????????? ?????? ????????? ????????? ????????? ?????????
            builder.setAutoCancel(true);
            // ?????? ????????? ???????????? ??? pendingIntent??? ?????? ?????? intent??? ????????? ???????????????
            builder.setContentIntent(pendingIntent);
            // ?????? ??????
            Notification noti = builder.build();
            // ??????
            notiMng.notify(2,noti);
        }
    }

    public void showNoti1() {
        notiMng = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = null;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if(notiMng.getNotificationChannel(CHANNEL_ID) == null) {
                notiMng.createNotificationChannel(new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,
                        NotificationManager.IMPORTANCE_DEFAULT)
                );

                builder = new NotificationCompat.Builder(this,CHANNEL_ID);
            } else {
                builder = new NotificationCompat.Builder(this);
            }
            // ?????? ????????? ??????
            builder.setContentTitle("????????? ??????");
            builder.setContentTitle("?????? ??????????????????");
            builder.setSmallIcon(android.R.drawable.ic_menu_view);
            // ?????? ??????
            Notification noti = builder.build();
            // ??????
            notiMng.notify(1,noti);
        }
    }
}