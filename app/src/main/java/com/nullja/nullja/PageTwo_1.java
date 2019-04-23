package com.nullja.nullja;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class PageTwo_1 extends Fragment {

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page_two_1, container, false);
    }
    public static PageTwo_1 newInstance(){
        Bundle args = new Bundle();

        PageTwo_1 fragment = new PageTwo_1();
        fragment.setArguments(args);
        return fragment;
    }
}
