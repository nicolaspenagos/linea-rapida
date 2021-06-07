package com.example.linea_rapida;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.linea_rapida.model.User;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {

    private FirebaseAuth auth;

    private TextView nameET;
    private TextView emailET;
    private TextView roleET;
    private Button signOutBtn;
    private ImageView image;

    private User currentUser;


    public ProfileFragment(User currentUser) {
        // Required empty public constructor
        this.currentUser = currentUser;
        auth = FirebaseAuth.getInstance();
    }



    public static ProfileFragment newInstance(User currentUser) {


        ProfileFragment fragment = new ProfileFragment(currentUser);
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
        nameET = root.findViewById(R.id.profileNameET);
        emailET = root.findViewById(R.id.profileEmailET);
        roleET = root.findViewById(R.id.profileRoleET);
        image = root.findViewById(R.id.profileImage);

        nameET.setText(currentUser.getFullName());
        emailET.setText(currentUser.getEmail());
        roleET.setText(currentUser.getRole().getName());

        if(currentUser.getGender().equals("W")){
            image.setImageResource(R.drawable.dra);
        }

        signOutBtn.setOnClickListener((v)->{

            auth.signOut();
            Intent intent = new Intent(getActivity(), Login.class);
            startActivity(intent);


        });
        return root;
    }
}