package com.example.linea_rapida;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpBodyReport#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpBodyReport extends Fragment {

    private ImageView backButton;

    public UpBodyReport() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static UpBodyReport newInstance() {
        UpBodyReport fragment = new UpBodyReport();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_up_body_report, container, false);

        backButton = root.findViewById(R.id.upBackButton);

        backButton.setOnClickListener(
                v -> {
                    ((MainActivity)getActivity()).showFragment(RegisterReport.newInstance());
                }

        );

        return root;
    }
}