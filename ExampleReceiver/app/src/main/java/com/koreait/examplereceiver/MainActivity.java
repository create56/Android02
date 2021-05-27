package com.koreait.examplereceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements  AutoPermissionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 101 -> 모든 위험 권한을 자동 부여하도록 설정
        AutoPermissions.Companion.loadAllPermissions(this,101);
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[],int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        AutoPermissions.Companion.loadAllPermissions(this,requestCode,permissions,this);
    }

    public void onDenied(int requestCode, String[] permission) {

    }

    public void onGranted(int requestCode, String[] permission) {

    }
}