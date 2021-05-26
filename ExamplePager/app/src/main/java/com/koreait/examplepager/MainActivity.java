package com.koreait.examplepager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vp.findViewById(R.id.pager);
        vp.setOffscreenPageLimit(3);

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());

        Fragment1 fg1 = new Fragment1();
        adapter.addItem(fg1);

        Fragment1 fg2 = new Fragment1();
        adapter.addItem(fg2);

        Fragment1 fg3 = new Fragment1();
        adapter.addItem(fg3);

        vp.setAdapter(adapter);
    }
   class MyPagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<Fragment>();
       public MyPagerAdapter(@NonNull @org.jetbrains.annotations.NotNull FragmentManager fm) {
           super(fm);
       }



       public Fragment getItem(int position) {
           return items.get(postion);
       }

       @Override
       public int getCount() {
           return items.size();
       }
   }
}