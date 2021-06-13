package com.example.linea_rapida;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragmentCases extends Fragment implements View.OnClickListener {



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragmentCases() {
        // Required empty public constructor
    }

    public static HomeFragmentCases newInstance() {
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
        View view = inflater.inflate(R.layout.fragment_home_cases, container, false);
        Button goToCasesBtn = view.findViewById(R.id.goToCasesBtn);
        goToCasesBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goToCasesBtn:
                Intent x = new Intent(getActivity(), CaseManagementActivity.class);
                startActivity(x);
                break;
        }
    }
}