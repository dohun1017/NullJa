package com.nullja.nullja;


import android.Manifest;
import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import noman.googleplaces.NRPlaces;
import noman.googleplaces.Place;
import noman.googleplaces.PlaceType;
import noman.googleplaces.PlacesException;
import noman.googleplaces.PlacesListener;

import static com.nullja.nullja.MainActivity.DB;

/**
 * A simple {@link Fragment} subclass.
 */
public class PageThreeFrag extends Fragment implements OnMapReadyCallback, LocationListener, PlacesListener {
    private OnFragmentInteractionListener mListener;
    GoogleMap map;
    MapView mapView;
    LatLng currentPosition;
    List<urlHotpl> prevDB;
    List<Marker> prevMarker;
    double latitude;
    double longitude;
    LocationListener locationListener;
    EditText editText;
    Spinner spinner;
    int cat = 1;
    final String[] place_show = {PlaceType.RESTAURANT, PlaceType.CAFE, PlaceType.AQUARIUM, PlaceType.ART_GALLERY};
    List<Marker> prevMarkers;
    EditText ed;
    List<Place> p;

    //지도 띄우기
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_page_three, container, false);

        prevDB = new ArrayList<urlHotpl>();
        mapView = (MapView) layout.findViewById(R.id.map);
        mapView.getMapAsync(this);
        Button button = (Button) layout.findViewById(R.id.button);
        Button a = (Button) layout.findViewById(R.id.button11);
        editText = (EditText) layout.findViewById(R.id.editText);
        spinner = (Spinner) layout.findViewById(R.id.spinner1);
        ed = (EditText) layout.findViewById(R.id.editText11);
        prevMarkers = new ArrayList<Marker>();
        p = new ArrayList<Place>();
        //cat = spinner.getTextAlignment();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String place = editText.getText().toString();
                Geocoder coder = new Geocoder(getContext());
                List<Address> list = null;
                try {
                    list = coder.getFromLocationName(place, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (list != null) {
                    if (list.size() == 0) {
                        prepareMap();
                        showPlaceInformation(currentPosition);
                        drawMap();
                    } else {
                        Address address = list.get(0);
                        double lat = address.getLatitude();
                        double log = address.getLongitude();
                        LatLng geoPoint = new LatLng(lat, log);
                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(geoPoint, 18));

                        MarkerOptions marker = new MarkerOptions();
                        marker.position(geoPoint);
                        marker.title(place);
                        showPlaceInformation(geoPoint);
                        map.addMarker(marker);
                    }
                } else {
                    prepareMap();
                    showPlaceInformation(currentPosition);
                    drawMap();
                }
            }
        });
        a.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("카테고리번호","aaaaaa");
                String place = ed.getText().toString();
                Log.d("카테고리번호",place);
                Geocoder coder = new Geocoder(getContext());
                List<Address> list = null;
                try {
                    list = coder.getFromLocationName(place, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("카테고리번호","에러");
                }
                if (list != null) {
                    if (list.size() == 0) {
                    } else {
                        Address address = list.get(0);
                        double lat = address.getLatitude();
                        double log = address.getLongitude();
                        LatLng geoPoint = new LatLng(lat, log);

                        onPlacesSuccess1(p, geoPoint);
                    }
                }
            }
        });
        return layout;

    }

    void showPlaceInformation(LatLng location) {
        if (map != null)
            map.clear();
        new NRPlaces.Builder()
                .listener(this)
                .key("AIzaSyC1epfuVA9Tl09Rz7z-VALUlLhVhPTrXlU")
                .latlng(location.latitude, location.longitude)
                .radius(1000) // 1km 이내 검색
                .type(place_show[0])
                .build()
                .execute();
        new NRPlaces.Builder()
                .listener(this)
                .key("AIzaSyC1epfuVA9Tl09Rz7z-VALUlLhVhPTrXlU")
                .latlng(location.latitude, location.longitude)
                .radius(1000) // 1km 이내 검색
                .type(place_show[1])
                .build()
                .execute();
        new NRPlaces.Builder()
                .listener(this)
                .key("AIzaSyC1epfuVA9Tl09Rz7z-VALUlLhVhPTrXlU")
                .latlng(location.latitude, location.longitude)
                .radius(1000) // 1km 이내 검색
                .type(place_show[2])
                .build()
                .execute();
        new NRPlaces.Builder()
                .listener(this)
                .key("AIzaSyC1epfuVA9Tl09Rz7z-VALUlLhVhPTrXlU")
                .latlng(location.latitude, location.longitude)
                .radius(1000) // 1km 이내 검색
                .type(place_show[3])
                .build()
                .execute();
    }

    @Override // 위치변경
    public void onLocationChanged(Location location) {
        if (Math.abs(latitude - location.getLatitude()) > 0.01 && Math.abs(longitude - location.getLongitude()) > 0.01) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            currentPosition = new LatLng(latitude, longitude);
            drawMap();
        }
    }

    void drawMap() {
        if (currentPosition != null) {
            map.clear();
            map.moveCamera(CameraUpdateFactory.newLatLng(currentPosition));
            map.animateCamera((CameraUpdateFactory.newLatLngZoom(currentPosition, 18)));
            MarkerOptions marker = new MarkerOptions();
            marker.position(currentPosition);
            marker.title("현재 위치");
            map.addMarker(marker);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        prepareMap();
        drawMap();
    }

    void prepareMap() {
        int check = ContextCompat.checkSelfPermission(
                getContext(), Manifest.permission.ACCESS_FINE_LOCATION);
        if (check != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            if (map != null) {
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                map.setMyLocationEnabled(true);
                map.getUiSettings().setZoomControlsEnabled(true);

                LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                String locationProvider = LocationManager.GPS_PROVIDER;
                Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);

                if (lastKnownLocation != null) {
                    latitude = lastKnownLocation.getLatitude();
                    longitude = lastKnownLocation.getLongitude();
                } else {
                    latitude = 37.5;
                    longitude = 126.9;
                }
                currentPosition = new LatLng(latitude, longitude);
                if (mListener != null) {
                    LatLng input = currentPosition;
                    mListener.onFragmentInteraction(input, cat);
                    Log.d("통신", "3:" + String.valueOf(cat));
                }
            }
        }
    }

    @Override
    public void onPlacesFailure(PlacesException e) {
    }

    @Override
    public void onPlacesStart() {
    }

    @Override
    public void onPlacesSuccess(final List<Place> places) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                for (Place place : places) {
                    p.add(place);
                    LatLng latLng = new LatLng(place.getLatitude(), place.getLongitude());
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);
                    markerOptions.title(place.getName());
                    markerOptions.snippet(place.getVicinity());
                    Marker item = map.addMarker(markerOptions);
                    prevMarkers.add(item);
                    urlHotpl db = new urlHotpl();
                    db.hotplcat = 1;
                    Log.d("카테고리번호", String.valueOf(cat));
                    db.hotplname = place.getName();
                    db.hotpladdr = place.getVicinity();
                    db.hotplat = place.getLatitude();
                    db.hotplon = place.getLongitude();
                    db.hotplimage = place.getIcon();
                    DataBase.insertData(DB, db);
                }
                HashSet<Marker> hashSet = new HashSet<Marker>();
                hashSet.addAll(prevMarkers);
                prevMarkers.clear();
                prevMarkers.addAll(hashSet);
            }
        });
    }

    public void onPlacesSuccess1(final List<Place> places, final LatLng latlng) {
        for (Place place : places) {
            if (latlng.latitude == place.getLatitude())
                if (latlng.longitude == place.getLongitude()) {
                    urlHotpl db = new urlHotpl();
                    db.hotplcat = ed.getTextAlignment();
                    Log.d("카테고리번호", String.valueOf(cat));
                    db.hotplname = place.getName();
                    db.hotpladdr = place.getVicinity();
                    db.hotplat = place.getLatitude();
                    db.hotplon = place.getLongitude();
                    db.hotplimage = place.getIcon();
                    DataBase.insertData(DB, db);
                }
        }
    }

    @Override
    public void onPlacesFinished() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
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
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MapsInitializer.initialize(getActivity().getApplicationContext());
        mapView.onCreate(savedInstanceState);
    }

    public static PageThreeFrag newInstance() {
        Bundle args = new Bundle();

        PageThreeFrag fragment = new PageThreeFrag();
        fragment.setArguments(args);
        return fragment;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(LatLng latlng, int category);
    }

    @Override
    public void onAttach(Context context) {
        Log.d("통신", "ㅇㅇㅇ");
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
