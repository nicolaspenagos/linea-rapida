package com.example.linea_rapida;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReportBackBodyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReportBackBodyFragment extends Fragment {

    private Button backSymptomsButton;
    private ImageButton turnFrontBodyButton;

    
    public ReportBackBodyFragment() {
        // Required empty public constructor
    }


    public static ReportBackBodyFragment newInstance() {
        ReportBackBodyFragment fragment = new ReportBackBodyFragment();
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
        View root = inflater.inflate(R.layout.fragment_report_back_body, container, false);

        backSymptomsButton = root.findViewById(R.id.backSymptomsButton);
        turnFrontBodyButton = root.findViewById(R.id.girarButton2);



        backSymptomsButton.setOnClickListener(
                v -> {
                    ((MainActivity)getActivity()).showFragment(SymptomBackFragment.newInstance());
                }

        );

        turnFrontBodyButton.setOnClickListener(
                v -> {
                    ((MainActivity)getActivity()).showFragment(RegisterReport.newInstance());
                }

        );


        return root;
    }

}