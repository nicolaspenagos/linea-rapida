package com.example.linea_rapida;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterReport#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterReport extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ImageView backReportBtn;
    private ImageButton girarBtn;
    private ImageButton checkedBtn;
    private Button headReport;
    private Button chestReport;
    private BottomNavigationView navigator;

    private MapsFragment mapsFragment;
    private ProfileFragment profileFragment;
    private Fragment homeFragment;


    public RegisterReport() {
        // Required empty public constructor
    }

    public static RegisterReport newInstance() {
        RegisterReport fragment = new RegisterReport();

        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           // userId_to_edit = getArguments().getString("userId");
        }else{
           // userId_to_edit = null;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_register_report, container, false);

        backReportBtn = root.findViewById(R.id.upBackButton);
        girarBtn = root.findViewById(R.id.girarButton);
        checkedBtn = root.findViewById(R.id.checkButton);
        headReport = root.findViewById(R.id.headButton);
        chestReport = root.findViewById(R.id.chestButton);

        backReportBtn.setOnClickListener(
                v -> {
                    ((MainActivity)getActivity()).showFragment(HomeFragmentReport.newInstance());
                }

        );

        headReport.setOnClickListener(
                v -> {
                    ((MainActivity)getActivity()).showFragment(UpBodyReport.newInstance());
                }

        );

        chestReport.setOnClickListener(
                v -> {
                    ((MainActivity)getActivity()).showFragment(ChestBodyReport.newInstance());
                }

        );

        girarBtn.setOnClickListener(
                v -> {
                    ((MainActivity)getActivity()).showFragment(ReportBackBodyFragment.newInstance());
                }

        );


        return root;
    }
}