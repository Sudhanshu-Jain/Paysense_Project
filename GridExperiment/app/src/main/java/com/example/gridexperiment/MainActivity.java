package com.example.gridexperiment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //GridLayout gridLayout = (GridLayout)findViewById(R.id.choice_grid);
        
//        gridLayout.removeAllViews();
//
//        int total = 12;
//        int row = 5;
//        int column = total / row;
//        gridLayout.setColumnCount(column+1);
//        gridLayout.setRowCount(row);
//        for(int i =0, c = 0, r = 0; i < total; i++, r++)
//        {
//            if(r == row)
//            {
//                r = 0;
//                c++;
//            }
//            ImageView oImageView = new ImageView(this);
//            oImageView.setImageResource(R.drawable.abc_ic_ab_back_material);
//            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
//            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
//            param.width = GridLayout.LayoutParams.WRAP_CONTENT;
//            param.rightMargin = 5;
//            param.topMargin = 5;
//            param.setGravity(Gravity.CENTER);
//            param.columnSpec = GridLayout.spec(c);
//            param.rowSpec = GridLayout.spec(r);
//            oImageView.setLayoutParams (param);
//            gridLayout.addView(oImageView);
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
