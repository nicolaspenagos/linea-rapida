package com.example.linea_rapida;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.linea_rapida.model.CaseTicket;
import com.example.linea_rapida.placeholder.PlaceholderContent;
import com.example.linea_rapida.util.Constants;
import com.example.linea_rapida.util.HTTPSWebUtilDomi;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A fragment representing a list of Items.
 */
public class TabCaseFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "1";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    FirebaseDatabase realtimeDatabase = FirebaseDatabase.getInstance();
    DatabaseReference reference = realtimeDatabase.getReference("cases");

    public static ArrayList<CaseTicket> caseTickets = new ArrayList<>();

    private View view;
    private static CaseRecyclerViewAdapter caseRecyclerViewAdapter;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TabCaseFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static TabCaseFragment newInstance(int columnCount) {
        TabCaseFragment fragment = new TabCaseFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        getCaseList();
    }


    private void getCaseList() {
        HTTPSWebUtilDomi httpsWebUtilDomi = new HTTPSWebUtilDomi();
        new Thread(() ->{
            String res = httpsWebUtilDomi.GETrequest(Constants.FIREBASE_BASEURL + "cases.json");
//                    String res = snapshot.getValue().toString();
//                    Log.e(">>>>>>>", "onDataChange: "+res);
//                    Log.e(">>>>>>>", "OnDataChange2: "+res1);
            Type type = new TypeToken<HashMap<String, CaseTicket>>(){}.getType();
            Gson gson = new Gson();
            HashMap<String, CaseTicket> caseTicketHashMap = gson.fromJson(res, type);
            caseTicketHashMap.forEach(
                    (key,value)->{
                        caseTickets.add(value);
                    }
            );
        }).start();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab_case_list, container, false);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            caseRecyclerViewAdapter = new CaseRecyclerViewAdapter(caseTickets);
            recyclerView.setAdapter(caseRecyclerViewAdapter);
        }
        return view;
    }

    public static void updateData() {
        caseRecyclerViewAdapter.notifyDataSetChanged();
    }

}