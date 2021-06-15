package com.example.linea_rapida;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.text.DecimalFormat;
import java.util.Locale;

public class MapsFragment extends Fragment implements LocationListener {

    public final static double CENTRAL_HOSPITAL_LAT = 3.372741;
    public final static double CENTRAL_HOSPITAL_LONG = -76.526244;

    private GoogleMap map;
    private LocationManager manager;
    private boolean availableGPS;
    private boolean availableNetwork;
    private LatLng openHere;
    private String provider;
    private Geocoder geocoder;

    private String ubicationForReport;

    private TextView distanceTV;

    public MapsFragment(){

    }

    public static MapsFragment newInstance() {
        MapsFragment fragment = new MapsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

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




            LatLng hospital = new LatLng(CENTRAL_HOSPITAL_LAT, CENTRAL_HOSPITAL_LONG);

            map = googleMap;
            map.setMyLocationEnabled(true);


            Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.marker);
            Bitmap sacaleBitmap = Bitmap.createScaledBitmap(
                    image, image.getWidth()/6, image.getHeight()/6, true
            );

            map.addMarker(new MarkerOptions()
                    .position(hospital).title("Clínica Fundación Valle del Lili")
                    .icon(BitmapDescriptorFactory.fromBitmap(sacaleBitmap)));

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

        if(location!=null)
         updateDistance(location);
         ubicationForReport = location.getLatitude() + ", " + location.getLongitude();
         System.out.println(ubicationForReport);
        manager.requestLocationUpdates(provider, 1000, 1, (LocationListener) this);


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

        geocoder = new Geocoder(getContext(), Locale.getDefault());

        View root = inflater.inflate(R.layout.fragment_maps, container, false);
        distanceTV = root.findViewById(R.id.mapDistance);
        return root;
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

    @Override
    public void onLocationChanged(@NonNull Location location) {

        updateDistance(location);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    public void updateDistance(Location location){

        float[] results = new float[1];
        Location.distanceBetween(location.getLatitude(), location.getLongitude(), CENTRAL_HOSPITAL_LAT, CENTRAL_HOSPITAL_LONG, results);
        float distanceInMeters = results[0];

        if(distanceInMeters>1000){

            float distanceInKm = distanceInMeters/1000;
            DecimalFormat df = new DecimalFormat("#.0");
            distanceTV.setText(""+df.format(distanceInKm)+" km");

        }else{
            distanceTV.setText(""+Math.round(distanceInMeters)+" m");
        }

    }

    public String getUbicationForReport(){
        return ubicationForReport;
    }

    public void setUbicationForReport(String ubicationForReport){
        this.ubicationForReport = ubicationForReport;
    }

}