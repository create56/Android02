package com.koreait.examplereceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {

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
}