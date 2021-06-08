package com.example.mealrecord;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_id,et_name,et_pass,et_pwc,et_email;
    private Button btn_register,btn_check;
    private boolean validate = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_id = findViewById(R.id.et_ids);
        et_name = findViewById(R.id.et_name);
        et_pass = findViewById(R.id.et_pas);
        et_pwc = findViewById(R.id.et_pwc);
        et_email = findViewById(R.id.et_email);



        class CustomTask extends AsyncTask<String,Void,String> {
            String sendMsg,receiveMsg;
            @Override
            protected String doInBackground(String... strings) {
                try {
                    String str;
                    URL url = new URL("http://192.168.2.6:8083/member/Login.jsp");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                    conn.setRequestMethod("POST");
                    OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                    sendMsg = "id=" + strings[0] + "&pw="+strings[1] +"&type=" + strings[2];
                    osw.write(sendMsg);
                    osw.flush();
                    if(conn.getResponseCode() == conn.HTTP_OK) {
                        InputStreamReader tmp = new InputStreamReader(conn.getInputStream(),"UTF-8");
                        BufferedReader reader = new BufferedReader(tmp);
                        StringBuffer buffer = new StringBuffer();
                        while ((str = reader.readLine()) !=null) {
                            buffer.append(str);
                        }
                        receiveMsg = buffer.toString();
                    } else {
                        Log.i("통신결과",conn.getResponseCode() + "에러");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return receiveMsg;
            }
        }


        // 중복 버튼을 눌렀을 떄
        btn_check = findViewById(R.id.btn_check);
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = et_id.getText().toString();
                if(userID.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    Dialog dialog = builder.setMessage("아이디를 입력하세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                    return;
                }
                Response.Listener<String>responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(validate){
                            return;
                        }
                        try{
                            JSONObject jsonObject = new JSONObject();
                            boolean success = jsonObject.getBoolean("success");
                            if(success) {
                                AlertDialog.Builder dia = new AlertDialog.Builder(RegisterActivity.this);
                                dia.setMessage("사용할 수 있는 아이디입니다");
                            } else {
                                AlertDialog.Builder dia = new AlertDialog.Builder(RegisterActivity.this);
                                dia.setMessage("이미 존재하는 아이디입니다");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                // volley
                ValidateRequest validateRequest = new ValidateRequest (userID,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(validateRequest);
            }
        });

        // 회원가입 버튼을 눌렀을떄
        btn_register = findViewById(R.id.btn_registers);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                String userID = et_id.getText().toString();
                String userName = et_name.getText().toString();
                String userPass = et_pass.getText().toString();
                String userPcf = et_pwc.getText().toString();
                String userEmail = et_email.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject();
                            boolean success = jsonObject.getBoolean("success");
                            if(success) {
                                AlertDialog.Builder dia = new AlertDialog.Builder(RegisterActivity.this);
                                dia.setMessage("회원가입에 성공했습니다");
                            } else {
                                AlertDialog.Builder dia = new AlertDialog.Builder(RegisterActivity.this);
                                dia.setMessage("회원가입에 실패했습니다");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(userID,userName,userPass,userPcf,userEmail,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });


    }
}