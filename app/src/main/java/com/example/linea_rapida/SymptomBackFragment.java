package com.example.linea_rapida;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SymptomBackFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SymptomBackFragment extends Fragment {

    private RadioButton murEsterBtn;
    private RadioButton crepBtn;
    private RadioButton sibilanciasBtn;
    private RadioButton froteBtn;

    private TextView murEsterText;
    private TextView crepText;
    private TextView sibilanciasText;
    private TextView froteText;

    private Button agregarSintomas;

    private String sintomas;

    private ImageView backButton;

    public SymptomBackFragment() {
        // Required empty public constructor
    }
//((MainActivity)getActivity()).setUbicationForReport(ubicationForReport);
    // TODO: Rename and change types and number of parameters
    public static SymptomBackFragment newInstance() {
        SymptomBackFragment fragment = new SymptomBackFragment();
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
        View root = inflater.inflate(R.layout.fragment_symptom_back, container, false);

        backButton = root.findViewById(R.id.upBackButton);

        murEsterBtn = root.findViewById(R.id.murEsterBtn);
        crepBtn = root.findViewById(R.id.crepBtn);
        sibilanciasBtn = root.findViewById(R.id.sibilanciasBtn);
        froteBtn = root.findViewById(R.id.froteBtn);

        murEsterText = root.findViewById(R.id.murmulloEsterText);
        crepText = root.findViewById(R.id.crepitosText);
        sibilanciasText = root.findViewById(R.id.sibilanciasText);
        froteText = root.findViewById(R.id.froteText);

        agregarSintomas = root.findViewById(R.id.addBackSymptom);

        backButton.setOnClickListener(
                v -> {
                    ((MainActivity)getActivity()).showFragment(ReportBackBodyFragment.newInstance());
                }

        );

        sintomas = "";


        agregarSintomas.setOnClickListener(
                v -> {
                    String finalSin = verificarSintomas();
                    ((MainActivity)getActivity()).setSintomasEspalda(finalSin);
                    System.out.println(finalSin);
                }

        );


        return root;
    }

    public String verificarSintomas(){

        if(murEsterBtn.isChecked()){
            sintomas += murEsterText.getText() + ",";
        }

        if(crepBtn.isChecked()){
            sintomas += crepText.getText() + ",";
        }

        if(sibilanciasBtn.isChecked()){
            sintomas += sibilanciasText.getText() + ",";
        }

        if(froteBtn.isChecked()){
            sintomas += froteText.getText() + ",";
        }
        return sintomas;
    }
}