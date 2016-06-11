package com.example.samplemusic;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.samplemusic.Utils.DbHelper;
import com.example.samplemusic.models.Song;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by sudhanshu on 8/4/16.
 */
public class FirstFragment extends Fragment {
    int leftMargin = 30;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1,container,false);

        LinearLayout parentLayout = (LinearLayout)view.findViewById(R.id.parentLayout);
       // ArrayList<Song> list =MainActivity.map1.get(MainActivity.artistList.get(0));
//        for(int i= 0;i<list.size();i++){
//            TextView tv=new TextView(getContext());
//            tv.setText(list.get(i).getSongName());
//            LinearLayout.LayoutParams paramsExample = new LinearLayout.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
//            paramsExample.setMargins(20,20,20,20);
//            tv.setLayoutParams(paramsExample);
//            parentLayout.addView(tv);
//            if(tv.)
//        }
        



//        TextView tv=new TextView(getContext());
//        tv.setText("test");
//        parentLayout.addView(tv);
//        int count = getUniqueListofArtist();
//        for(int i=0;i<count;i++){
//
//        }

        return view;

    }

//    public int getUniqueListofArtist(){
//        DbHelper dbHelper = DbHelper.getDatabaseHelper(getContext());
//        return dbHelper.getUniqueList();
//    }
}
