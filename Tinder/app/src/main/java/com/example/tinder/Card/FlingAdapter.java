package com.example.tinder.Card;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

/**
 * Created by sudhanshu on 7/5/16.
 */
abstract public class FlingAdapter extends AdapterView {



    public int measuredWidth;
    public int measuredHeight;

    public int getWidthMeasure() {
        return measuredWidth;
    }

    public int getHeightMeasure() {
        return measuredHeight;
    }

    public FlingAdapter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.measuredWidth = widthMeasureSpec;
        this.measuredHeight = heightMeasureSpec;
    }

    @Override
    public Adapter getAdapter() {
        return null;
    }

    @Override
    public void setAdapter(Adapter adapter) {

    }

    @Override
    public View getSelectedView() {
        return null;
    }

    @Override
    public void setSelection(int position) {

    }
}
