package com.nullja.nullja;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TestPagerAdapter mTestPagerAdapter = new TestPagerAdapter(getSupportFragmentManager());
        ViewPager mViewPager = (ViewPager)findViewById(R.id.view_pager);
        mViewPager.setAdapter(mTestPagerAdapter);

        TabLayout mTab = (TabLayout) findViewById(R.id.main_Tab) ;
        mTab.setupWithViewPager(mViewPager);
    }

}
