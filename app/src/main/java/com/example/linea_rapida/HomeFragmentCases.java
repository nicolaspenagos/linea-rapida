package com.example.linea_rapida;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragmentCases extends Fragment {



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragmentCases() {
        // Required empty public constructor
    }

    public static HomeFragmentCases newInstance(String param1, String param2) {
        HomeFragmentCases fragment = new HomeFragmentCases();
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
        return inflater.inflate(R.layout.fragment_home_cases, container, false);
    }
}