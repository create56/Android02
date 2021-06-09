package com.example.mealrecord;

import android.graphics.ColorSpace;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class VitaminAdapter extends RecyclerView.Adapter<VitaminAdapter.ViewHolder> {
    ArrayList<Vitamin> items = new ArrayList<Vitamin>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.vitamin_item, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        final Vitamin vitamin = items.get(position);

        viewHolder.checkBox.setOnCheckedChangeListener(null);

        viewHolder.checkBox.setChecked(vitamin.getSupplement());

        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                vitamin.setSupplement(isChecked);
            }
        }); // end checkBox.setOnCheckedChangeListener
//
//        Vitamin item = items.get(position);
//        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView vitaminName;
        CheckBox checkBox;

        public ViewHolder(View itemView){
            super(itemView);

            vitaminName = itemView.findViewById(R.id.vitaminName);
            checkBox = itemView.findViewById(R.id.checkBox);
        }

        public void setItem(Vitamin item){
            // 비타민 이름 가져오기
            vitaminName.setText(item.getName());
            // 체크박스 체크 여부
            checkBox.setChecked(checkBox.isChecked());
        }
    }

    public void addItem(Vitamin item){
        items.add(item);
    }

    public void setItems(ArrayList<Vitamin> items){
        this.items = items;
    }

    public Vitamin getItem(int position){
        return items.get(position);
    }

    public void setItem(int position, Vitamin item){
        items.set(position, item);
    }
}
