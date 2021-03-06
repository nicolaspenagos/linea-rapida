package com.example.linea_rapida;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.linea_rapida.model.Role;
import com.example.linea_rapida.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
//import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    public final static int PERMISSIONS_CALLBACK = 11;

    private FirebaseAuth auth;
    private FirebaseFirestore db;

    private BottomNavigationView navigator;

    private MapsFragment mapsFragment;
    private ProfileFragment profileFragment;
    private Fragment homeFragment;

    private User currentUser;
    private String ubication;

    private String sintomasCabeza;
    private String sintomasPecho;
    private String sintomasEspalda;

    private String name;
    private String date;
    private String id;
    private String age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        String userJson = getIntent().getStringExtra("user");
        Log.e(">>>", userJson);
        Gson gson = new Gson();

        currentUser = gson.fromJson(userJson, User.class);
        ubication = "";
        sintomasCabeza = "";
        sintomasPecho = "";
        sintomasEspalda = "";

        name = "";
        date = "";
        id = "";
        age = "";

        ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                },
                PERMISSIONS_CALLBACK
        );

        //FirebaseUser fbUser = auth.getCurrentUser();

        FirebaseMessaging.getInstance().subscribeToTopic("news");





            navigator = findViewById(R.id.navigatorBNV);

            navigator.setOnNavigationItemSelectedListener(
                    (menuItem)->{

                        switch (menuItem.getItemId()){

                            case R.id.mapItem:
                                showFragment(mapsFragment);
                                break;

                            case R.id.homeItem:
                                showFragment(homeFragment);
                                break;

                            case R.id.profileItem:
                                showFragment(profileFragment);
                                break;

                        }
                        return true;
                    }

            );



            switch (currentUser.getRole().getRole()){
                case Role.ADMIN_ROLE:
                    homeFragment = HomeFragmentAdmin.newInstance();
                    break;
                case Role.REPORTER_ROLE:
                    homeFragment = HomeFragmentReport.newInstance();
                    break;
                case Role.DOCTOR_ROLE:
                    homeFragment = HomeFragmentCases.newInstance();
                    break;

            }

            mapsFragment = MapsFragment.newInstance();
            profileFragment = ProfileFragment.newInstance(currentUser);

            navigator.setSelectedItemId(R.id.homeItem);
            showFragment(homeFragment);





    }

    public void goToLogin() {

        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();

    }

    public void showFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();

    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == PERMISSIONS_CALLBACK){

            boolean allGrant = true;
            for (int i = 0; i<grantResults.length && allGrant; i++){
                if(grantResults[i] == PackageManager.PERMISSION_DENIED){
                    allGrant = false;
                }
            }


        }

    }

    public String getUbicationForReport(){
        return ubication;
    }

    public void setUbicationForReport(String ubication){
        this.ubication = ubication;
    }

    public String getSintomasCabeza(){
        return sintomasCabeza;
    }

    public void setSintomasCabeza(String sintomasCabeza){
        this.sintomasCabeza = sintomasCabeza;
    }

    public String getSintomasPecho(){
        return sintomasPecho;
    }

    public void setSintomasPecho(String sintomasPecho){
        this.sintomasPecho = sintomasPecho;
    }

    public String getSintomasEspalda(){
        return sintomasEspalda;
    }

    public void setSintomasEspalda(String sintomasPecho){
        this.sintomasEspalda = sintomasEspalda;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getAge(){
        return age;
    }

    public void setAge(String age){
        this.age = age;
    }


}