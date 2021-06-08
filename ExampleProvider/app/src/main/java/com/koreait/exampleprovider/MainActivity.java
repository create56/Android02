package com.koreait.exampleprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);

        Button btn1 = findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertPerson();
            }
        });

        Button btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryPerson();
            }
        });

        Button btn3 = findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button btn4 = findViewById(R.id.button4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePerson();
            }
        });
    } // end Create

    public void deletePerson() {
        String uriString = "content://com.koreait.provider/person";
        Uri uri = new Uri.Builder().build().parse(uriString);

        String selection = "name =?";
        String[] selectionArgs = new String[] {"john"};

        int  count = getContentResolver().delete(uri,selection,selectionArgs);
        println("delete 결과 : " + count);
    }

    public void updatePerson() {
        String uriString = "content://com.koreait.provider/person";
        Uri uri = new Uri.Builder().build().parse(uriString);

        // update할 조건
        String selection = "mobile = ?";
        String[] selectionArgs = new String[] {"010-1000-1000"};

        ContentValues updateValue = new ContentValues();
        updateValue.put("mobile","010-2000-2000");

        int count = getContentResolver().update(uri,updateValue,selection,selectionArgs);
        println("update 결과 :" + count);

    }

    public void queryPerson() {
        try {
            String urlString = "content://com.koreait.provider/person";
            Uri uri = new Uri.Builder().build().parse(urlString);

            String[] column = new String[] {"name","age","mobile"};

            // ASC : 오름차순 정렬 / Ascending
            // DESC : 내림 차순 / Descending
            Cursor cursor = getContentResolver().query(uri,column,null,null,"name ASC");
            println("query 결과 :" + cursor.getCount());

            int index = 0;
            while (cursor.moveToNext()) {
                // 검색결과에서 name 칼럼의 인덱스 번호
                int nameColumnIndex = cursor.getColumnIndex(column[0]);
                String name = cursor.getString(nameColumnIndex);

                int ageColumnIndex = cursor.getColumnIndex(column[1]);
                int age = cursor.getInt(ageColumnIndex);

                int mobileColumnIndex = cursor.getColumnIndex(column[2]);
                String mobile = cursor.getString(mobileColumnIndex);

                println("#" + index + "->" + name + ", " + age + "," + mobile);
                index++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
         // end while
    }

    public void insertPerson() {
        println("insertPerson 호출됨");

        // 데이터를 제공 받을 앱의 URI
        String uriString = "com.koreait.provider/person";
        Uri uri = new Uri.Builder().build().parse(uriString);

        /*
        // 다른 앱에 있는 데이터를 사용하기 위해서 조회
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        // 칼럼의 이름들을 저장
        String[] colums = cursor.getColumnNames();

        // 조회된 데이터의 개수 출력
        println("columns count ->" + colums.length);
        for(int i=0; i<colums.length; i++) {
            // 조회된 데이터의 처음부터 끝까지 하나씩 접근해서 출력
            println("#" + i + ":" + colums[i]);
        }
         */

        // 다른 앱에 데이터를 넣기 위한 객체
        ContentValues values = new ContentValues();
        // name -> 칼럼의 이름
        // john -> 칼럼의 값
        values.put("name","john");
        values.put("age","20");
        values.put("mobile","010-1000-1000");

        uri = getContentResolver().insert(uri,values);
        println("insert 결과 ->" + uri.toString());

    }

    public  void println(String message) {
        tv.append(message +"\n");
    }
}