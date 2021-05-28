package com.koreait.examplenumber;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;

// 문자 메세지가 오면 읽어서
// 문자 메서지의 내용에서
// 인증번호를 추출해서
// 인증번호 입력 액티비티로 전달하는 역활
public class SmsReceiver extends BroadcastReceiver {

    // 문자 메세지가 오면 실행되는 콜백 메서드
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = parseSmsMessage(bundle);

        if(msgs !=null && msgs.length > 0) {
            // 문자 보낸 연락처
            String sender = msgs[0].getOriginatingAddress();
            // 문자를 보낸 사람의 연락처가 010-1234-5678 일경우에
            if("01012345678".equals(sender)) {
                // 인증번호가 포함된 문자의 내용
                String contents = msgs[0].getMessageBody();

                if(contents.indexOf("number") !=1) {
                    // 문자의 인덱스 번호
                    int numberIndex = contents.indexOf(":");
                    // 1234 1-> 2-> 3-> 4-> 콤마 다음으로 연결
                    // 문자 다음 다음 인덱스 번호의 문자열부터 마지막까지 자름
                    String number = contents.substring(numberIndex+2);

                    // 인증번호를 입력할 액티비티 띄움
                    sendToActivity(context,number);
                }
            } else {
                // 문자를 보낸 사람의 연락처가 010-1234-5678이 아닌 경우에는
                // 아무것도 하지 않는다
            } // end if
        } // end if
    } // end onReceive

    //번들에 들어있는 문자메세지를 꺼내는 메서드
    private SmsMessage[] parseSmsMessage(Bundle bundle) {
        Object[] obj = (Object[]) bundle.get("pdus");

        SmsMessage[] messages = new SmsMessage[obj.length];

        int smsCount = obj.length;
        for(int i=0; i<smsCount; i++) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[]) obj[i],format);

            } else {
                messages[i] = SmsMessage.createFromPdu((byte[]) obj[i]);
            }
        }
        return messages;
    }
    // 액티비티를 실행하는 메서드
    private void sendToActivity(Context context, String number) {
        // SmsActivity 띄워라
        Intent myIntent = new Intent(context, SmsActivity.class);

        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        myIntent.putExtra("number",number);

        context.startActivity(myIntent);
    }
}
