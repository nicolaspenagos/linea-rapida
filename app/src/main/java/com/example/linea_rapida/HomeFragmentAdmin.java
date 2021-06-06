package com.example.linea_rapida;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.linea_rapida.model.Role;
import com.example.linea_rapida.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.Executor;

public class HomeFragmentAdmin extends Fragment implements View.OnClickListener{

    private FirebaseAuth auth;
    private FirebaseFirestore db;


   private EditText fullnameET;
   private EditText emailET;
   private EditText usernameET;
   private EditText passwordET;
   private RadioButton plantRdoBtn;
   private RadioButton fieldRdoBtn;
   private RadioButton adminRdoBtn;
   private Button signUpBtn;
   private TextView signUpErrorTv;


    public HomeFragmentAdmin() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HomeFragmentAdmin newInstance() {
        HomeFragmentAdmin fragment = new HomeFragmentAdmin();
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

        View root = inflater.inflate(R.layout.fragment_home_admin, container, false);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


        fullnameET = root.findViewById(R.id.signUpFullnameET);
        emailET = root.findViewById(R.id.signUpEmailET);
        usernameET = root.findViewById(R.id.signUpUsernameET);
        passwordET = root.findViewById(R.id.signUpPasswordET);
        plantRdoBtn = root.findViewById(R.id.signUpPlantRdoBtn);
        fieldRdoBtn = root.findViewById(R.id.signUpfieldRdoBtn);
        adminRdoBtn = root.findViewById(R.id.signUpAdminRdoBtn);
        signUpBtn = root.findViewById(R.id.signUpBtn);
        signUpErrorTv = root.findViewById(R.id.signUpErrorTV);

        signUpBtn.setOnClickListener(this);
        plantRdoBtn.setOnClickListener(this);
        fieldRdoBtn.setOnClickListener(this);
        adminRdoBtn.setOnClickListener(this);

        return root;

    }

    public void signUp(String fullname, String email, String username, String password){

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(

                        task -> {
                            if(task.isSuccessful()){
                                createUser(fullname, email, username, auth.getCurrentUser().getUid());
                            }
                        }

                );



    }

    private void createUser(String fullname, String email, String username, String id) {

        // Creating the role
        Role role;

        if(plantRdoBtn.isChecked())
            role =  new Role(Role.DOCTOR_ROLE);

        else if(fieldRdoBtn.isChecked())
            role =  new Role(Role.REPORTER_ROLE);
        else  if(adminRdoBtn.isChecked())//Falta agregar el Radio Button para admin *********************************************
            role = new Role(Role.ADMIN_ROLE);
        else
            return;

        User newUser = new User(username, id, role, fullname, email);

        db.collection("users").document(newUser.getId()).set(newUser);


    }

    private boolean checkSignUpData(String fullname, String email, String username, String password, boolean plant, boolean field, boolean admin) {


        String errorMsg = "";
        signUpErrorTv.setText(errorMsg);

        if(fullname.equals(""))
            errorMsg += "Porfavor añade tu nombre.";

        if(email.equals(""))
            errorMsg += "Porfavor añade tu correo.";

        if(username.equals(""))
            errorMsg += "Porfavor añade tu nombre de usuario.";

        if(password.equals(""))
            errorMsg += "Porfavor añade tu contraseña.";

        if(!plant && !field && !admin)
            errorMsg += "Porfavor añade un rol.";


        if(errorMsg.equals(""))
            return true;
        else{
            signUpErrorTv.setText(errorMsg);
            return false;
        }



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signUpBtn:

                String fullname = fullnameET.getText().toString().trim();
                String email = emailET.getText().toString().trim();
                String username = usernameET.getText().toString().trim();
                String password = passwordET.getText().toString().trim();
                boolean plant = plantRdoBtn.isChecked();
                boolean field = fieldRdoBtn.isChecked();
                boolean admin = adminRdoBtn.isChecked();

                if(checkSignUpData(fullname, email, username, password, plant, field, admin))
                    signUp(fullname, email, username, password);


                break;

            case R.id.signUpPlantRdoBtn:
                fieldRdoBtn.setChecked(false);

                break;

            case R.id.signUpfieldRdoBtn:
                plantRdoBtn.setChecked(false);
                break;
        }
    }
}