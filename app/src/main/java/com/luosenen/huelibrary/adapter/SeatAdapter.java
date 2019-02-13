package com.luosenen.huelibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.luosenen.huelibrary.R;
import com.luosenen.huelibrary.info.Info;

import java.util.List;

public class SeatAdapter extends ArrayAdapter {
    private final int resourceId;

    public SeatAdapter(Context context, int textViewResourceId, List<Info> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Info info = (Info) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView name = (TextView) view.findViewById(R.id.result_account);
        TextView seat = (TextView) view.findViewById(R.id.result_location);
        TextView date = (TextView) view.findViewById(R.id.result_date);
        info.setAccount(info.getAccount());//为图片视图设置图片资源
        info.setSeat(info.getSeat());
        info.setUpdatedAt(info.getUpdatedAt());
        return view;
    }
}
