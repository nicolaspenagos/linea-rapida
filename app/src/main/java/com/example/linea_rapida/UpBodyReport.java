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
 * Use the {@link UpBodyReport#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpBodyReport extends Fragment {

    private RadioButton halopeciaBtn;
    private RadioButton pupilasSymp;
    private RadioButton agudezaBtn;
    private RadioButton mucosasSymp;

    private TextView halopeciaText;
    private TextView pupilasText;
    private TextView agudezaText;
    private TextView mucosasText;

    private Button agregarSintomas;

    private String sintomas;

    private ImageView backButton;

//((MainActivity)getActivity()).setUbicationForReport(ubicationForReport);
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

        halopeciaBtn = root.findViewById(R.id.haloSymp);
        pupilasSymp = root.findViewById(R.id.pupilasSymp);
        agudezaBtn = root.findViewById(R.id.agudezaSymp);
        mucosasSymp = root.findViewById(R.id.mucosasSymp);

        halopeciaText = root.findViewById(R.id.haloText);
        pupilasText = root.findViewById(R.id.pupilasText);
        agudezaText = root.findViewById(R.id.agudezaText);
        mucosasText = root.findViewById(R.id.mucosasText);

        agregarSintomas = root.findViewById(R.id.addHeadSymptomBtn3);

        backButton.setOnClickListener(
                v -> {
                    ((MainActivity)getActivity()).showFragment(RegisterReport.newInstance());
                }

        );

        sintomas = "";


        agregarSintomas.setOnClickListener(
                v -> {
                    String finalSin = verificarSintomas();
                    ((MainActivity)getActivity()).setSintomasCabeza(finalSin);
                    System.out.println(finalSin);
                }

        );



        return root;
    }

    public String verificarSintomas(){

        if(halopeciaBtn.isChecked()){
            sintomas += halopeciaText.getText() + ",";
        }

        if(pupilasSymp.isChecked()){
            sintomas += pupilasText.getText() + ",";
        }

        if(agudezaBtn.isChecked()){
            sintomas += agudezaText.getText() + ",";
        }

        if(mucosasSymp.isChecked()){
            sintomas += mucosasText.getText() + ",";
        }
        return sintomas;
    }
}