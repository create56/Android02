package com.example.mealrecord;

import android.content.Intent;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {
    private EditText et_id,et_pass;
    private Button btn_login,btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id = findViewById(R.id.et_id);
        et_pass =findViewById(R.id.et_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_register =findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject();
                            boolean success = jsonObject.getBoolean("success");
                            if(success) {
                                String userID = jsonObject.getString("userID");
                                String userPass = jsonObject.getString("userPass");
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                AlertDialog.Builder dia = new AlertDialog.Builder(LoginActivity.this);
                                dia.setMessage("로그인에 성공하였습니다");
                            } else {
                                AlertDialog.Builder dia = new AlertDialog.Builder(LoginActivity.this);
                                dia.setMessage("로그인에 실패했습니다");
                            }

                            } catch (JSONException e) {
                             e.printStackTrace();
                        }
                    }
                };
                com.example.project2.RegisterRequest registerRequest = new com.example.project2.RegisterRequest(userID,userPass,responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(LoginRequest);
            }
        });
    }
}