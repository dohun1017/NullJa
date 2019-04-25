package com.nullja.nullja;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class PageThreeFrag extends Fragment{


    public PageThreeFrag() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View three = inflater.inflate(R.layout.fragment_page_three, container, false);

        getChildFragmentManager().beginTransaction().replace(R.id.MapPage, MapPage.newInstance()).commit();


        return three;
    }
    public static PageThreeFrag newInstance(){
        Bundle args = new Bundle();

        PageThreeFrag fragment = new PageThreeFrag();
        fragment.setArguments(args);
        return fragment;
    }

}
