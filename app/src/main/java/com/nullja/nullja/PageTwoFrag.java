package com.nullja.nullja;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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
        return inflater.inflate(R.layout.fragment_page_two, container, false);
    }
    public static PageTwoFrag newInstance(){
        Bundle args = new Bundle();

        PageTwoFrag fragment = new PageTwoFrag();
        fragment.setArguments(args);
        return fragment;
    }
}
