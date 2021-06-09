package com.example.mealrecord;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private EditText et_id,et_pass;
    private Button btn_login,btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id = findViewById(R.id.et_ids);
        et_pass =findViewById(R.id.et_name);
        btn_login = findViewById(R.id.btn_login);
        btn_register =findViewById(R.id.btn_registers);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();

                // 로그인 성공 실패 여부
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject();
                            boolean success = jsonObject.getBoolean("success");

                            if(success) {
                                String userID = jsonObject.getString("userID");
                                String userPass = jsonObject.getString("userPass");

                                // 로그인 이동
                                Toast.makeText(getApplicationContext(),"로그인에 성공했습니다",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                intent.putExtra("userID",userID);
                                intent.putExtra("userPass",userPass);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(),"로그인에 실패했습니다",Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                // Volley 객체
                LoginRequest loginRequest = new LoginRequest(userID,userPass,responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
        // 로그인 창에서 회원가입을 누르면 회원가입 창으로~~
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerintent = new Intent(LoginActivity.this,RegisterActivity.class);
                LoginActivity.this.startActivity(registerintent);
            } // end onClick
        });
    }
}