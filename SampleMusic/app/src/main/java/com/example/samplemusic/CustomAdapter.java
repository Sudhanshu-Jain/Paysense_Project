package com.example.samplemusic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.samplemusic.models.Song;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sudhanshu on 9/4/16.
 */
public class CustomAdapter extends BaseAdapter {

    private Context mContext;

    private  List<List<Song>> map;

    public CustomAdapter(Context mContext, List<List<Song>> map) {


        this.mContext = mContext;
        this.map = map;

    }


    @Override
    public int getCount() {
        return map.size();
    }

    @Override
    public List<Song> getItem(int position) {
        return map.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        ViewHolder holder;
        List<Song> item = getItem(position);
        holder = new ViewHolder();
        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_layout, parent, false);
//            RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.parentLayout2);
//            for (int i = 0; i < item.getValue().size(); i++) {
//
//                TextView tv = new TextView(mContext);
//                tv.setText(item.getValue().get(i).getSongName());
//                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                layoutParams.setMargins(20,20,20,20);
//                tv.setLayoutParams(layoutParams);
//                layout.addView(tv);
//            }

            view.setTag(holder);
        }


        holder.key = (TextView) view.findViewById(R.id.text1);
        holder.value = (TextView) view.findViewById(R.id.text2);
        holder.text3 = (TextView)view.findViewById(R.id.text3);

        try {
            if (item.get(0) != null)
                holder.key.setText(item.get(0).getSongName());
            if (item.get(1) != null)
                holder.key.setText(item.get(1).getSongName());
            if (item.get(2) != null)
                holder.key.setText(item.get(2).getSongName());
        }catch (IndexOutOfBoundsException e){
            return view;
        }


        return view;


    }

    class ViewHolder {
        TextView key;
        TextView value;
        TextView text3;

    }
}
