package com.example.mealrecord;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    // 서버 설정 우리는 JSP로 설정
    final static private String URL ="";
    private Map<String,String> map;

    public RegisterRequest(String userID, String userName, String userPass, String userPcf, String userEmail,
                           Response.Listener<String>listener) {
        super(Method.POST,URL,listener,null);

        map = new HashMap<>();
        map.put("userID",userID);
        map.put("userPassword",userPass);
        map.put("userName",userName);
        map.put("userPwc",userPcf);
        map.put("userEmail",userEmail);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
