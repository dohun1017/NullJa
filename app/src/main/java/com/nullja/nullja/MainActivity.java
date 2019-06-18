package com.nullja.nullja;

import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements PageThreeFrag.OnFragmentInteractionListener{
    private PageTwo_1 mPageTwo_1;
    private PageTwo_2 mPageTwo_2;
    private PageTwo_3 mPageTwo_3;
    private PageTwo_4 mPageTwo_4;
    public static SQLiteDatabase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPageTwo_1 = PageTwo_1.newInstance();
        mPageTwo_2 = PageTwo_2.newInstance();
        mPageTwo_3 = PageTwo_3.newInstance();
        mPageTwo_4 = PageTwo_4.newInstance();

        MainPagerAdapter mMainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        ViewPager mViewPager = (ViewPager)findViewById(R.id.view_pager);
        mViewPager.setAdapter(mMainPagerAdapter);

        TabLayout mTab = (TabLayout) findViewById(R.id.main_Tab) ;
        mTab.setupWithViewPager(mViewPager);

        Log.i("MainActivity","DB open Or Create");
        DB = openOrCreateDatabase(DataBase.DB_Name, MODE_ENABLE_WRITE_AHEAD_LOGGING, null);
        if(DB==null){
            Log.w("MainActivity : ","DB 없음 오류");
        }else{
            Log.i("MainActivity","DB open Or Create OK");
            DataBase.createTable(DB);
        }
    }
    @Override
    public void onFragmentInteraction(LatLng latlng, int cat) {
        if(latlng != null){
            Log.d("통신","메인"+String.valueOf(cat));
            Log.d("통신","메인"+String.valueOf(latlng.latitude));}
        switch (cat){
            case 0:
                mPageTwo_1.setLatLng(latlng);
                break;
            case 1:
                mPageTwo_2.setLatLng(latlng);
                break;
            case 2:
                mPageTwo_3.setLatLng(latlng);
                break;
            case 3:
                mPageTwo_4.setLatLng(latlng);
                break;
        }
    }
}
