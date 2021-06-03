package com.example.mealrecord;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
// 질문 - 리싸이클러 뷰 적용 시 AppCompatActivity 상속 문제
//      페이지 이동이 안됨
public class DailyRecordAdapter extends RecyclerView.Adapter<DailyRecordAdapter.ViewHolder> {
    ArrayList<DailyRecord> dailyRecords = new ArrayList<DailyRecord>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View dailyView = inflater.inflate(R.layout.activity_main, viewGroup, false);

        return new ViewHolder(dailyView);
    }

    @Override
    public void onBindViewHolder(DailyRecordAdapter.ViewHolder holder, int position) {
        DailyRecord daily = dailyRecords.get(position);
    }

    @Override
    public int getItemCount() {
        return dailyRecords.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView recordCategory;
        TextView recordFoodName;

        public ViewHolder(View itemView){
            super(itemView);

            recordCategory = itemView.findViewById(R.id.recordCategory);
            recordFoodName = itemView.findViewById(R.id.recordFoodName);
        }

        public void setItem(DailyRecord record){
            recordCategory.setText(record.getCategory());
            recordFoodName.setText(record.getFoodName());
        }
    }

    public void addItem(DailyRecord item){
        dailyRecords.add(item);
    }

    public void setItems(ArrayList<DailyRecord> dailyRecords){
        this.dailyRecords = dailyRecords;
    }

    public DailyRecord getItem(int position){
        return dailyRecords.get(position);
    }

    public void setItem(int position, DailyRecord item){
        dailyRecords.set(position, item);
    }
}
