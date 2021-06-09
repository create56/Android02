package com.example.mealrecord;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {
    private final ArrayList<String> dayOfMonth;
    private final OnItemListener onItemListener;

    public CalendarAdapter(ArrayList<String> dayOfMonth, OnItemListener onItemListener) {
        this.dayOfMonth = dayOfMonth;
        this.onItemListener = onItemListener;
    }

    @Override
    public CalendarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int)(parent.getHeight() * 0.166666666);
        return new CalendarViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(CalendarViewHolder holder, int position) {
        holder.dayOfMonth.setText(dayOfMonth.get(position));
    }

    @Override
    public int getItemCount() {
        return dayOfMonth.size();
    }

    public interface OnItemListener{
        void onItemClick(int position, String dayText);
    }
}
