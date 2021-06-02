package com.koreait.examplerequest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText et;
    TextView tv;

    // 요청 큐는 한번만 만들고 사용할 계획
    static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.editText);
        tv = findViewById(R.id.textView);

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeRequest();
            }
        });

        if (requestQueue == null) {
            // 요청 큐가 만들어지지 않았다면 요청 큐를 만들어라
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    private void makeRequest() {
        String url = et.getText().toString();
        // 첫번째 매개 변수 -> 요청 방식
        // 두번쨰 매개 변수 -> 요청 URL
        // 세번쨰 매개 변수 -> 성공적으로 응답이 왔을 떄 호출될 콜백 메서드를 갖고 있는 리스너
        // 네번쨰 매개 변수 -> 서버에 에러(문제)가 생겼을 떄 호출될 콜백 메서드를 갖고 있는 리스너너
        // 마지막으로 오버라이딩한 getParmas -> POST방식으로 요청할떄 서버로 잔달해야하는 요청 파라미터를 설정하는 메서드
   StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    tv.append(response);
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            tv.append(error.getMessage());
          }

        }) {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String> params = new HashMap<String, String>();

            return super.getParams();
        }
    };
    // 요청 객체 안에 cache 기능이 내장되어 있음
    // cache - 응답 결과를 빠르게 보여주기 위해서 가장 마지막에 받았던 응답 결과를 저장해두는 곳
    // request.setShouldCache(false) -> 내장되어있는 캐시 기능을 사용하지 않겠다
    // 내장되어있는 캐시 기능을 사용하지 않겠다 -> 매번 새로운 결과값을 받아서 보여주겠다

    request.setShouldCache(false);
    requestQueue.add(request);
    tv.append("요청 보냄\n");
    }
  }

