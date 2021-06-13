package com.example.linea_rapida;

import android.os.Bundle;

import com.example.linea_rapida.model.CaseTicket;
import com.example.linea_rapida.util.Constants;
import com.example.linea_rapida.util.HTTPSWebUtilDomi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.linea_rapida.ui.main.SectionsPagerAdapter;
import com.example.linea_rapida.databinding.ActivityCaseManagementBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

public class CaseManagementActivity extends AppCompatActivity {

    private ActivityCaseManagementBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCaseManagementBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);


        ImageView backArrow = binding.backArrowIV;

        ImageView syncData = binding.syncDataBtn;

        backArrow.setOnClickListener(view->{
            finish();
        });

        syncData.setOnClickListener(v -> {
            TabCaseFragment.caseTickets.clear();
            HTTPSWebUtilDomi httpsWebUtilDomi = new HTTPSWebUtilDomi();
            new Thread(() ->{
                String res = httpsWebUtilDomi.GETrequest(Constants.FIREBASE_BASEURL + "cases.json");

                Type type = new TypeToken<HashMap<String, CaseTicket>>(){}.getType();
                Gson gson = new Gson();

                HashMap<String, CaseTicket> caseTicketHashMap = gson.fromJson(res, type);
                caseTicketHashMap.forEach(
                        (key,value)->{
                            TabCaseFragment.caseTickets.add(value);
                        }
                );
            }).start();
            this.recreate();
        });
    }
}