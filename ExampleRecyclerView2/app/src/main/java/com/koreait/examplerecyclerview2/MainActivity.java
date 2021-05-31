package com.koreait.examplerecyclerview2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);

        // 레이아웃 매니저를 설정 -> 리싸이클러뷰가 보일 형태를 설정
        //  - 격자모양으로 설정 / 한 행에 두개의 아이템이 보이도록
        recyclerView.setLayoutManager(layoutManager);

        // 원본 데이터(아이템)를 관리하면서 각각의 아이템이 리싸이클러뷰에서 보이도록
        // 하는 adapter 생성
        PersonAdapter adapter = new PersonAdapter();
        adapter.addItem(new Person("김민수","010-1234-5678"));
        adapter.addItem(new Person("김하늘","010-5678-9012"));
        adapter.addItem(new Person("홍길동","010-3456-7890"));
        adapter.addItem(new Person("김민수","010-1234-5678"));
        adapter.addItem(new Person("김하늘","010-5678-9012"));
        adapter.addItem(new Person("홍길동","010-3456-7890"));
        adapter.addItem(new Person("김민수","010-1234-5678"));
        adapter.addItem(new Person("김하늘","010-5678-9012"));
        adapter.addItem(new Person("홍길동","010-3456-7890"));
        adapter.addItem(new Person("김민수","010-1234-5678"));
        adapter.addItem(new Person("김하늘","010-5678-9012"));
        adapter.addItem(new Person("홍길동","010-3456-7890"));
        adapter.addItem(new Person("김민수","010-1234-5678"));
        adapter.addItem(new Person("김하늘","010-5678-9012"));
        adapter.addItem(new Person("홍길동","010-3456-7890"));

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnPersonItemClickListener() {
            @Override
            public void onItemClick(PersonAdapter.ViewHolder holder, View view, int position) {
                Person.item = adapter.getItem(position);
                Toast.makeText(getApplicationContext(),"선택된 아이템 :" + item.getName(),Toast.LENGTH_LONG).show();
            }
        });

    }
}