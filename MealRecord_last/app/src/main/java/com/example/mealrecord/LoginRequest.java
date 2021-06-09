package com.example.mealrecord;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    // 서버 설정 우리는 JSP로 설정

    final static private String URL ="";
    private Map<String,String> map;

    public LoginRequest(String userID, String userPass,Response.Listener<String>listener) {
        super(Method.POST,URL, listener,null);

        map = new HashMap<>();
        map.put("userID",userID);
        map.put("userPassword",userPass);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
