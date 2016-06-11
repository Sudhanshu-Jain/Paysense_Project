package com.example.tinder.Card;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import javax.xml.datatype.Duration;


/**
 * Created by sudhanshu on 7/5/16.
 */
public class CardListener implements View.OnTouchListener {

    public float x1;
    public float x2;
    static final int MIN_DISTANCE = 150;
    public static final String TAG = CardListener.class.getSimpleName();


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    // Left to Right swipe action
                    if (x2 > x1) {
                        Log.i(TAG, "Left to Right");
                    }


                    // Right to left swipe action
                    else {
                        Log.i(TAG, "Right to Left");
                    }

                } else {
                    Log.i(TAG, "Please swipe for more distance");
                }
                // consider as something else - a screen tap for example

                break;
        }
        return true;
    }
}
