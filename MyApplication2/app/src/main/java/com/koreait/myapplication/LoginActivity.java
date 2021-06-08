package com.koreait.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import util.ToastUtil;

public class LoginActivity extends AppCompatActivity {
    private static RequestQueue rq;
    // 응답코드를 사용하기 위한 전역변수
    private int statusCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(rq == null) {
          rq = Volley.newRequestQueue(this);
        }
    }

    public void clickLoginBtn(View v) {
        EditText idEditText = findViewById(R.id.et_id);
        EditText pwEditText = findViewById(R.id.et_pw);

        String id = idEditText.getText().toString();
        String pw = pwEditText.getText().toString();

        if (id.trim().length() == 0 || pw.trim().length() == 0) {
            ToastUtil.showShort(this, "아이디 또는 비밀번호가 비어있습니다");
            return;
        } else if (id.length() > 20) {
            ToastUtil.showShort(this, "아이디는 20자 이하입니다");
            return;
        } else if (pw.length() > 16) {
            ToastUtil.showShort(this, "아이디는 16자 이하입니다");
            return;
        }
        // 아이디, 비밀번호를 정상적으로 입력했을 떄
        StringRequest request = new StringRequest(
                Request.Method.POST, "http://192.168.2.6:8083/member/login",
                // 성공 했을 떄 리스너
                new LoginSuccessListener(),
                // 실패 했을 떄 리스너
                new LoginFailureLister()
        ) {
            // POST Method로 요청할 떄 요청 파라미터를 구성하는 역활
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("id",id);
                params.put("pw",pw);

                return params;
            }

            // 요청을 했을  떄 성공을 했다. 이떄 성공에 관련된 status code가 여러개 일수 있음
            // 회원가입
            // 1.이전에 가입 했던 정보 그대로 다시 회원 가입을 시도 - 응답 코드 : 200
            // 2.새롭게 회원가입을 시도 한다 - 응답코드 :201
            // 3.휴면 계정의 정보를 그대로 사용해서 회원 가입을 시도 - 응답 코드 : 202
            // 4.탈퇴한 계정의 정보를 그대로 사용해서 회원 가입을 시도 응답 코드 : 203

            // 응답 코드, 결과값 이러한 것들을 우리 앱에 맞게 변환시켜주는 역활
            // 요청이 돌아왔을떄(100,200,300번대 응답 코드)
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                if(response == null) {
                    statusCode = response.statusCode;
                }
                // 요청이 성공했을 떄 응답 코드가 몆번인지 저장
                return super.parseNetworkResponse(response);
            }
        };

        request.setShouldCache(false);
        rq.add(request);
    }
    private class LoginSuccessListener implements Response.Listener<String> {

        @Override
        public void onResponse(String response) {
            ToastUtil.showLong(getApplicationContext(),"로그인이 되었습니다.");

            // 일반적으로 앱은 처을 설치를 하고 로그인을 하지 않았을 떄만 로그인을 해야하고
            // 로그인을 한번 하고 나면 지우기 전까지는 로그인이 풀리지 않음
            // 로그인을 한번 하고 난 다음에 지우기 전까지 로그인을 풀리지 않도록 하려면?
            // hint.데이터베이스를 활용해서 로그인한 사용자의 정보를 앱에서 저장해 둔다

            // 위의 문제를 해결하고 나면 회원정보를 수정헀을떄 앱의 데이터베이스 내 사용자 정보를 갱신해줘야함
            // 회원탈퇴,로그 아웃 등의 동작을 할 떄도 앱 의 데이터베이스 내 사용자 정보를 갱신해줘야함

            // 이렇게 구성을 하고나면 서버의 DB에서 사용자의 정보는 앱을 지우고 다시 설치했을 떄 로그인을 하면
            // 사용자의 정보를 전달해주는 용도가 클것
            // 앱의 DB랑 서버의 DB랑 사용자의 정보가 달라질 수도 있음
            // 같은 사용자지만 앱 내 저장된 사용자의 정보랑 저장된 사용자의 정보가 달라질 수 있음
            // EX) 앱 개발자가 사용자가 정보를 수정했을 떄 앱 DB 내 사용자 정보는 수정했는데 서버의 회원 정보 수정 API를 호출하지 않았다면
            // 서버 회원 정보 수정 API는 호출 했는데 앱 내 사용자 정보를 수정하지 않았다던지 하는 등
            // 위와 같이 구성을 하면 주기적으로 서버와 앱 내 사용자 정보를 대조해서 동일하게 맞추는 동자도 필요해짐 / 일명 동기화화
           Log.d("login","응답 코드 :" + statusCode);
        }
    }

    private class LoginFailureLister implements Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError error) {
            int statusCode = error.networkResponse.statusCode;

            if(statusCode == 400) {
                ToastUtil.showShort(getApplicationContext(),"잘못된 값을 입력하였습니다");
                // 응답코드가 400 일떄
            } else if(statusCode == 404) {
                // 응답코드가 404 일떄
                ToastUtil.showShort(getApplicationContext(),"아이디 또느 비밀번호가 올바르지 않습니다");
            } else {
                ToastUtil.showLong(getApplicationContext(),"잠시 후 다시 시도해 주세요");
            }


            // 응답코드가 500 일떄
        }
    }
}