package com.example.tinder;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.tinder.Card.CardListener;

import java.util.ArrayList;

/**
 * Created by sudhanshu on 7/5/16.
 */
public class CardStack extends RelativeLayout {
    private int numCards = 4;
    ArrayList<View> cardsList = new ArrayList<>();
    public int lastCardIndex = 0;
    Adapter mAdapter;
    public int mContentResource =0;


    public interface CardEventListener{

        boolean swipeEnd(int section,float distance);
        boolean swipeStart(int section,float distance);
        boolean swipeContinue(int section, float distanceX,float distanceY );
        void discarded(int mIndex, int direction);
        void topCardTapped();
    }


    public CardStack(Context context, AttributeSet attrs) {
        super(context, attrs);

        for(int i=0;i<numCards;i++){
            addContainerViews();
        }
        final View cardView = cardsList.get(cardsList.size()-1);

        cardView.setOnTouchListener(new CardListener());
    }

    private DataSetObserver dOb = new DataSetObserver(){
        @Override
        public void onChanged(){
            reset(false);
        }
    };

    public void setAdapter(final ArrayAdapter<?> adapter){
        if(mAdapter != null){
            mAdapter.unregisterDataSetObserver(dOb);
        }
        mAdapter = adapter;
        adapter.registerDataSetObserver(dOb);

        loadData();
    }

    public void reset(boolean index){
        removeAllViews();
        cardsList.clear();
        for(int i=0;i<numCards;i++){
            addContainerViews();
        }

    }

    public Adapter getAdapter(){
        return mAdapter;
    }

    private void loadLast(){
        ViewGroup parent = (ViewGroup)cardsList.get(0);

        int lastIndex = (numCards - 1)+ lastCardIndex;
        if(lastIndex > mAdapter.getCount() -1 ){
            parent.setVisibility(View.GONE);
            return;
        }

        View child = mAdapter.getView( lastIndex, getContentView(), parent);
        parent.removeAllViews();
        parent.addView(child);
    }



    public void addContainerViews(){
        FrameLayout layout = new FrameLayout(getContext());
        cardsList.add(layout);
        addView(layout);

    }

    private void loadData(){
        for(int i=numCards-1 ; i>=0 ; i--) {
            ViewGroup parent = (ViewGroup) cardsList    .get(i);
            int index = (lastCardIndex + numCards - 1) - i;
            if (index > mAdapter.getCount() - 1) {
                parent.setVisibility(View.GONE);
            }else{
                View child = mAdapter.getView(index, getContentView(), this);
                parent.addView(child);
                parent.setVisibility(View.VISIBLE);
            }
        }
    }

    private View getContentView(){
        View contentView = null;
        if(mContentResource != 0) {
            LayoutInflater lf = LayoutInflater.from(getContext());
            contentView = lf.inflate(mContentResource,null);
        }
        return contentView;

    }

    public void setContentResource(int res){
        mContentResource = res;
    }

    public void discadTop(){
        lastCardIndex++;
        loadLast();

        cardsList.get(0).setOnTouchListener(null);
        cardsList.get(cardsList.size() - 1).setOnTouchListener(new CardListener());
    }

    public int getStackSize() {
        return numCards;
    }
}
