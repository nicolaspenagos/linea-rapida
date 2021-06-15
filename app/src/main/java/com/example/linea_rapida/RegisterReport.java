package com.example.linea_rapida;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.linea_rapida.model.CaseTicket;
import com.example.linea_rapida.model.FCMMessage;
import com.example.linea_rapida.model.FCMWrapper;
import com.example.linea_rapida.util.Constants;
import com.example.linea_rapida.util.HTTPSWebUtilDomi;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.Random;
import java.util.UUID;

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

        checkedBtn.setOnClickListener(
                v -> {
                    registrarPaciente();

                    String sintomas = ((MainActivity)getActivity()).getSintomasEspalda();
                    System.out.println(sintomas);
                }

        );



        return root;
    }

    public void registrarPaciente(){
        Random random = new Random();
        int randomNumber = random.nextInt(10000);

        String name = ((MainActivity)getActivity()).getName();
        String id = ((MainActivity)getActivity()).getId();
        String age = ((MainActivity)getActivity()).getAge();
        String actualDate = ((MainActivity)getActivity()).getDate();
//long date, String username, String userid, String location, String body, String status, long number

        String sintomas = ((MainActivity)getActivity()).getSintomasPecho() +" "+  ((MainActivity)getActivity()).getSintomasCabeza()
                + " " + ((MainActivity)getActivity()).getSintomasEspalda()+ ";"+"Diagnostico";
        System.out.println(sintomas+"diosssslos sintomas");
        String body = "Sintomas;Diagnostico";
        String username = "ana";
        String userid = ((MainActivity)getActivity()).getCurrentUser().getId();
        String location = ((MainActivity)getActivity()).getUbicationForReport();
        String status = "1";
        long number = (long) randomNumber;
        String caseId = UUID.randomUUID().toString();

        System.out.println(location+"funcionooooo");

        CaseTicket caseTicket = new CaseTicket(sintomas, actualDate, id, username, userid, location, status, number, name, age,caseId);
        Gson gson = new Gson();
        String json = gson.toJson(caseTicket);

        HTTPSWebUtilDomi https = new HTTPSWebUtilDomi();

        FCMWrapper wrapper = new FCMWrapper("/topics/news",new FCMMessage(UUID.randomUUID().toString(), "Caso: " + caseTicket.getNumber() + " Iniciado!"));
        String jsonNotification = gson.toJson(wrapper);
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