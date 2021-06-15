package com.example.linea_rapida;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChestBodyReport#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChestBodyReport extends Fragment {

    private ImageView back;
    private RadioButton peristalAuBtn;
    private RadioButton peristalDisBtn;
    private RadioButton masasBtn;
    private RadioButton megaliasBtn;

    private TextView peristalAumText;
    private TextView peristalDisText;
    private TextView masasText;
    private TextView megaliasText;

    private Button agregarSintomas;

    private String sintomas;

    public ChestBodyReport() {
        // Required empty public constructor
    }
//((MainActivity)getActivity()).setUbicationForReport(ubicationForReport);
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

        peristalAuBtn = root.findViewById(R.id.peristalAuBtn);
        peristalDisBtn = root.findViewById(R.id.peristalDisBtn);
        masasBtn = root.findViewById(R.id.masasBtn);
        megaliasBtn = root.findViewById(R.id.megaliasBtn);

        peristalAumText = root.findViewById(R.id.peristalAumText);
        peristalDisText = root.findViewById(R.id.peristalDisText);
        masasText = root.findViewById(R.id.masasText);
        megaliasText = root.findViewById(R.id.megaliasText);

        agregarSintomas = root.findViewById(R.id.addHeadSymptomBtn2);

        back.setOnClickListener(
                v -> {
                    ((MainActivity)getActivity()).showFragment(RegisterReport.newInstance());
                }

        );

        sintomas = "";


        agregarSintomas.setOnClickListener(
                v -> {
                    String finalSin = verificarSintomas();
                    ((MainActivity)getActivity()).setSintomasPecho(finalSin);
                    System.out.println(finalSin);
                }

        );



        return root;
    }

    public String verificarSintomas(){

        if(peristalAuBtn.isChecked()){
            sintomas += peristalAumText.getText() + ",";
        }

        if(peristalDisBtn.isChecked()){
            sintomas += peristalDisText.getText() + ",";
        }

        if(masasBtn.isChecked()){
            sintomas += masasText.getText() + ",";
        }

        if(megaliasBtn.isChecked()){
            sintomas += megaliasText.getText() + ",";
        }
        return sintomas;
    }
}