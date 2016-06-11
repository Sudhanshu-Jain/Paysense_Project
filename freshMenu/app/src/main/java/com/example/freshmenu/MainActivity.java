package com.example.freshmenu;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    TextView title;
    String tabMainsTitle;
    String tabStarterTitle;
    String tabDesertTitle;
    ListView listView;



    ViewPagerAdapter adapter;

    private static MainsListFragment mainsListFragment;
    private static StarterListFragment startersListFragment;
    private static DesertListFragment desertListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        tabMainsTitle = getApplicationContext().getResources().getString(R.string.maintitle);
        tabStarterTitle = getApplicationContext().getResources().getString(R.string.startertitle);
        tabDesertTitle = getApplicationContext().getResources().getString(R.string.deserttitle);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        setupViewPager(this.viewPager, getSupportFragmentManager());

//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                hideKeyboard(viewPager.getChildAt(position));
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });



        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void setupViewPager(ViewPager viewPager, FragmentManager manager) {

        adapter = new ViewPagerAdapter(manager);

        mainsListFragment = new MainsListFragment();
        startersListFragment = new StarterListFragment();
        desertListFragment = new DesertListFragment();
        adapter.addFragment(mainsListFragment,"Mains");
        adapter.addFragment(startersListFragment,tabStarterTitle);
        adapter.addFragment(desertListFragment,tabDesertTitle);
        viewPager.setAdapter(adapter);
    }

    void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
