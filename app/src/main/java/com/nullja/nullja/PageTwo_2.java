package com.nullja.nullja;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import static com.nullja.nullja.MainActivity.DB;


/**
 * A simple {@link Fragment} subclass.
 */
public class PageTwo_2 extends Fragment {
    private static final String TAG = "MainActivity";
    private LatLng latlng;
    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<byte[]> mImageUrls = new ArrayList<>();
    private ArrayList<String> mdistance = new ArrayList<>();

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_page_two_1);
        Log.d(TAG, "onCreate: started.");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page_two_2, container, false);
        // Inflate the layout for this fragment
        initImageBitmaps(view);
        return view;
    }

    private void initImageBitmaps(View view){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        //여기서 Category와 lat, lon을 현재 위치의 at와 on으로 수정하세요.

        if(latlng != null) {
            List<disHotpl> hotplList = DataBase.setCategoryData(DB, 2, latlng.latitude, latlng.longitude);
            Integer size = hotplList.size();
            Integer i = 0;

            while (i < size) {
                disHotpl hp = hotplList.get(i);
                mImageUrls.add(hp.hotplimage);
                Log.i("DATA OK!! > ", hp.hotplname);
                mNames.add(hp.hotplname);
                mdistance.add(Double.toString(hp.distance) + "km");
                i++;
            }
        }
        /*
        mImageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mNames.add("Havasu Falls");
        mdistance.add("30km");

        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNames.add("Trondheim");
        mdistance.add("30km");

        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("Portugal");
        mdistance.add("30km");

        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        mNames.add("Rocky Mountain National Park 22222222222222");
        mdistance.add("30km");

        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNames.add("Mahahual");
        mdistance.add("30km");

        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNames.add("Frozen Lake");
        mdistance.add("30km");

        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");
        mNames.add("White Sands Desert");
        mdistance.add("30km");

        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");
        mNames.add("Austrailia");
        mdistance.add("30km");

        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNames.add("Washington");
        mdistance.add("30km");
        */

        initRecyclerView(view);
    }

    private void initRecyclerView(View view){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerv_view2);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), mNames, mImageUrls, mdistance);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public static PageTwo_2 newInstance(){
        return new PageTwo_2();
    }
    public void setLatLng(LatLng latng){
        this.latlng = latlng;
    }
}
