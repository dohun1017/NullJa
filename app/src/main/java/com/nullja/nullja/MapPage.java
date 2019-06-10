package com.nullja.nullja;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapPage extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    MapView mapView = null;
    FragmentManager fragmentManager;


    public MapPage(){

    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d("MapPage","onCreate시작");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("MapPage","시작");

        //SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager();
        View MapV = inflater.inflate(R.layout.fragment_map_page, container, false);

        fragmentManager = getFragmentManager();

        mapView = (com.google.android.gms.maps.MapView)MapV.findViewById(R.id.MapPage);
        mapView.getMapAsync(this);
        Log.d("MapPage","getMapAsync 시작");

        return MapV;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onLowMemory();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //if(mapView != null)
        //{
            mapView.onCreate(savedInstanceState);
        //}
    }

    public static MapPage newInstance(){
        Bundle args = new Bundle();

        MapPage fragment = new MapPage();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.d("MapPage","onMapReady");
        MapsInitializer.initialize(this.getActivity());

        // Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(35.141233, 126.925594), 14);

        googleMap.animateCamera(cameraUpdate);

        /*googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.26606602672905, 126.99985343949024))
                .title("수원역"));*/
        LatLng soowon = new LatLng(37.26606602672905,126.99985343949024);
        mMap.addMarker(new MarkerOptions().position(soowon).title("수원역"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(soowon));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                
            }
        });
    }
}
