package com.example.linea_rapida;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.linea_rapida.model.Role;
import com.example.linea_rapida.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseFirestore db;

    private BottomNavigationView navigator;

    private MapsFragment mapsFragment;
    private ProfileFragment profileFragment;
    private Fragment homeFragment;

    private User currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        FirebaseUser fbUser = auth.getCurrentUser();

        if(fbUser == null){
            goToLogin();
        }else{





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







            db.collection("users").document(fbUser.getUid()).get().addOnCompleteListener(
                    task -> {
                        if(task.isSuccessful()){

                            DocumentSnapshot dataSnapshot = task.getResult();
                            currentUser = dataSnapshot.toObject(User.class);

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

                            mapsFragment = new MapsFragment();
                            profileFragment = ProfileFragment.newInstance(currentUser);

                            navigator.setSelectedItemId(R.id.homeItem);
                            showFragment(homeFragment);




                        }else{

                        }

                    }
            );



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