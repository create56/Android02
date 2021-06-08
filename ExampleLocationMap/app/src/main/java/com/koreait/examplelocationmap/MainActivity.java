package com.koreait.examplelocationmap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends AppCompatActivity {
    SupportMapFragment mapFragment;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // XML로 SupportMapFragment 타입의 fragment를 추가
        // SupportMapFragment 타입의 fragment 안에 GoogleMap 객체가 들어 있음
        // GoogleMap 객체를 통해서 지도를 볼 수 있음

        //XML의 fragment를 불러와서
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        // fragment 내 지도를 준비하도록 오버라이딩
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d("map","지도 준비됨");
                map = googleMap;
            }
        });{
        }

    }
}