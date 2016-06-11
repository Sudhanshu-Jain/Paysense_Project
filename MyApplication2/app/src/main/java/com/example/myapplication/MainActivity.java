package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    ListView list;
    ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.listview);
        String[] values = new String[] { "Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };

         adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);



        list.setAdapter(adapter);
        getTotalHeightofListView();





ddd
    }
    private void getTotalHeightofListView() {
        if(adapter.getCount() > 2){
            View item = adapter.getView(0, null, list);
            item.measure(0, 0);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (int) (2.5 * item.getMeasuredHeight()));
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOMddddddddddddddddddddddddddddddddddddddddddddddddd);
            list.setLayoutParams(params);
            list.requestLayout();
dd
        }
    }
}
