package com.nullja.nullja;

import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    
    SQLiteDatabase NullJaDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainPagerAdapter mMainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        ViewPager mViewPager = (ViewPager)findViewById(R.id.view_pager);
        mViewPager.setAdapter(mMainPagerAdapter);

        TabLayout mTab = (TabLayout) findViewById(R.id.main_Tab) ;
        mTab.setupWithViewPager(mViewPager);

    }

}
