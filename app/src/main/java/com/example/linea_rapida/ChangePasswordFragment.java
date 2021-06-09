 package com.example.linea_rapida;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

 public class ChangePasswordFragment extends Fragment implements View.OnClickListener{

    private ImageView goBackBtn;
    private EditText passwordET;
    private EditText rePasswordET;
    private Button changePasswordBtn;
    private TextView errorTV;

    private FirebaseAuth auth;

    public ChangePasswordFragment() {
        // Required empty public constructor
        auth = FirebaseAuth.getInstance();
    }


    public static ChangePasswordFragment newInstance() {
        ChangePasswordFragment fragment = new ChangePasswordFragment();
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
        View root =  inflater.inflate(R.layout.fragment_change_password, container, false);

        goBackBtn = root.findViewById(R.id.changePasswordGoBack);
        passwordET = root.findViewById(R.id.changePasswordET);
        rePasswordET = root.findViewById(R.id.reChangePasswordET);
        changePasswordBtn = root.findViewById(R.id.changePasswordBtn);
        errorTV = root.findViewById(R.id.changePasswordErrorET);

        changePasswordBtn.setOnClickListener(this);
        goBackBtn.setOnClickListener(this);

        return root;
    }

     @Override
     public void onClick(View v) {

        switch (v.getId()){
            case R.id.changePasswordGoBack:
                ((MainActivity)getActivity()).showFragment(ProfileFragment.newInstance(((MainActivity)getActivity()).getCurrentUser()));
                break;

            case R.id.changePasswordBtn:

                errorTV.setText("");

                String password = passwordET.getText().toString().trim();
                String rePassword = rePasswordET.getText().toString().trim();

                if(password.equals(rePassword)){

                    auth.getCurrentUser().updatePassword(password).addOnCompleteListener(
                            task -> {

                                if(task.isSuccessful()){

                                    Toast.makeText(getContext(), "Contraseña cambiada exitosamente.", Toast.LENGTH_LONG).show();
                                    ((MainActivity)getActivity()).showFragment(ProfileFragment.newInstance(((MainActivity)getActivity()).getCurrentUser()));

                                }else{
                                    Toast.makeText(getContext(), "Error, inicie sesión nuevamente y vuelva a intentarlo.", Toast.LENGTH_LONG).show();
                                }
                            }
                    );


                }else{
                    errorTV.setText("Las contraseñas no son iguales.");
                }

                break;
        }

     }
 }