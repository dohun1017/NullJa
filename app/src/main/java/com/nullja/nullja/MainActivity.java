package com.nullja.nullja;

import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainPagerAdapter mMainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        ViewPager mViewPager = (ViewPager)findViewById(R.id.view_pager);
        mViewPager.setAdapter(mMainPagerAdapter);

        TabLayout mTab = (TabLayout) findViewById(R.id.main_Tab) ;
        mTab.setupWithViewPager(mViewPager);

        Log.i("MainActivity","DB open Or Create");
        DB = openOrCreateDatabase(DataBase.DB_Name, MODE_PRIVATE, null);
        if(DB==null){
            Log.w("MainActivity : ","DB 없음 오류");
        }else{
            Log.i("MainActivity","DB open Or Create OK");
            DataBase.createTable(DB);
        }
    }

}
