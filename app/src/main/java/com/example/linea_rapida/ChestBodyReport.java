package com.example.linea_rapida;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChestBodyReport#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChestBodyReport extends Fragment {

    private ImageView back;

    public ChestBodyReport() {
        // Required empty public constructor
    }

    public static ChestBodyReport newInstance() {
        ChestBodyReport fragment = new ChestBodyReport();
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
        View root = inflater.inflate(R.layout.fragment_chest_body_report, container, false);

        back = root.findViewById(R.id.upBackButton);

        back.setOnClickListener(
                v -> {
                    ((MainActivity)getActivity()).showFragment(RegisterReport.newInstance());
                }

        );


        return root;
    }
}