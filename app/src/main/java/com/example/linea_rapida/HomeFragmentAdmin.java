package com.example.linea_rapida;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.linea_rapida.list_logic.UserAdapter;
import com.example.linea_rapida.model.Role;
import com.example.linea_rapida.model.User;


public class HomeFragmentAdmin extends Fragment implements View.OnClickListener {

    private RecyclerView users_list;
    private LinearLayoutManager layoutManager;
    private UserAdapter adapter;

    private Button button_plant;
    private Button button_field;
    private ImageView button_back;

    private User user;

    public HomeFragmentAdmin() {
        // Required empty public constructor
    }

    public static HomeFragmentAdmin newInstance() {
        HomeFragmentAdmin fragment = new HomeFragmentAdmin();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Role role = new Role();
        role.setRole(0);
        user = new User();
        user.setRole(role);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home_admin, container, false);

        users_list = root.findViewById(R.id.users_list);
        button_plant = root.findViewById(R.id.button_plant);
        button_field = root.findViewById(R.id.button_field);
        button_back = root.findViewById(R.id.button_back);

        button_plant.setOnClickListener(this);
        button_field.setOnClickListener(this);

        users_list.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        users_list.setLayoutManager(layoutManager);

        adapter = new UserAdapter(user);
        users_list.setAdapter(adapter);

        //llamado a metodo para obtener los doctores

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_plant:
                adapter.onlyPlant();
                break;

            case R.id.button_field:
                adapter.onlyField();
                break;
        }
    }
}
