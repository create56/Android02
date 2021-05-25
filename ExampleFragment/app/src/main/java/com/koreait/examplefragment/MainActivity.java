package com.koreait.examplefragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    MainFragment1 mainFragment;
    MenuFragment1  menuFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = (MainFragment1) getSupportFragmentManager().findFragmentById(R.id.mainFragment);
        menuFragment = new MenuFragment1();
    }
    public void onFragmentChanged(int index) {
        if(index == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container,
                menuFragment).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.container,
                mainFragment).commit();
        }
    }
}