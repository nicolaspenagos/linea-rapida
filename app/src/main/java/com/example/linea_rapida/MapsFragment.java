package com.example.linea_rapida;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment {

    private GoogleMap map;
    private LocationManager manager;
    private boolean availableGPS;
    private boolean availableNetwork;
    private LatLng openHere;
    private String provider;

    @SuppressLint("MissingPermission")
    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {

            map = googleMap;
            map.setMyLocationEnabled(true);
            setInitialPos();



        }
    };

    @SuppressLint("MissingPermission")
    public void setInitialPos(){


        //Determina si el NETWORK_PROVIDER esta disponible:
        try {
            availableNetwork = manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            provider = LocationManager.NETWORK_PROVIDER;
        } catch (Exception ex) {
            Log.e(">>>>","Error obtaining NETWORK_PROVIDER.");
        }


        //Determina si el GPS_PROVIDER esta disponible:
        try {
            availableGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            provider = LocationManager.GPS_PROVIDER;
        } catch (Exception ex) {
            Log.e(">>>>","Error obtaining GPS_PROVIDER.");
        }


        Location location = manager.getLastKnownLocation(provider);

        if(openHere!=null){
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(openHere, 16));
            openHere = null;
        }else{
            if(location!=null){

                LatLng myPos = new LatLng(location.getLatitude(), location.getLongitude());
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(myPos, 16));

            }
        }


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }

        manager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);

    }
}