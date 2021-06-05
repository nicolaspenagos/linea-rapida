package com.example.linea_rapida;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class HomeFragmentAdmin extends Fragment {

   private EditText fullnameET;
   private EditText emailET;
   private EditText usernameET;
   private EditText passwordET;
   private RadioButton plantRdoBtn;
   private RadioButton fieldRdoBtn;
   private Button signUpBtn;


    public HomeFragmentAdmin() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HomeFragmentAdmin newInstance() {
        HomeFragmentAdmin fragment = new HomeFragmentAdmin();
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

        View root = inflater.inflate(R.layout.fragment_home_admin, container, false);

        fullnameET = root.findViewById(R.id.signUpFullnameET);
        emailET = root.findViewById(R.id.signUpEmailET);
        usernameET = root.findViewById(R.id.signUpUsernameET);
        passwordET = root.findViewById(R.id.signUpPasswordET);
        plantRdoBtn = root.findViewById(R.id.signUpPlantRdoBtn);
        fieldRdoBtn = root.findViewById(R.id.signUpfieldRdoBtn);
        signUpBtn = root.findViewById(R.id.signUpBtn);

        signUpBtn.setOnClickListener((v)->{


            checkSignUpData();


        });





        return root;

    }

    private void checkSignUpData() {
    }


}