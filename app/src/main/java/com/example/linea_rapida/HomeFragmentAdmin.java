package com.example.linea_rapida;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragmentAdmin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragmentAdmin extends Fragment {


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_admin, container, false);
    }
}