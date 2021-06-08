package com.koreait.exampleprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PersonProvider extends ContentProvider {

    private static final String AUTHORITY = "com.koreait.provider";
    private static  final String BASE_PATH = "person";
    // content:// -> 내용제공자에 의해 제어되는 데이터라는 의미(프로토콜)
    // AUTHORITY -> 내용 제공자의 ID의 역활
    // BASE_PATH -> 테이블 이름
    private static  final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

    private static  final  int PERSONS = 1;
    private static  final  int PERSON_ID = 2;

    private SQLiteDatabase database;

    private  static  final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        // PersonProvider 내용 제공자로 접근할 수 있는 URI(통로)를 제한하기 위해서
        // URIMatcher 객체에 두 개의 URI를 설정
        uriMatcher.addURI(AUTHORITY,BASE_PATH,PERSONS);
        uriMatcher.addURI(AUTHORITY,BASE_PATH + "/#", PERSON_ID);
    }

    @Override
    public boolean onCreate() {
        DatabaseHelper helper = new DatabaseHelper(getContext());
        // 내용 제공자의 CRUD  동작을 실제로 구현
        database = helper.getWritableDatabase();
        return false;
    }
    // 내용제공자를 통해서 Read를 할떄 호춣되는 콜백 메서드
    // projection -> 어떤 칼럼을 조회할 것인지 (null 전달된다면 모든 칼럼을 조회)
    // selection -> WHERE절에 들어갈 조건(null이 전달된다면 모든 데이터(레코드)를 조회)
    // selectionArgs -> JSP/Servlet에서 PreParedStatement를 구성했을 떄처럼 where절을 동작으로 구성했을떄
                        // where절에 들어가야할 값
    // sortorder -> 정렬 방법

    @Nullable
    @Override
    public Cursor query(Uri uri,String[] projection,  String selection,String[] selectionArgs,String sortOrder) {
        Cursor cursor;

        switch (uriMatcher.match(uri)) {
            // 데이터베이스를 조회(SELECT)해라
            // query(1,2,3,4,5,6,7)

            case PERSONS:
                cursor = database.query(
                        DatabaseHelper.TABLE_NAME,
                        DatabaseHelper.ALL_COLUMNS,
                       selection, null,null,null,DatabaseHelper.PERSON_NAME + "ASC");
                break;
            default:
                throw new IllegalArgumentException("알 수 없는 URI :" +uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(),uri);

        return null;
    }

    // MINE타입이 무엇인지 알려주는 메서드
    // query 콜백 메서드를 사용해서 조회를 했을 떄 조회 결과가 어떤 데이터타입인지 알려주는 메서드
    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case PERSONS:
                return "vnd.android.cursor.dir/persons";
            default:
                throw new IllegalArgumentException("알수 없는 URI :" + uri);
        }
    }

    // 내용 제공자를 통해서 insert를 할떄 호출되는 콜백 메서드
    // values -> 추가할 데이터의 칼럼명과 칼럼의 값
    @Nullable
    @Override
    public Uri insert( Uri uri,ContentValues values) {
        // insert메서드는 추가된 레코드의 PRIMARY KEY 값을 반환 (insert를 하지 못했다면 0을 반환)
        long id = database.insert(DatabaseHelper.TABLE_NAME,null,values);

        if(id > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI,id);
            getContext().getContentResolver().notifyChange(_uri,null);
            // 추가한 레코드에 접근할 수 있도록 URI를 반환
            return _uri;
        }

        throw new SQLException("추가 실패 -> URI : " + uri);
    }
    // 내용 제공자을 통해서 delete를 하고 싶을 떄
    // s -> where절
    //selectionArgs -> JSP/Servlet에서 PreParedStatement를 구성했을 떄처럼 where절을 동작으로 구성했을떄
    //                  where절에 들어가야할 값
    // delete를 하게되면은 delete가 된 레코드(행)의 개수를 가지고 delete가 정상적으로 이뤄졌는지 판단
    @Override
    public int delete(Uri uri, String selection,String[] selectionArgs) {
        int count = 0;

        switch (uriMatcher.match(uri)) {
            case PERSONS:
                count = database.delete(DatabaseHelper.TABLE_NAME,selection,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("알수 없는 URI" + uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }
    // 내용제공자를 통해서 update 하고 싶을 떄 호출되는 콜백 메서드
    // values -> 수정할 칼럼의 이름, 칼럼의 값 (이 매개변수는 절대 null이 되면 안된다.)
    // s -> where절
    //selectionArgs -> JSP/Servlet에서 PreParedStatement를 구성했을 떄처럼 where절을 동작으로 구성했을떄
    //                        // where절에 들어가야할 값
    // update를 하게 되면은 update가 된 레코드(행)의 개수를 사용해서 update가 정상적으로 이뤄졌는지 여부를 판단
    @Override
    public int update(Uri uri, ContentValues values, String selection,String[] selectionArgs) {
        int count = 0;

        switch (uriMatcher.match(uri)) {
            case PERSONS:
                count = database.update(DatabaseHelper.TABLE_NAME,values,selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("알수 없는 URI" + uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }
}
