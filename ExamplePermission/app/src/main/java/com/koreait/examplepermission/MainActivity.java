package com.koreait.examplepermission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] permissions = {
                // 권한을 더 추가 시킬수 있다!!!
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        checkPermissions(permissions);
    }
    // 정해준 권한들에 대해서 권한이 부여되어 있는지 확인하는 메서드
    public void checkPermissions(String[] permissions) {
        // 권한이 승인되지 않은 권한 목록
        ArrayList<String> targetList = new ArrayList<String>();

        // 권한들을 하나하나 체크
        for (int i=0; i<permissions.length; i++) {
            // n번쨰 권한
            String curPermission = permissions[i];

            //checkSelfPermission-> 매개변수로 전달된 curPermission이 권한이 부여됬는지 체크하는 메서드
            int permissionCheck = ContextCompat.checkSelfPermission(this, curPermission);
            if(permissionCheck == PackageManager.PERMISSION_GRANTED) {
                Log.d("permission",curPermission + "권한 있음");
            } else {
                Log.d("permission",curPermission + "권한 없음");

                if(ActivityCompat.shouldShowRequestPermissionRationale(this,curPermission)) {
                    Log.d("permission",curPermission + "권한 설명이 필요함");
                } else {
                    // 권한이 부여받지 못하면
                    targetList.add(curPermission);
                }
            }
        }

        String[] targets = new String[targetList.size()];
        targetList.toArray(targets);
        // targets에 들어있는 권한을 요청하는 대화상자를 띄움
        ActivityCompat.requestPermissions(this,targets,101);
    }

    // requestCode -> 권한 수락 / 거절한 요청의 번호
    // permissions -> 수락 / 거절한 권한의 이름
    // grantResults -> 수락/ 거절의 여부
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 101:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("permission",  "첫번쨰 권한을 사용자가 승인");
                } else {
                    Log.d("permission", "첫번쨰 권한을 사용자가 승인하지 않음");
                }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}