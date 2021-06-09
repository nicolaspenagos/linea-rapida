package com.example.linea_rapida;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth auth;

    private EditText emailET;
    private Button resetPasswordBtn; 
    private ImageView goBackBtn;
    private TextView errorTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        auth = FirebaseAuth.getInstance();

        emailET = findViewById(R.id.resetPasswordEmailET);
        resetPasswordBtn = findViewById(R.id.resetPasswordBtn);
        goBackBtn = findViewById(R.id.resetPasswordGoBackBtn);
        errorTV = findViewById(R.id.resetPasswordErrorTV);

        resetPasswordBtn.setOnClickListener(this);
        goBackBtn.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.resetPasswordBtn:

                errorTV.setText("");
                String email = emailET.getText().toString().trim();

                if(!email.equals(""))
                    resetPassword(email);
                else
                    errorTV.setText("Por favor añade un correo.");

                break;
            case R.id.resetPasswordGoBackBtn:
                finish();
                break;
        }

    }

    private void resetPassword(String email) {

        auth.sendPasswordResetEmail(email).addOnCompleteListener(
                task -> {
                    Toast.makeText(this, "Email de verificación enviado", Toast.LENGTH_LONG).show();
                    finish();
                }
        );
    }
}