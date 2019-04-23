package com.nullja.nullja;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class PageTwoFrag extends Fragment {


    public PageTwoFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View two = inflater.inflate(R.layout.fragment_page_two, container, false);

        getChildFragmentManager().beginTransaction().replace(R.id.hot_page, PageTwo_1.newInstance()).commit();

        Button button1, button2, button3, button4;
        button1 = (Button) two.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getChildFragmentManager().beginTransaction().replace(R.id.hot_page, PageTwo_1.newInstance()).commit();
            }
        });
        button2 = (Button) two.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getChildFragmentManager().beginTransaction().replace(R.id.hot_page, PageTwo_2.newInstance()).commit();
            }
        });
        button3 = (Button) two.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getChildFragmentManager().beginTransaction().replace(R.id.hot_page, PageTwo_3.newInstance()).commit();
            }
        });
        button4 = (Button) two.findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getChildFragmentManager().beginTransaction().replace(R.id.hot_page, PageTwo_4.newInstance()).commit();
            }
        });
        return two;
    }
    public static PageTwoFrag newInstance(){
        Bundle args = new Bundle();

        PageTwoFrag fragment = new PageTwoFrag();
        fragment.setArguments(args);
        return fragment;
    }
}