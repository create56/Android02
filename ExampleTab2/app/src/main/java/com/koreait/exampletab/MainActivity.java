package com.koreait.exampletab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    Fragment1 fg1;
    Fragment2 fg2;
    Fragment3 fg3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fg1 = new Fragment1();
        fg2 = new Fragment2();
        fg3 = new Fragment3();

        getSupportFragmentManager().beginTransaction().replace(R.id.container,fg1).commit();

        BottomNavigationView bnv = findViewById(R.id.bottom_navigation);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab1:
                        Toast.makeText(getApplicationContext(),"첫번쨰",Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,fg1).commit();
                        return true;
                }
                switch (item.getItemId()) {
                    case R.id.tab2:
                        Toast.makeText(getApplicationContext(),"두번쨰",Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,fg2).commit();
                        return true;
                }
                switch (item.getItemId()) {
                    case R.id.tab3:
                        Toast.makeText(getApplicationContext(),"세번쨰",Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,fg3).commit();
                        return true;
                }
                return false;
            }
        });

    }
}