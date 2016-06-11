package com.example.horizontalscrollview;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class HorizontalScrollView extends Activity {
    CenterLockHorizontalScrollView centerLockHorizontalScrollview;
    CenterLockHorizontalScrollView centerLockHorizontalScrollView1;
    CustomListAdapter customListAdapter;
    CustomListAdapter customListAdapter1;
    Button btnPrev, btnNext;
    int currIndex = 0;
    private TextView text;
    ArrayList<String> list = new ArrayList<String>() {

        {
            add("Manchester city");
            add("Manchester United");
            add("Chelsea");
            add("Liverpool");
            add("TottenHam");
            add("Everton");
            add("WestHam");
            add("Arsenal");
            add("West Broom");
            add("New Castle");
            add("Norich City");
            add("Swansea city");
            add("stroke city");

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_scroll_view);

        text=(TextView)findViewById(R.id.text);
        centerLockHorizontalScrollview = (CenterLockHorizontalScrollView) findViewById(R.id.scrollView);
        centerLockHorizontalScrollView1 =(CenterLockHorizontalScrollView) findViewById(R.id.scrollView1);
        customListAdapter = new CustomListAdapter(this,
                R.layout.news_list_item, list);
        customListAdapter1 = new CustomListAdapter(this,R.layout.news_list_item,list);

        centerLockHorizontalScrollview.setAdapter(this, customListAdapter);
        centerLockHorizontalScrollView1.setAdapter(this,customListAdapter1);


    }


}
