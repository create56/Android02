package com.example.mealrecord;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class AddMeal extends AppCompatActivity {
    TextView title;
    EditText foodDetails;
    Button addPicButton;
    Button saveButton;
    ImageView foodPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_meal_activity);

        // 음식 설명
        foodDetails = findViewById(R.id.foodDetails);
        // 사진 추가할 버튼
        addPicButton = findViewById(R.id.addPicButton);
        // 저장 버튼
        saveButton = findViewById(R.id.saveButton);
        // MealRecord 글씨
        title = findViewById(R.id.mealRecordText);

        foodPic = findViewById(R.id.foodPic);

        // 상단바에 MealRecord 텍스트 뷰 클릭 시 메인 화면으로 이동
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        // 사진 추가 버튼 눌렀을 때
        addPicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        }); // end addPicButton.setOnClickListener

        // 저장하기 버튼 눌렀을 때
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 저장 후 메인 화면으로 이동
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }); // end saveButton.setOnClickListener
    }

    public void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(intent, 101);
        
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == 101){
            if(resultCode == RESULT_OK){
                Uri fileUri = data.getData();

                ContentResolver resolver = getContentResolver();

                try {

                    InputStream is = resolver.openInputStream(fileUri);
                    Bitmap imgBitmap = BitmapFactory.decodeStream(is);
                    foodPic.setImageBitmap(imgBitmap);

                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
