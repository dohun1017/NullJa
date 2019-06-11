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
import android.widget.Button;
import android.widget.EditText;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class PageThreeFrag extends Fragment implements OnMapReadyCallback, LocationListener, PlacesListener {

    GoogleMap map;
    MapView mapView;
    LatLng currentPosition;
    List<Marker> prevMarkers;
    double latitude;
    double longitude;
    LocationListener locationListener;
    EditText editText;

    //지도 띄우기
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_page_three, container, false);

        prevMarkers = new ArrayList<>();
        mapView = (MapView) layout.findViewById(R.id.map);
        mapView.getMapAsync(this);
        Button button = (Button)layout.findViewById(R.id.button);
        editText = (EditText)layout.findViewById(R.id.editText);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String place = editText.getText().toString();
                Log.d("SSSS","place:"+place);
                Geocoder coder = new Geocoder(getContext());
                List<Address> list = null;
                try{
                    list = coder.getFromLocationName(place,1);
                    Address address = list.get(0);
                    double lat = address.getLatitude();
                    double log = address.getLongitude();
                    LatLng geoPoint = new LatLng(lat,log);
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(geoPoint,18));

                    MarkerOptions marker = new MarkerOptions();
                    marker.position(geoPoint);
                    marker.title(place);
                    showPlaceInformation(geoPoint);
                    map.addMarker(marker);
                } catch (IOException e){
                    prepareMap();
                    drawMap();
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
                .type(PlaceType.RESTAURANT)
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
    public void onStatusChanged(String provider, int status, Bundle extras) {    }

    @Override
    public void onProviderEnabled(String provider) {    }

    @Override
    public void onProviderDisabled(String provider) {    }

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
                Log.i("test", "currentPosition:" + currentPosition);
            }
        }
    }

    @Override
    public void onPlacesFailure(PlacesException e) {
    }

    @Override
    public void onPlacesStart() {    }

    @Override
    public void onPlacesSuccess(final List<Place> places) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                for (Place place : places) {
                    LatLng latLng = new LatLng(place.getLatitude(), place.getLongitude());
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);
                    markerOptions.title(place.getName());
                    markerOptions.snippet(place.getVicinity());
                    Marker item = map.addMarker(markerOptions);
                    prevMarkers.add(item);
                }
                HashSet<Marker> hashSet = new HashSet<Marker>();
                hashSet.addAll(prevMarkers);
                prevMarkers.clear();
                prevMarkers.addAll(hashSet);
            }
        });
    }

    @Override
    public void onPlacesFinished() {    }


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
}
