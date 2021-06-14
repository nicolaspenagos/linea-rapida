package com.example.linea_rapida;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.linea_rapida.connect.Constant;
import com.example.linea_rapida.model.CaseTicket;
import com.example.linea_rapida.model.FCMMessage;
import com.example.linea_rapida.util.Constants;
import com.example.linea_rapida.util.HTTPSWebUtilDomi;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class HomeFragmentReport extends Fragment implements View.OnClickListener {

    private Button registerPatient;
    private EditText namePatientET;
    private EditText idPatientET;
    private EditText agePatientET;
    private EditText actualDateET;

    private CaseTicket caseTicket;

    private DatabaseReference mRootReference;

    public HomeFragmentReport() {
        // Required empty public constructor
    }

    public static HomeFragmentReport newInstance() {
        HomeFragmentReport fragment = new HomeFragmentReport();
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
        View root = inflater.inflate(R.layout.fragment_home_report, container, false);

        mRootReference = FirebaseDatabase.getInstance().getReference();

        registerPatient = root.findViewById(R.id.registerPatientBtn);
        namePatientET = root.findViewById(R.id.namePatient);
        idPatientET = root.findViewById(R.id.idPatient);
        agePatientET = root.findViewById(R.id.agePatientEditText);
        actualDateET = root.findViewById(R.id.dateActualET);

        registerPatient.setOnClickListener(this);

        return root;
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.registerPatientBtn:

                String name = namePatientET.getText().toString();
                String id = idPatientET.getText().toString();
                String age = agePatientET.getText().toString();
                String actualDate = actualDateET.getText().toString();
//long date, String username, String userid, String location, String body, String status, long number

                String body = "Sintomas;Diagnostico";
                String username = "ana";
                String userid = "12";
                String location = "3.454563, 5.23534";
                String status = "1";
                long number = 3;
                String caseId = UUID.randomUUID().toString();

                CaseTicket caseTicket = new CaseTicket(body, actualDate, id, username, userid, location, status, number, name, age,caseId);
                Gson gson = new Gson();
                String json = gson.toJson(caseTicket);

                HTTPSWebUtilDomi https = new HTTPSWebUtilDomi();

                String jsonNotification = gson.toJson(new FCMMessage(UUID.randomUUID().toString(), "Caso: " + caseTicket.getNumber() + " Iniciado!"));
                new Thread(
                        ()->{
                            https.PUTrequest(Constants.FIREBASE_BASEURL+ "cases/" + caseId+".json",json);
                            //notificación
                            https.POSTtoFCM(jsonNotification);
                        }
                ).start();

                ((MainActivity)getActivity()).showFragment(RegisterReport.newInstance());


        }
    }
}