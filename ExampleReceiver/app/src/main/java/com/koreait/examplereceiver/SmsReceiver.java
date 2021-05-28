package com.koreait.examplereceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:")

    // SMS(문자 메세지)가 핸드폰으로 전달 됬을 떄
    // ㅁ
    @Override
    public void onReceive(Context context, Intent intent) {
     Log.i("SmsReceiver","onReceive() 메서드 호출됨");

        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = paresSmsMessage(bundle);

        if(messages != null && messages.length > 0) {
            String sender = messages[0].getOriginatingAddress();
            Log.i("SmsReceiver","SMS sender :" + sender);

            String contents = messages[0].getMessageBody();
            Log.i("SmsReceiver","SMS sender :" + contents);

            Date receiveDate = new Date(messages[0].getTimestampMillis());
            Log.i("SmsReceiver","SMS received date :" + receiveDate.toString());

            sendToActivity(context, sender, contents, receiveDate);
        } // end if
    } // end onReceive


    private SmsMessage[] paresSmsMessage(Bundle bundle) {
        // bundle 객체가 갖고 있는 pdus 데이터를 꺼냄
        Object[] objs = (Object[]) bundle.get("pdus");
        // 문자 메세지들을 저장하기 위해서 문자메서지타입(SmsMessage)의 배열 생성
        SmsMessage[] messages = new SmsMessage[objs.length];

        int smsCount = objs.length;
        for (int i=0; i<smsCount; i++) {
           // Build.VERSION_CODES.M -> 마시멜로버전
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i],format);
            } else {
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
            }
        }
        return  messages;
    }

    private void sendToActivity(Context context, String sender String content, Date receivedDate) {
        Intent myIntent = new Intent(context, SmsActivity.class);
        // 화면을 띄울 떄 아래와 같은 플래그가 적용된 채로 띄워라
        //FLAG_ACTIVITY_NEW_TASK -> 새로운 테스크를 생성해서 테스크 안에 액티비티를 추가
        //FLAG_ACTIVITY_SINGLE_TOP ->  태스크 내 같은 액티비티가 여러게 만들어져 있었을 떄 모두 제거하고 하나만 남기는 플래그
        // FLAG_ACTIVITY_CLEAR_TOP -> 태스크 내 액티비티가 이미 만들어져 있다면 만들어져있는 액티비티를 활성화 시커라
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        myIntent.putExtra("sender",sender);
        myIntent.putExtra("content", content);
        myIntent.putExtra("receiveDate",sdf.format(receivedDate));

        context.startActivity(myIntent);
    }

}