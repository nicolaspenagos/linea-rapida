package com.example.linea_rapida;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    private BottomNavigationView navigator;

    private MapsFragment mapsFragment;
    private ProfileFragment profileFragment;
    private Fragment homeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() == null){
            goToLogin();
        }else{

            navigator = findViewById(R.id.navigatorBNV);

            mapsFragment = new MapsFragment();
            profileFragment = ProfileFragment.newInstance();
            //Si el usuario es admin -- Falta por implementar..
            homeFragment = HomeFragmentAdmin.newInstance();

        }

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


}