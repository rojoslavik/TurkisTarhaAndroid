package com.example.turkistarha;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class FoxAdapter extends ArrayAdapter<FoxThread> {

    static final int VIEW_TYPE_FOX = 0;
    static final int VIEW_TYPE_COUNT = 1;

    public FoxAdapter(Context context, ArrayList<FoxThread> foxes) {
        super(context, 0, foxes);
    }

    @Override
    public int getViewTypeCount(){
        return VIEW_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_FOX;
    }

    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, ViewGroup parent) {
        FoxThread base = getItem(position);

        if(convertView == null) {
            int layoutId = 0;

            layoutId = R.layout.foxlistitem;

            convertView = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        }
        TextView name = convertView.findViewById(R.id.foxName);
        name.setText("Fox " + base.getIndex() + 1);
        TextView food = convertView.findViewById(R.id.foxFood);
        food.setText("food " + base.getFood());
        base.setView(convertView);

        return convertView;
    }
}
