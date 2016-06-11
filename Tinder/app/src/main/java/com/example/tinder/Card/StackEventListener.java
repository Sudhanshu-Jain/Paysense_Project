package com.example.tinder.Card;

import com.example.tinder.CardStack;

/**
 * Created by sudhanshu on 7/5/16.
 */
public class StackEventListener implements CardStack.CardEventListener {

    private int threshold;

    public StackEventListener(int i) {
        threshold = i;
    }

    @Override
    public boolean swipeEnd(int section, float distance) {
        return false;
    }

    @Override
    public boolean swipeStart(int section, float distance) {
        return false;
    }

    @Override
    public boolean swipeContinue(int section, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void discarded(int mIndex, int direction) {

    }

    @Override
    public void topCardTapped() {

    }
}
