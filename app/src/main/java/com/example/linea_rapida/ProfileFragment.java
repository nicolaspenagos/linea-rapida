package com.example.linea_rapida;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {

    private FirebaseAuth auth;

    private Button signOutBtn;


    public ProfileFragment() {
        // Required empty public constructor

        auth = FirebaseAuth.getInstance();
    }



    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        signOutBtn = root.findViewById(R.id.profileLogOutBtn);

        signOutBtn.setOnClickListener((v)->{

            auth.signOut();
            Intent intent = new Intent(getActivity(), Login.class);
            startActivity(intent);


        });
        return root;
    }
}