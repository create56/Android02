package com.koreait.examplelocationmap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pedro.library.AutoPermissions;

public class MainActivity extends AppCompatActivity {
    SupportMapFragment mapFragment;
    GoogleMap map;
    MarkerOptions MyLocationMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // XML로 SupportMapFragment 타입의 fragment를 추가
        // SupportMapFragment 타입의 fragment 안에 GoogleMap 객체가 들어 있음
        // GoogleMap 객체를 통해서 지도를 볼 수 있음

        //XML의 fragment를 불러와서
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d("Map","GooleMap is ready");
                map = googleMap;
                map.setMyLocationEnabled(true);
            }
        });
        // fragment 내 지도를 준비하도록 오버라이딩
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d("map","지도 준비됨");
                map = googleMap;
            }
        });

        /*
        try {
            MapsInitializer.initialize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
         */

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLocationService();
            }
        });
        }

    private void startLocationService() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try {
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(location != null) {
                double letitude = location.getLatitude();
                double longtitude = location.getLongitude();

                String message = "최근 위치 \n" + letitude + "\n Longtitude :" + longtitude;

                Log.d("Map",message);
            }

            GPSListener gpsListener = new GPSListener();
            long minTime = 10000;
            float minDistance = 0;

            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime,minDistance,gpsListener);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    class GPSListener implements LocationListener {


        @Override
        public void onLocationChanged(@NonNull Location location) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();

            String message = "내 위치\n Latitude : " + latitude + "\n Longitude : " + longitude;

            Log.d("Map",message);

            showCurrentLocation(latitude,longitude);


        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {

        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {

        }
    }

    private void showCurrentLocation(Double latitude, Double longitude) {
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint,15));

        showMyLocationMarker(curPoint);
    }

    private void showMyLocationMarker(LatLng curPoint) {
        if(myLocationMarker == null) {
            myLocationMarker = new MarkerOptions();
            myLocationMarker.position(curPoint);
            myLocationMarker.title("내 위치\n");
            myLocationMarker.snippet("GPS로 확인한 위치");
            myLocationMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation));
            map.addMarker()

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        AutoPermissions.Companion.parsePermissions(this,requestCode,permissions,this);
    }
    /*
    @Override
    protected void onResume() {
        // 액티비티가 화면에 보일 떄 내 위치 표시를 활성화
        super.onResume();

        if(map != null) {
            // 액티비티가 작동될 떄 내 위치 표시를 활성화
            map.setMyLocationEnabled(true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(map != null) {
            // 액티비티가 중지될 떄 내 위치 표시를 비활성화
            map.setMyLocationEnabled(false);
        }

    }
     */
}