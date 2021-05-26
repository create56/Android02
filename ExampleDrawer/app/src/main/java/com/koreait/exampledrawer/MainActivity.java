package com.koreait.exampledrawer;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;


import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.koreait.exampledrawer.databinding.ActivityMainBinding;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,FragmentCallback {
    // 메인화면에서 보여줄 프래그먼트들
    Fragment1 fg1;
    Fragment2 fg2;
    Fragment3 fg3;
    // 바로가기 메뉴
    DrawerLayout drawer;
    //액션바
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //XML 구성한 액션바
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //이 앱의 액션바로 사용하도록(NoActionBar일 떄만 동작)
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        //XML 구성한 바로가기 메뉴
        drawer.addDrawerListener(toggle);
        //toggle -> 바로가기 메뉴를 보였다가 보이지 않았다가 하는 이벤틀처리를 위한 객체
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fg1.new Fragment1()
        fg2.new Fragment2();
        fg3.new Fragment3();

        getSupportFragmentManager().beginTransaction().replace(R.id.container,fg1).commit();

    }
    // 바로가기 메뉴가 열려 있는 상태에서
    // 바로가기 메뉴(주로) 오른쪽에 있는 검은 음영처리된 영역을 눌렀을 떄
    // (바로가기 메뉴가 열려 있는데 바로가기 메뉴가 아닌 다른 부분을 선택했을떄)
    @Override
    public void onBackPressed() {
        //바로가기 메뉴가 열려 있다면
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            //바로가기 메뉴를 닫아라
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //그렇지 않다면 onBackPressed
            super.onBackPressed();
        }
    }
    // 바로가기 메뉴 안에서 메뉴를 선택 했을떄
    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.menu1) {
            Toast.makeText(this,"첫 번쨰",Toast.LENGTH_LONG).show();
            onFragmentSelected(0,null);
        } else if(id == R.id.menu2) {
            Toast.makeText(this,"두 번쨰",Toast.LENGTH_LONG).show();
            onFragmentSelected(0,null);
        } else if(id == R.id.menu3) {
            Toast.makeText(this,"세 번쨰",Toast.LENGTH_LONG).show();
            onFragmentSelected(0,null);
        }

        //바로가기 메뉴에서 메뉴를 선택하면 바로가기 메뉴가 닫히도록 만들어주는 코드
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onFragmentSelected(int position, Bundle bundle) {
        Fragment curFragment = null;

        if(position == 0) {
            curFragment = fg1;
            toolbar.setTitle("첫번쨰");
        } else if(position == 1) {
            curFragment = fg2;
            toolbar.setTitle("두번쨰");
        } else {
            curFragment = fg3
            toolbar.setTitle("세번쨰");
        }
    }
    // onFragmentSelected 추가
}