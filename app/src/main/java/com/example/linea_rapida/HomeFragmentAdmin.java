package com.example.linea_rapida;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.linea_rapida.list_logic.UserAdapter;
import com.example.linea_rapida.model.Role;
import com.example.linea_rapida.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;


public class HomeFragmentAdmin extends Fragment implements View.OnClickListener {

    private RecyclerView users_list;
    private LinearLayoutManager layoutManager;
    private UserAdapter adapter;

    private Button button_plant;
    private Button button_field;
    private ImageView button_add;
    private ImageView button_back;

    private User user;

    private FirebaseFirestore db;

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
        button_back = root.findViewById(R.id.button_back2);
        button_add = root.findViewById(R.id.button_add);

        button_plant.setOnClickListener(this);
        button_field.setOnClickListener(this);

        db = FirebaseFirestore.getInstance();

        button_add.setOnClickListener(
                (v) -> {
                    ((MainActivity)getActivity()).showFragment(RegisterUserFragment.newInstance());
                }
        );

        users_list.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        users_list.setLayoutManager(layoutManager);

        adapter = new UserAdapter(user, this);
        users_list.setAdapter(adapter);

        //llamado a metodo para obtener los doctores

        getData();

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_plant:
                button_field.setBackgroundResource(R.drawable.button_background_2);
                button_field.setTextColor(getResources().getColor(R.color.light_blue));
                button_plant.setBackgroundResource(R.drawable.button_background);
                button_plant.setTextColor(getResources().getColor(R.color.white));
                adapter.onlyPlant();
                break;

            case R.id.button_field:
                button_field.setBackgroundResource(R.drawable.button_background);;
                button_field.setTextColor(getResources().getColor(R.color.white));
                button_plant.setBackgroundResource(R.drawable.button_background_2);
                button_plant.setTextColor(getResources().getColor(R.color.light_blue));
                adapter.onlyField();
                break;
        }
    }

    private void getData() {
        adapter.getUsers().clear();
        db.collection("users")
                .get().addOnSuccessListener(
                command -> {
                    for (DocumentSnapshot doc : command.getDocuments()) {
                        User user = doc.toObject(User.class);
                        adapter.addUser(user);
                    }

                    if (button_plant.getCurrentTextColor() == -1){
                        adapter.onlyPlant();
                    }else{
                        adapter.onlyField();
                    }

                }
        );
    }

    public void deleteUserFromFireStore(String userId){

        db.collection("users").document(userId)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(">>>", "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(">>>", "Error deleting document", e);
                    }
                });

        getData();
    }

}
