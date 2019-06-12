package com.nullja.nullja;


import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_APPEND;
import static android.content.Context.MODE_ENABLE_WRITE_AHEAD_LOGGING;
import static android.content.Context.MODE_PRIVATE;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;


/**
 * A simple {@link Fragment} subclass.
 */

import static com.nullja.nullja.MainActivity.DB;

public class PageOneFrag extends Fragment {

    private static final String TAG = "MainActivity";
    private static final int NUM_COLUMNS = 2;

    private ArrayList<byte[]> mImageUrls = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mContents = new ArrayList<>();

    public PageOneFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page_one, container, false);

        initImageBitmaps(view);

        // Inflate the layout for this fragment
        return view;
    }

    private void initImageBitmaps(View view){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");
        List<imageHotpl> hotplList = DataBase.getAllData(DB);
        Integer size = hotplList.size();
        Integer i=0;

        while(i<size){
            imageHotpl hp = hotplList.get(i);
            mImageUrls.add(hp.hotplimage);
            Log.i("DATA OK!! > ",hp.hotplname);
            mNames.add(hp.hotplname);
            mContents.add(hp.hotpladdr);
            i++;
        }

        initRecyclerView(view);
    }

    private void initRecyclerView(View view){
        Log.d(TAG, "initRecyclerView: initializing staggered recyclerview.");

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        StaggeredRecyclerViewAdapter staggeredRecyclerViewAdapter =
                new StaggeredRecyclerViewAdapter(getActivity(), mNames, mImageUrls, mContents);
        // 정렬 방식 설정
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(staggeredRecyclerViewAdapter);
    }

    public static PageOneFrag newInstance(){
        Bundle args = new Bundle();
        PageOneFrag fragment = new PageOneFrag();
        fragment.setArguments(args);
        return fragment;
    }
}
