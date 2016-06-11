package com.example.horizontalscrollview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

class CenterLockHorizontalScrollView extends HorizontalScrollView {
    Context context;
    int prevIndex = 0;

    public CenterLockHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.setSmoothScrollingEnabled(true);

    }

    public void setAdapter(Context context, CustomListAdapter mAdapter) {

        try {
            fillViewWithAdapter(mAdapter);
        } catch (ZeroChildException e) {

            e.printStackTrace();
        }
    }

    private void fillViewWithAdapter(CustomListAdapter mAdapter)
            throws ZeroChildException {
        if (getChildCount() == 0) {
            throw new ZeroChildException(
                    "CenterLockHorizontalScrollView must have one child");
        }
        if (getChildCount() == 0 || mAdapter == null)
            return;

        ViewGroup parent = (ViewGroup) getChildAt(0);

        parent.removeAllViews();

        for (int i = 0; i < mAdapter.getCount(); i++) {
            parent.addView(mAdapter.getView(i, null, parent));
        }
    }


}

