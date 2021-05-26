package com.koreait.exampletab;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {
    Toolbar tb;
    Fragment1 fg1;
    Fragment2 fg2;
    Fragment3 fg3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // XML 레이아웃을 정의한 toolbar를 가져옴
        tb = findViewById(R.id.toolbar);
        // toolbar를 액션바로 만들어줌
        // 액티비티에 디폴트로 만들어진 액션바가 없어야 동작을 함
        // 프로젝트를 만들 떄 메인 액티비티에 자동으로 액션바가 만들어지기 떄문에
        // 이 메서드를 사용하려면 직접 자동으로 만들어진 액션바를 해체해야함
        setSupportActionBar(tb);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowTitleEnabled(false);

        fg1 = new Fragment1();
        fg2 = new Fragment2();
        fg3 = new Fragment3();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fg1).commit();

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("통화기록"));
        tabs.addTab(tabs.newTab().setText("스팸기록"));
        tabs.addTab(tabs.newTab().setText("연락처"));

       tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {
               int postion = tab.getPosition();
               Log.d("MainActivity","선택된 됨 :"+postion);

               Fragment selected = null;
               if(postion == 0) {
                   selected = fg1;
               } else if(postion == 1) {
                   selected = fg2;
               } else if(postion == 2) {
                   selected = fg3;
               }

               getSupportFragmentManager().beginTransaction().replace(R.id.container,selected).commit();
           }

           @Override
           public void onTabUnselected(TabLayout.Tab tab) {

           }

           @Override
           public void onTabReselected(TabLayout.Tab tab) {

           }
       });
    }


}