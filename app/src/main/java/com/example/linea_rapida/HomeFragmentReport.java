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
import com.example.linea_rapida.model.FCMWrapper;
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
import java.util.Random;
import java.util.UUID;


public class HomeFragmentReport extends Fragment implements View.OnClickListener {

    private Button registerPatient;
    private EditText namePatientET;
    private EditText idPatientET;
    private EditText agePatientET;
    private EditText actualDateET;

    private CaseTicket caseTicket;

    private MapsFragment mapsFragment;

    private DatabaseReference mRootReference;

    private String ubication;

    public HomeFragmentReport() {
        // Required empty public constructor
        mapsFragment = MapsFragment.newInstance();
        ubication = "";
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

        mapsFragment.getUbicationForReport();
        return root;
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.registerPatientBtn:

                Random random = new Random();
                int randomNumber = random.nextInt(10000);

                ((MainActivity)getActivity()).setName(namePatientET.getText().toString());

                ((MainActivity)getActivity()).setId(idPatientET.getText().toString());
                ((MainActivity)getActivity()).setAge(agePatientET.getText().toString());
                ((MainActivity)getActivity()).setDate(actualDateET.getText().toString());
//long date, String username, String userid, String location, String body, String status, long number

               /* String sintomas = ((MainActivity)getActivity()).getSintomasPecho() + ((MainActivity)getActivity()).getSintomasPecho();
                System.out.println(sintomas);
                String body = "Sintomas;Diagnostico";
                String username = "ana";
                String userid = ((MainActivity)getActivity()).getCurrentUser().getId();
                String location = ((MainActivity)getActivity()).getUbicationForReport();
                String status = "1";
                long number = (long) randomNumber;
                String caseId = UUID.randomUUID().toString();

                System.out.println(location+"funcionooooo");

                CaseTicket caseTicket = new CaseTicket(body, actualDate, id, username, userid, location, status, number, name, age,caseId);
                Gson gson = new Gson();
                String json = gson.toJson(caseTicket);

                HTTPSWebUtilDomi https = new HTTPSWebUtilDomi();

                FCMWrapper wrapper = new FCMWrapper("/topics/news",new FCMMessage(UUID.randomUUID().toString(), "Caso: " + caseTicket.getNumber() + " Iniciado!"));
                String jsonNotification = gson.toJson(wrapper);
                new Thread(
                        ()->{
                            https.PUTrequest(Constants.FIREBASE_BASEURL+ "cases/" + caseId+".json",json);
                            //notificaci??n
                            https.POSTtoFCM(jsonNotification);
                        }
                ).start();
*/
                ((MainActivity)getActivity()).showFragment(RegisterReport.newInstance());


        }
    }
}