package com.example.tinder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by sudhanshu on 7/5/16.
 */
public class CardsDataAdapter extends ArrayAdapter<String> {
    public CardsDataAdapter(Context context) {
        super(context, R.layout.card_content);
    }

    @Override
    public View getView(int position, final View contentView, ViewGroup parent){
        TextView v = (TextView)(contentView.findViewById(R.id.content));
        v.setText(getItem(position));
        return contentView;
    }
}
