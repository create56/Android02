package com.example.mealrecord;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AutoLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_login);

        CustomTask task = new CustomTask();
        task.excute("rain483","1234");
    }

    class CustomTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("content-Type","application/x-www-from-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg ="ID=" + strings[0] + "&pass="+strings[1];
                osw.write(sendMsg);
                osw.flush();

                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(),"UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();

                    while ((str = reader.readLine() != null)) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                } else {
                    Log.i("통신 결과",conn.getResponseCode()+"에러");

                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
               e.printStackTrace();
            }
            return receiveMsg;
        }
    }
}